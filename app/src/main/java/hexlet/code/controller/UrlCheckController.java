package hexlet.code.controller;

import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.utils.NamedRoutes;
import hexlet.code.utils.Utils;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.sql.SQLException;

public class UrlCheckController {
    public static void createCheck(Context ctx) throws SQLException {
        var urlId = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlsRepository.find(urlId)
                .orElseThrow(() -> new NotFoundResponse("ID = " + urlId + " not found"));

        try {
            HttpResponse<String> response = Unirest.get(url.getName()).asString();
            var statusCode = response.getStatus();
            Document document = Jsoup.parse(response.getBody());
            var title = document.title();
            var h1 = document.selectFirst("h1");
            var h1Element = h1 == null ? "" : h1.text();
            var descriptionElem = document.selectFirst("meta[name=description]");
            var description = descriptionElem == null ? "" : descriptionElem.attr("content");
            var urlCheck = new UrlCheck(statusCode, title, h1Element, description, urlId);
            urlCheck.setCreatedAt(Utils.getTime());
            UrlCheckRepository.save(urlCheck);
            ctx.sessionAttribute("flash", "Страница успешно проверена");
            ctx.sessionAttribute("flash-type", "success");
            ctx.redirect(NamedRoutes.urlPath(urlId));
        } catch (Exception e) {
            ctx.sessionAttribute("flash", "Некорректный URL");
            ctx.sessionAttribute("flash-type", "danger");
        }
        ctx.redirect(NamedRoutes.urlPath(urlId));
    }
}

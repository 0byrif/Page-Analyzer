package hexlet.code.dto.urls;

import hexlet.code.dto.BasePage;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
public final class UrlsPage extends BasePage {
    private List<Url> urls;
    private Map<Long, UrlCheck> checks;

    public UrlsPage(List<Url> urls) {
        this.urls = urls;
    }

    public UrlsPage(List<Url> urls, Map<Long, UrlCheck> checks) {
        this.urls = urls;
        this.checks = checks;
    }
}

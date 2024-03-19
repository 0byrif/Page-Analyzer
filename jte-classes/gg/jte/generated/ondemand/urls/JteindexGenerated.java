package gg.jte.generated.ondemand.urls;
import hexlet.code.utils.NamedRoutes;
import hexlet.code.dto.urls.UrlsPage;
public final class JteindexGenerated {
	public static final String JTE_NAME = "urls/index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,4,4,7,7,37,37,39,39,39,41,41,41,41,41,41,41,41,41,41,41,41,43,43,43,44,44,44,46,46,50,50,50,50,50,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPage page) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <div class=\"container-lg mt-5\">\n        <h1>Сайты</h1>\n        <style>\n            table {\n                border-collapse: collapse;\n                width: 100%;\n            }\n\n            th, td {\n                border: 1px solid #dddddd;\n                padding: 8px;\n                text-align: left;\n            }\n\n            th {\n                background-color: #f2f2f2;\n            }\n        </style>\n\n        <table class=\"table table-striped mt-3\">\n            <thead>\n            <tr>\n                <th scope=\"col\">ID</th>\n                <th scope=\"col\">Имя</th>\n                <th scope=\"col\">Последняя проверка</th>\n                <th scope=\"col\">Код ответа</th>\n            </tr>\n            </thead>\n            <tbody>\n            ");
				for (var url : page.getUrls()) {
					jteOutput.writeContent("\n                <tr>\n                    <th scope=\"row\">");
					jteOutput.setContext("th", null);
					jteOutput.writeUserContent(url.getId());
					jteOutput.writeContent("</th>\n                    <td>\n                        <a");
					var __jte_html_attribute_0 = NamedRoutes.urlPath(url.getId());
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
						jteOutput.writeContent(" href=\"");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(__jte_html_attribute_0);
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(">");
					jteOutput.setContext("a", null);
					jteOutput.writeUserContent(url.getName());
					jteOutput.writeContent("</a>\n                    </td>\n                    <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(page.getChecks().isEmpty() || page.getChecks().get(url.getId()) == null ? "" : page.getChecks().get(url.getId()).getCreatedAt().toString());
					jteOutput.writeContent("</td>\n                    <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(page.getChecks().isEmpty() || page.getChecks().get(url.getId()) == null ? "" : page.getChecks().get(url.getId()).getStatusCode().toString());
					jteOutput.writeContent("</td>\n                </tr>\n                ");
				}
				jteOutput.writeContent("\n            </tbody>\n        </table>\n    </div>\n        ");
			}
		}, page);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlsPage page = (UrlsPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}

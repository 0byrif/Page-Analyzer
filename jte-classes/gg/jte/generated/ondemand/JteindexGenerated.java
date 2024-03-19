package gg.jte.generated.ondemand;
import hexlet.code.utils.NamedRoutes;
import hexlet.code.dto.BasePage;
public final class JteindexGenerated {
	public static final String JTE_NAME = "index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,4,4,7,7,17,17,17,17,17,17,17,17,17,43,43,43,44,44,44,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, BasePage page) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <main class=\"flex-grow-1\">\n        <div class=\"px-4 py-5 my-5 text-left\">\n            <h1 class=\"display-5 fw-bold text-body-emphasis text-center\">\n                Анализатор страниц\n            </h1>\n            <div class=\"col-lg-6 mx-auto\">\n                <p class=\"lead mb-4 text-left\">\n                    Бесплатно проверяйте сайты на SEO пригодность\n                </p>\n                <form");
				var __jte_html_attribute_0 = NamedRoutes.urlsPath();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" action=\"");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" method=\"post\" class=\"rss-form text-body\">\n                    <div class=\"row\">\n                        <div class=\"col\">\n                            <div class=\"form-floating\">\n                                <input\n                                        id=\"url-input\"\n                                        autofocus type=\"text\"\n                                        required name=\"url\"\n                                        aria-label=\"url\"\n                                        class=\"form-control w-100\"\n                                        placeholder=\"RSS link\"\n                                        autocomplete=\"off\">\n                                <label for=\"url-input\">Ссылка</label>\n                            </div>\n                        </div>\n                        <div class=\"col-auto\">\n                            <button type=\"submit\" class=\"h-100 btn btn-lg btn-primary px-sm-5\">Проверить</button>\n                        </div>\n                    </div>\n                </form>\n                <div class=\"col mt-2 text-left\">\n                    <p class=\"lead mb-0\">Пример: https://www.example.com</p>\n                </div>\n            </div>\n        </div>\n    </main>\n");
			}
		}, page);
		jteOutput.writeContent("\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		BasePage page = (BasePage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}

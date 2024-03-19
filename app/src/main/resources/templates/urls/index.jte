@import hexlet.code.utils.NamedRoutes
@import hexlet.code.dto.urls.UrlsPage
@param UrlsPage page

@template.layout.page(
page = page,
content = @`
    <div class="container-lg mt-5">
        <h1>Сайты</h1>
        <style>
            table {
                border-collapse: collapse;
                width: 100%;
            }

            th, td {
                border: 1px solid #dddddd;
                padding: 8px;
                text-align: left;
            }

            th {
                background-color: #f2f2f2;
            }
        </style>

        <table class="table table-striped mt-3">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Имя</th>
                <th scope="col">Последняя проверка</th>
                <th scope="col">Код ответа</th>
            </tr>
            </thead>
            <tbody>
            @for(var url : page.getUrls())
                <tr>
                    <th scope="row">${url.getId()}</th>
                    <td>
                        <a href="${NamedRoutes.urlPath(url.getId())}">${url.getName()}</a>
                    </td>
                    <td>${page.getChecks().isEmpty() || page.getChecks().get(url.getId()) == null ? "" : page.getChecks().get(url.getId()).getCreatedAt().toString()}</td>
                    <td>${page.getChecks().isEmpty() || page.getChecks().get(url.getId()) == null ? "" : page.getChecks().get(url.getId()).getStatusCode().toString()}</td>
                </tr>
                @endfor
            </tbody>
        </table>
    </div>
        `
        )
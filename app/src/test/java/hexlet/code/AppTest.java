package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import hexlet.code.model.Url;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.utils.NamedRoutes;
import hexlet.code.utils.Utils;
import io.javalin.Javalin;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;

import io.javalin.testtools.JavalinTest;

class AppTest {
    private static MockWebServer mockWebServer;
    private static Javalin app;

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @BeforeEach
    public final void setApp() throws SQLException, IOException {
        app = App.getApp();
    }

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws IOException {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }

    @Test
    void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body()).isNotNull();
            assertThat(response.body().string()).contains("Анализатор страниц");
        });
    }

    @Test
    void testCreateUrl() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=https://ru.hexlet.io";
            var response = client.post(NamedRoutes.urlsPath(), requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("https://ru.hexlet.io");
        });
    }

    @Test
    void testUrlsPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.urlsPath());
            assertThat(response.code()).isEqualTo(200);
        });
    }


    @Test
    void testCreateIncorrectPage() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=67688";
            var response = client.post(NamedRoutes.urlsPath(), requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(UrlsRepository.getUrls()).isEmpty();
        });
    }

    @Test
    void testUrlsNotFound() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.urlPath("43434344"));
            assertThat(response.code()).isEqualTo(404);
        });
    }

    @Test
    void testCreatePage() throws SQLException {
        var url = new Url("https://www.example.com", Utils.getTime());
        UrlsRepository.save(url);
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=https://www.example.com";
            var response = client.post(NamedRoutes.urlsPath(), requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("https://www.example.com");
            assertThat(UrlsRepository.getUrls()).hasSize(1);
        });
    }

    @Test
    void testUrlCheck() throws IOException, SQLException {
        var url = new Url(mockWebServer.url("").toString());
        UrlsRepository.save(url);

        MockResponse mockedResponse = new MockResponse()
                .setBody(readFixture("testIndex.html")).setResponseCode(200);
        mockWebServer.enqueue(mockedResponse);

        JavalinTest.test(app, (server1, client) -> {
            var response = client.post(NamedRoutes.urlChecksPath(url.getId()));
            assertThat(response.code()).isEqualTo(200);

            var responseUrlDetail = client.get(NamedRoutes.urlPath(url.getId()));
            assertThat(responseUrlDetail.code()).isEqualTo(200);

            var expectedContents = Arrays.asList(
                    "200",
                    "Анализатор страниц",
                    "example",
                    "description"
            );
            var responseBody = responseUrlDetail.body().string();
            expectedContents.forEach(content -> assertThat(responseBody).contains(content));

            var urlChecks = UrlCheckRepository.getUrlCheck(url.getId());
            assertThat(urlChecks).isNotEmpty();

            var urlCheck = urlChecks.get(0);
            assertThat(urlCheck.getTitle()).isEqualTo("Анализатор страниц");
            assertThat(urlCheck.getH1()).isEqualTo("example");
            assertThat(urlCheck.getDescription()).isEqualTo("description");
        });
    }
}

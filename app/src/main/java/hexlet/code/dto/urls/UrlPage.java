package hexlet.code.dto.urls;

import hexlet.code.dto.BasePage;
import hexlet.code.model.UrlCheck;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;

@Getter
public final class UrlPage extends BasePage {
    private final Long id;
    private final String name;
    private final Timestamp createdAt;
    private final List<UrlCheck> urlChecks;

    public UrlPage(Long id, String name, Timestamp createdAt, List<UrlCheck> urlChecks) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.urlChecks = urlChecks;
    }
}

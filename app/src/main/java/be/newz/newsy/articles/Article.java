package be.newz.newsy.articles;

public class Article {
    private String title;
    private String url;
    private String published;
    private String source;
    private String sourceUrl;
    private int id;

    public Article() {
    }

    public Article(int id, String title, String url, String published, String source, String sourceUrl) {
        this.title = title;
        this.url = url;
        this.published = published;
        this.source = source;
        this.sourceUrl = sourceUrl;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return title;
    }
}

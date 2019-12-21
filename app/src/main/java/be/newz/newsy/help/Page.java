package be.newz.newsy.help;

public class Page {
    private String title;
    private Integer layoutFile;
    private Integer icon;
    private Integer color;

    public Page() {
    }

    public Page(String title, Integer layoutFile, Integer icon, Integer color) {
        this.title = title;
        this.layoutFile = layoutFile;
        this.icon = icon;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLayoutFile() {
        return layoutFile;
    }

    public void setLayoutFile(Integer layoutFile) {
        this.layoutFile = layoutFile;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }
}

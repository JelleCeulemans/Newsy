package be.newz.newsy.preferences;

public class Preference {
    private String country;
    private String language;
    private int position;

    public Preference() {
    }

    public Preference(String country, String language, int position) {
        this.country = country;
        this.language = language;
        this.position = position;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

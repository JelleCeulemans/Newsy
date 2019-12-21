package be.newz.newsy.preferences;

public class CountryItem {
    private String country;
    private String language;
    private int flagImage;
    private String countryValue;
    private String languageValue;

    public CountryItem() {
    }

    public CountryItem(String country, String language, int flagImage, String countryValue, String languageValue) {
        this.country = country;
        this.language = language;
        this.flagImage = flagImage;
        this.countryValue = countryValue;
        this.languageValue = languageValue;
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

    public int getFlagImage() {
        return flagImage;
    }

    public void setFlagImage(int flagImage) {
        this.flagImage = flagImage;
    }

    public String getCountryValue() {
        return countryValue;
    }

    public void setCountryValue(String countryValue) {
        this.countryValue = countryValue;
    }

    public String getLanguageValue() {
        return languageValue;
    }

    public void setLanguageValue(String languageValue) {
        this.languageValue = languageValue;
    }
}

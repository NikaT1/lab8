package client.clientUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;

public class LocalizationTool {

    private ResourceBundle resource;

    private int numberOfLang = 3;

    public LocalizationTool(ResourceBundle resource) {
        this.resource = resource;
    }

    public ResourceBundle getBundle() {
        return resource;
    }

    public int getNumberOfLang() {
        return numberOfLang;
    }

    public void setNumberOfLang(int numberOfLang) {
        this.numberOfLang = numberOfLang;
    }

    public final void setResource(ResourceBundle resource) {
        this.resource = resource;
    }

    public final ResourceBundle getResource() {
        return getBundle();
    }

    public String getDateString(LocalDate date) {
        if (date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(resource.getLocale());
            return date.format(formatter);
        } else return null;
    }

    public String getString(String word) {
        if (word == null) return null;
        else return resource.getString(word);
    }
}

package client.clientUtils;

import java.util.ResourceBundle;

public class LocalizationTool {

    private ResourceBundle resource = null;

    public LocalizationTool (ResourceBundle resource){
        this.resource = resource;
    }

    public ResourceBundle getBundle() {
        return resource;
    }

    public final void setResource(ResourceBundle resource) {
        this.resource = resource;
    }

    public final ResourceBundle getResource() {
        return getBundle();
    }

    public String getString(String word) {
        if (word==null) return null;
        else return resource.getString(word);
    }
}

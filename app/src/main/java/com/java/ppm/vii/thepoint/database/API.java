package com.java.ppm.vii.thepoint.database;

/**
 * Stores valid API calls to the remote database
 */
public enum API {
    INSERT("insert"),
    SELECT("select"),
    UPDATE("update"),
    DELETE("delete&id="),
    ABOUT("select-about"),
    CONTACT("contact");
    
    private static final String ROOT_URL = "PATH-TO-WEB-ROOT";
    private String urlOperation;

    /**
     * Sets the op parameter for the API URL call
     *
     * @param urlOperation The operation name
     */
    API(String urlOperation) {
        this.urlOperation = urlOperation;
    }

    /**
     * Retrieve the full URL for the API call
     *
     * @return The full URL for the API call
     */
    public String getURL() {
        return ROOT_URL + "api/api.php?op=" + this.urlOperation;
    }
    
    /**
     * Last minute addition
     */
    public static String getEventImageURL() {
        return ROOT_URL + "assets/img/events/";
    }

    /**
     * Last minute addition
     */
    public static String getAboutUsImageURL() {
        return ROOT_URL + "assets/img/";
    }
}

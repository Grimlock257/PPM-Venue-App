package com.java.ppm.vii.thepoint.database;

/**
 * Stores valid API calls to the remote database
 */
public enum API {
    INSERT("insert"),
    SELECT("select"),
    UPDATE("update"),
    DELETE("delete&id=");
    
    private static final String ROOT_URL = "PATH-TO-PHP-API-FILE";
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
        return ROOT_URL + this.urlOperation;
    }
}

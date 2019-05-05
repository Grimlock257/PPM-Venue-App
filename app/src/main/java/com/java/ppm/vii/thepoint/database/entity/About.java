package com.java.ppm.vii.thepoint.database.entity;

/**
 * Represent an About Us section from the database
 */
public class About {
    private int id;
    private String title;
    private String info;
    private String img;

    /**
     * About Constructor, represents an About Us section from the database
     *
     * @param id    The ID of the About Us section
     * @param title The title of the About Us section
     * @param info  The content for the About Us section
     * @param img   The img (if applicable) for the About Us section
     */
    public About(int id, String title, String info, String img) {
        this.id = id;
        this.title = title;
        this.info = info;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public String getImg() {
        return img;
    }
}

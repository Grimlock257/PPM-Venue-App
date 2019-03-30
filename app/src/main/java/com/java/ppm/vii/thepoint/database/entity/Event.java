package com.java.ppm.vii.thepoint.database.entity;

import java.io.Serializable;

/**
 * Represent an event from the database
 */
public class Event implements Serializable {
    private int id;
    private String promoter;
    private String title;
    private String date;
    private String description;
    private double price;
    private String ticketUrl;
    private String fbEvent;
    private String mainImage;
    private int active;

    /**
     * Event Constructor, represents an event from the database
     *
     * @param id          The ID of the event
     * @param promoter    The promoter of the event
     * @param title       The title of the event
     * @param date        The date of the event
     * @param description The description of the event
     * @param price       The price of the event
     * @param ticketUrl   The ticket URL of the event
     * @param fbEvent     The Facebook event page
     * @param mainImage   The poster for the event
     * @param active      Whether the event is active or not
     */
    public Event(int id, String promoter, String title, String date, String description, double price, String ticketUrl, String fbEvent, String mainImage, int active) {
        this.id = id;
        this.promoter = promoter;
        this.title = title;
        this.date = date;
        this.description = description;
        this.price = price;
        this.ticketUrl = ticketUrl;
        this.fbEvent = fbEvent;
        this.mainImage = mainImage;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public String getPromoter() {
        return promoter;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getTicketUrl() {
        return ticketUrl;
    }

    public String getFbEvent() {
        return fbEvent;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}

package com.hotels.api;

/**
 * Created by Peter_Kedvessy on 1/25/2017.
 */
public class Hotel {

    private String name;
    private String image;
    private String price;
    private String url;
    private double guestRating;
    private double starRating;
    private double tripAdvisorRating;
    private double priceAmount;

    public double getTripAdvisorRating() {
        return tripAdvisorRating;
    }

    public void setTripAdvisorRating(double tripAdvisorRating) {
        this.tripAdvisorRating = tripAdvisorRating;
    }

    public double getGuestRating() {
        return guestRating;
    }

    public void setGuestRating(double guestRating) {
        this.guestRating = guestRating;
    }

    public double getStarRating() {
        return starRating;
    }

    public void setStarRating(double starRating) {
        this.starRating = starRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(double priceAmount) {
        this.priceAmount = priceAmount;
    }
}

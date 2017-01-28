
package com.hotels.client.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {

    private Integer id;
    private String accommodationType;
    private Integer supplierHotelId;
    private String name;
    private Telephone telephone;
    private Address address;
    private String thumbnailUrl;
    private Double starRating;
    private Urls urls;
    private WelcomeRewards welcomeRewards;
    private GuestReviews guestReviews;
    private TripAdvisorGuestReviews tripAdvisorGuestReviews;
    private List<Landmark> landmarks = null;
    private RatePlan ratePlan;
    private String neighbourhood;
    private Deals deals;
    private Messaging messaging;
    private Integer position;
    private Sponsored sponsored;
    private Supplier supplier;
    private Badging badging;
    private String pimmsAttributes;
    private String altText;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccommodationType() {
        return accommodationType;
    }

    public void setAccommodationType(String accommodationType) {
        this.accommodationType = accommodationType;
    }

    public Integer getSupplierHotelId() {
        return supplierHotelId;
    }

    public void setSupplierHotelId(Integer supplierHotelId) {
        this.supplierHotelId = supplierHotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Telephone getTelephone() {
        return telephone;
    }

    public void setTelephone(Telephone telephone) {
        this.telephone = telephone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Double getStarRating() {
        return starRating;
    }

    public void setStarRating(Double starRating) {
        this.starRating = starRating;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public WelcomeRewards getWelcomeRewards() {
        return welcomeRewards;
    }

    public void setWelcomeRewards(WelcomeRewards welcomeRewards) {
        this.welcomeRewards = welcomeRewards;
    }

    public GuestReviews getGuestReviews() {
        return guestReviews;
    }

    public void setGuestReviews(GuestReviews guestReviews) {
        this.guestReviews = guestReviews;
    }

    public TripAdvisorGuestReviews getTripAdvisorGuestReviews() {
        return tripAdvisorGuestReviews;
    }

    public void setTripAdvisorGuestReviews(TripAdvisorGuestReviews tripAdvisorGuestReviews) {
        this.tripAdvisorGuestReviews = tripAdvisorGuestReviews;
    }

    public List<Landmark> getLandmarks() {
        return landmarks;
    }

    public void setLandmarks(List<Landmark> landmarks) {
        this.landmarks = landmarks;
    }

    public RatePlan getRatePlan() {
        return ratePlan;
    }

    public void setRatePlan(RatePlan ratePlan) {
        this.ratePlan = ratePlan;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public Deals getDeals() {
        return deals;
    }

    public void setDeals(Deals deals) {
        this.deals = deals;
    }

    public Messaging getMessaging() {
        return messaging;
    }

    public void setMessaging(Messaging messaging) {
        this.messaging = messaging;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Sponsored getSponsored() {
        return sponsored;
    }

    public void setSponsored(Sponsored sponsored) {
        this.sponsored = sponsored;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Badging getBadging() {
        return badging;
    }

    public void setBadging(Badging badging) {
        this.badging = badging;
    }

    public String getPimmsAttributes() {
        return pimmsAttributes;
    }

    public void setPimmsAttributes(String pimmsAttributes) {
        this.pimmsAttributes = pimmsAttributes;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

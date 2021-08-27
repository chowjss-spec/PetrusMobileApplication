package com.example.petrusapplication.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;

public class AdoptionRequest {
    private String adoptionRequestId;
    private String description;
    private LocalDate requestDate;
    private Residence residenceType;
    private RequestStatus requestStatus;
    private int dogsOwned;
    private String UserID;
    private String AdoptionListingID;
    private ApplicationStatus applicationStatus;
    private String name;
    private int age;
    private Breed breed1;



    private Color color1;
    private  String image;

    public AdoptionRequest(String adoptionRequestId, String description, LocalDate requestDate, Residence residenceType, RequestStatus requestStatus, int dogsOwned, String userID, String adoptionListingID) {
        adoptionRequestId = adoptionRequestId;
        description = description;
        requestDate = requestDate;
        this.residenceType = residenceType;
        requestStatus = requestStatus;
        this.dogsOwned = dogsOwned;
        UserID = userID;
        AdoptionListingID = adoptionListingID;
    }

    public String getAdoptionRequestId() {
        return adoptionRequestId;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Breed getBreed1() {
        return breed1;
    }

    public Color getColor1() {
        return color1;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public Residence getResidenceType() {
        return residenceType;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public int getDogsOwned() {
        return dogsOwned;
    }

    public String getUserID() {
        return UserID;
    }

    public String getAdoptionListingID() {
        return AdoptionListingID;
    }
    public AdoptionRequest(JSONObject object){
        try{
            this.adoptionRequestId = object.getString("adoptionRequestId");
            JSONObject listing = object.getJSONObject("adoptionListing");
            this.adoptionRequestId = listing.getString("adoptionListingID");
            this.name = listing.getString("name");
            this.image = listing.getString("image");
            int ordinalStatus = Integer.parseInt(listing.getString("applicationStatus"));
            this.applicationStatus = ApplicationStatus.values()[ordinalStatus];
            int ordinalBreed = Integer.parseInt(listing.getString("breed1"));
            this.breed1 = breed1.values()[ordinalBreed];
            int ordinalColor = Integer.parseInt(listing.getString("color1"));
            this.color1 = color1.values()[ordinalColor];
            this.age = Integer.parseInt(listing.getString("age"));



//            System.out.println(listing.getString("adoptionListingID"));
//            System.out.println("listing value is " + listing.getString("adoptionListingID"));
//            System.out.println("the value is " +object.getString("adoptionRequestId"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

enum Residence
{
    Pending, HDB, Private
}

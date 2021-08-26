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
            this.AdoptionListingID = object.getString("adoptionListingID");
            requestStatus = RequestStatus.valueOf(object.getString("requestStatus"));
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

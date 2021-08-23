package com.example.petrusapplication.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;

public class AdoptionRequest {
    private String AdoptionRequestId;
    private String Description;
    private LocalDate RequestDate;
    private Residence residenceType;
    private RequestStatus requestStatus;
    private int dogsOwned;
    private String UserID;
    private String AdoptionListingID;

    public AdoptionRequest(String adoptionRequestId, String description, LocalDate requestDate, Residence residenceType, RequestStatus requestStatus, int dogsOwned, String userID, String adoptionListingID) {
        AdoptionRequestId = adoptionRequestId;
        Description = description;
        RequestDate = requestDate;
        this.residenceType = residenceType;
        this.requestStatus = requestStatus;
        this.dogsOwned = dogsOwned;
        UserID = userID;
        AdoptionListingID = adoptionListingID;
    }

    public String getAdoptionRequestId() {
        return AdoptionRequestId;
    }

    public String getDescription() {
        return Description;
    }

    public LocalDate getRequestDate() {
        return RequestDate;
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
            this.AdoptionRequestId = object.getString("adoptionRequestId");
            this.AdoptionListingID = object.getString("adoptionListingID");
            this.requestStatus = RequestStatus.valueOf(object.getString("requestStatus"));
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


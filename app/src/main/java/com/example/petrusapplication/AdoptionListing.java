package com.example.petrusapplication;


import com.example.petrusapplication.models.ApplicationStatus;
import com.example.petrusapplication.models.Breed;
import com.example.petrusapplication.models.Color;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;

public class AdoptionListing {
    private String adoptionListingID;
    private Species species;
    private String name;
    private int age;
    private Breed breed1;
    private  Breed breed2;
    private  Gender gender;
    private Color color1;
    private  Color color2;
    private  Color color3;
    private  MaturitySize maturitySize;
    private  FurLength furLength;
    private  Vaccinated vaccinated;
    private  Dewormed dewormed;
    private  Sterilized sterilized;
    private  Health health;
    private  int quantityRepresented;
    private  double fee;
    private  String video;
    private  String image;
    private  String description;
    private  LocalDate listingDate;
    private ApplicationStatus applicationStatus;
    private  String acceptedRequest;
    private  String userID;

    public String getAdoptionListingID() {
        return adoptionListingID;
    }

    public Species getSpeciess() {
        return species;
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

    public Breed getBreed2() {
        return breed2;
    }

    public Gender getGender() {
        return gender;
    }

    public Color getColor1() {
        return color1;
    }

    public Color getColor2() {
        return color2;
    }

    public Color getColor3() {
        return color3;
    }

    public MaturitySize getMaturitySize() {
        return maturitySize;
    }

    public FurLength getFurLength() {
        return furLength;
    }

    public Vaccinated getVaccinated() {
        return vaccinated;
    }

    public Dewormed getDewormed() {return dewormed; }

    public Sterilized getSterilized() {
        return sterilized;
    }

    public Health getHealth() {
        return health;
    }

    public int getQuantityRepresented() {
        return quantityRepresented;
    }

    public double getFee() {
        return fee;
    }

    public String getVideo() {
        return video;
    }

    public String getImage() {
        return image;
    }

    public String getDescriptionn() {
        return description;
    }

    public LocalDate getListingDatee() {
        return listingDate;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public String getAcceptedRequest() {
        return acceptedRequest;
    }

    public String getUserID() {
        return userID;
    }

    public AdoptionListing(String adoptionListingID, Species species, String name, int age, Breed breed1, Breed breed2, Gender gender, Color color1, Color color2, Color color3, MaturitySize maturitySize, FurLength furLength, Vaccinated vaccinated, Dewormed dewormed, Sterilized sterilized, Health health, int quantityRepresented, double fee, String video, String image, String description, LocalDate listingDate, ApplicationStatus applicationStatus, String acceptedRequest, String userID) {
        this.adoptionListingID = adoptionListingID;
        this.species = species;
        this.name = name;
        this.age = age;
        this.breed1 = breed1;
        this.breed2 = breed2;
        this.gender = gender;
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        this.maturitySize = maturitySize;
        this.furLength = furLength;
        this.vaccinated = vaccinated;
        this.dewormed = dewormed;
        this.sterilized = sterilized;
        this.health = health;
        this.quantityRepresented = quantityRepresented;
        this.fee = fee;
        this.video = video;
        this.image = image;
        this.description = description;
        this.listingDate = listingDate;
        this.applicationStatus = applicationStatus;
        this.acceptedRequest = acceptedRequest;
        this.userID = userID;
    }



    public AdoptionListing(JSONObject object){
        try{
            this.adoptionListingID = object.getString("adoptionListingID");
            this.name = object.getString("name");
            this.image = object.getString("image");
            int ordinalStatus = Integer.parseInt(object.getString("applicationStatus"));
            this.applicationStatus = ApplicationStatus.values()[ordinalStatus];

            int ordinalBreed = Integer.parseInt(object.getString("breed1"));
            this.breed1 = breed1.values()[ordinalBreed];

            int ordinalColor = Integer.parseInt(object.getString("color1"));
            this.color1 = color1.values()[ordinalColor];

            this.age = Integer.parseInt(object.getString("age"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }


}

enum Species
{
    Cat, Dog
}

enum Gender
{
    Male, Female, Mixed
}

enum MaturitySize
{
    Small,
    Medium,
    Large,
    ExtraLarge,
    Unknown
}

enum FurLength
{
    Short,
    Medium,
    Long,
    Unknown
}

enum Vaccinated
{
    Yes, No, Unknown
}

enum Dewormed
{
    Yes, No, Unknown
}

enum Sterilized
{
    Yes, No, Unknown
}

enum Health
{
    Healthy,
    MinorCondition,
    MajorCondition,
    Unknown
}


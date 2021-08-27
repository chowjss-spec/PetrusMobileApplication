package com.example.petrusapplication.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;

public class AdoptionListing {
    private String AdoptionListingID;
    private Species Species;
    private String Name;
    private int Age;
    private  Breed Breed1;
    private  Breed Breed2;
    private  Gender Gender;
    private  Color Color1;
    private  Color Color2;
    private  Color Color3;
    private  MaturitySize maturitySize;
    private  FurLength FurLength;
    private  Vaccinated Vaccinated;
    private  Dewormed Dewormed;
    private  Sterilized Sterilized;
    private  Health Health;
    private  int QuantityRepresented;
    private  double Fee;
    private  String Video;
    private  String Image;
    private  String Description;
    private  LocalDate ListingDate;
    private  ApplicationStatus ApplicationStatus;
    private  String AcceptedRequest;
    private  String UserID;

    public String getAdoptionListingID() {
        return AdoptionListingID;
    }

    public com.example.petrusapplication.models.Species getSpecies() {
        return Species;
    }

    public String getName() {
        return Name;
    }

    public int getAge() {
        return Age;
    }

    public Breed getBreed1() {
        return Breed1;
    }

    public Breed getBreed2() {
        return Breed2;
    }

    public com.example.petrusapplication.models.Gender getGender() {
        return Gender;
    }

    public Color getColor1() {
        return Color1;
    }

    public Color getColor2() {
        return Color2;
    }

    public Color getColor3() {
        return Color3;
    }

    public MaturitySize getMaturitySize() {
        return maturitySize;
    }

    public com.example.petrusapplication.models.FurLength getFurLength() {
        return FurLength;
    }

    public com.example.petrusapplication.models.Vaccinated getVaccinated() {
        return Vaccinated;
    }

    public com.example.petrusapplication.models.Dewormed getDewormed() {
        return Dewormed;
    }

    public com.example.petrusapplication.models.Sterilized getSterilized() {
        return Sterilized;
    }

    public com.example.petrusapplication.models.Health getHealth() {
        return Health;
    }

    public int getQuantityRepresented() {
        return QuantityRepresented;
    }

    public double getFee() {
        return Fee;
    }

    public String getVideo() {
        return Video;
    }

    public String getImage() {
        return Image;
    }

    public String getDescription() {
        return Description;
    }

    public LocalDate getListingDate() {
        return ListingDate;
    }

    public com.example.petrusapplication.models.ApplicationStatus getApplicationStatus() {
        return ApplicationStatus;
    }

    public String getAcceptedRequest() {
        return AcceptedRequest;
    }

    public String getUserID() {
        return UserID;
    }

    public AdoptionListing(String adoptionListingID, com.example.petrusapplication.models.Species species, String name, int age, Breed breed1, Breed breed2, com.example.petrusapplication.models.Gender gender, Color color1, Color color2, Color color3, MaturitySize maturitySize, com.example.petrusapplication.models.FurLength furLength, com.example.petrusapplication.models.Vaccinated vaccinated, com.example.petrusapplication.models.Dewormed dewormed, com.example.petrusapplication.models.Sterilized sterilized, com.example.petrusapplication.models.Health health, int quantityRepresented, double fee, String video, String image, String description, LocalDate listingDate, com.example.petrusapplication.models.ApplicationStatus applicationStatus, String acceptedRequest, String userID) {
        AdoptionListingID = adoptionListingID;
        Species = species;
        Name = name;
        Age = age;
        Breed1 = breed1;
        Breed2 = breed2;
        Gender = gender;
        Color1 = color1;
        Color2 = color2;
        Color3 = color3;
        this.maturitySize = maturitySize;
        FurLength = furLength;
        Vaccinated = vaccinated;
        Dewormed = dewormed;
        Sterilized = sterilized;
        Health = health;
        QuantityRepresented = quantityRepresented;
        Fee = fee;
        Video = video;
        Image = image;
        Description = description;
        ListingDate = listingDate;
        ApplicationStatus = applicationStatus;
        AcceptedRequest = acceptedRequest;
        UserID = userID;
    }



    public AdoptionListing(JSONObject object){
        try{
            this.AdoptionListingID = object.getString("adoptionListingID");
            this.Name = object.getString("name");
            this.Image = object.getString("image");
            int ordinalStatus = Integer.parseInt(object.getString("applicationStatus"));
            this.ApplicationStatus = ApplicationStatus.values()[ordinalStatus];

            int ordinalBreed = Integer.parseInt(object.getString("breed1"));
            this.Breed1 = Breed1.values()[ordinalBreed];

            int ordinalColor = Integer.parseInt(object.getString("color1"));
            this.Color1 = Color1.values()[ordinalColor];

            this.Age = Integer.parseInt(object.getString("age"));
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


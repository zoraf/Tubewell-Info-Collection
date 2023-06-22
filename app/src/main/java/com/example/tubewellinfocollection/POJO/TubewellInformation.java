package com.example.tubewellinfocollection.POJO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TubewellInformation implements Serializable {


    @SerializedName("ownerName")
    private String ownerName;

    @SerializedName("ownerType")
    private String ownerType;

    @SerializedName("dateOfInstallation")
    private String dateOfInstallation;

    @SerializedName("isApprovalTaken")
    private String isApprovalTaken;

    @SerializedName("approvalAuthority")
    private String approvalAuthority;

    @SerializedName("lastApprovalDate")
    private String lastApprovalDate;

    @SerializedName("noOfUser")
    private String noOfUser;

    @SerializedName("purposeOfUsage")
    private List<String> purposeOfUsage = new ArrayList<String>();

    @SerializedName("amountOfWaterUse")
    private String amountOfWaterUse;

    @SerializedName("tubewellType")
    private String tubewellType;

    @SerializedName("modeOfAbstraction")
    private String modeOfAbstraction;

    @SerializedName("lengthOfPipeUsed")
    private String lengthOfPipeUsed;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

    public TubewellInformation() {
    }

    public TubewellInformation(String ownerName, String ownerType, String dateOfInstallation, String isApprovalTaken, String approvalAuthority, String lastApprovalDate, String noOfUser, List<String> purposeOfUsage, String amountOfWaterUse, String tubewellType, String modeOfAbstraction, String lengthOfPipeUsed, String latitude, String longitude) {
        this.ownerName = ownerName;
        this.ownerType = ownerType;
        this.dateOfInstallation = dateOfInstallation;
        this.isApprovalTaken = isApprovalTaken;
        this.approvalAuthority = approvalAuthority;
        this.lastApprovalDate = lastApprovalDate;
        this.noOfUser = noOfUser;
        this.purposeOfUsage = purposeOfUsage;
        this.amountOfWaterUse = amountOfWaterUse;
        this.tubewellType = tubewellType;
        this.modeOfAbstraction = modeOfAbstraction;
        this.lengthOfPipeUsed = lengthOfPipeUsed;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getDateOfInstallation() {
        return dateOfInstallation;
    }

    public void setDateOfInstallation(String dateOfInstallation) {
        this.dateOfInstallation = dateOfInstallation;
    }

    public String getIsApprovalTaken() {
        return isApprovalTaken;
    }

    public void setIsApprovalTaken(String isApprovalTaken) {
        this.isApprovalTaken = isApprovalTaken;
    }

    public String getApprovalAuthority() {
        return approvalAuthority;
    }

    public void setApprovalAuthority(String approvalAuthority) {
        this.approvalAuthority = approvalAuthority;
    }

    public String getLastApprovalDate() {
        return lastApprovalDate;
    }

    public void setLastApprovalDate(String lastApprovalDate) {
        this.lastApprovalDate = lastApprovalDate;
    }

    public String getNoOfUser() {
        return noOfUser;
    }

    public void setNoOfUser(String noOfUser) {
        this.noOfUser = noOfUser;
    }

    public List<String> getPurposeOfUsage() {
        return purposeOfUsage;
    }

    public void setPurposeOfUsage(List<String> purposeOfUsage) {
        this.purposeOfUsage = purposeOfUsage;
    }

    public String getAmountOfWaterUse() {
        return amountOfWaterUse;
    }

    public void setAmountOfWaterUse(String amountOfWaterUse) {
        this.amountOfWaterUse = amountOfWaterUse;
    }

    public String getTubewellType() {
        return tubewellType;
    }

    public void setTubewellType(String tubewellType) {
        this.tubewellType = tubewellType;
    }

    public String getModeOfAbstraction() {
        return modeOfAbstraction;
    }

    public void setModeOfAbstraction(String modeOfAbstraction) {
        this.modeOfAbstraction = modeOfAbstraction;
    }

    public String getLengthOfPipeUsed() {
        return lengthOfPipeUsed;
    }

    public void setLengthOfPipeUsed(String lengthOfPipeUsed) {
        this.lengthOfPipeUsed = lengthOfPipeUsed;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}

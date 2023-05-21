package com.example.tubewellinfocollection.POJO;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TubewellInformation {

    @SerializedName("ownerName")
    private String ownerName;

    @SerializedName("ownerType")
    private String ownerType;

    @SerializedName("installationDate")
    private Date installationDate;

    @SerializedName("isApproved")
    private String isApproved;

    @SerializedName("approvalAuthority")
    private String approvalAuthority;

    @SerializedName("numberOfUser")
    private String numberOfUser;

    @SerializedName("purposeOfUse")
    private List<String> purposeOfUse;

    @SerializedName("volumeOfWaterUsage")
    private String volumeOfWaterUsage;

    @SerializedName("typeOfTubewell")
    private String typeOfTubewell;

    @SerializedName("abstractionType")
    private String abstractionType;

    public TubewellInformation() {
        purposeOfUse = new ArrayList<String>();
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

    public Date getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(Date installationDate) {
        this.installationDate = installationDate;
    }

    public String getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(String isApproved) {
        this.isApproved = isApproved;
    }

    public String getApprovalAuthority() {
        return approvalAuthority;
    }

    public void setApprovalAuthority(String approvalAuthority) {
        this.approvalAuthority = approvalAuthority;
    }

    public String getNumberOfUser() {
        return numberOfUser;
    }

    public void setNumberOfUser(String numberOfUser) {
        this.numberOfUser = numberOfUser;
    }

    public List<String> getPurposeOfUse() {
        return purposeOfUse;
    }

    public void setPurposeOfUse(List<String> purposeOfUse) {
        this.purposeOfUse = purposeOfUse;
    }

    public String getVolumeOfWaterUsage() {
        return volumeOfWaterUsage;
    }

    public void setVolumeOfWaterUsage(String volumeOfWaterUsage) {
        this.volumeOfWaterUsage = volumeOfWaterUsage;
    }

    public String getTypeOfTubewell() {
        return typeOfTubewell;
    }

    public void setTypeOfTubewell(String typeOfTubewell) {
        this.typeOfTubewell = typeOfTubewell;
    }

    public String getAbstractionType() {
        return abstractionType;
    }

    public void setAbstractionType(String abstractionType) {
        this.abstractionType = abstractionType;
    }
}

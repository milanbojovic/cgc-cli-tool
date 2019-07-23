package com.sevenbridges.http.json;

import com.google.gson.annotations.SerializedName;

public class MetaData {
    private String library_id;
    private String reference_genome;
    private String sample_type;
    private String platform;
    private String investigation;
    @SerializedName("case_id")
    private String caseId;
    @SerializedName("sample_id")
    private String sampleId;
    @SerializedName("experimental_strategy")
    private String experimentalStrategy;
    private String gender;

    public MetaData(String library_id, String reference_genome, String sample_type, String platform, String investigation, String caseId, String sampleId, String experimentalStrategy, String gender) {
        this.library_id = library_id;
        this.reference_genome = reference_genome;
        this.sample_type = sample_type;
        this.platform = platform;
        this.investigation = investigation;
        this.caseId = caseId;
        this.sampleId = sampleId;
        this.experimentalStrategy = experimentalStrategy;
        this.gender = gender;
    }

    public String getLibrary_id() {
        return library_id;
    }

    public void setLibrary_id(String library_id) {
        this.library_id = library_id;
    }

    public String getReference_genome() {
        return reference_genome;
    }

    public void setReference_genome(String reference_genome) {
        this.reference_genome = reference_genome;
    }

    public String getSample_type() {
        return sample_type;
    }

    public void setSample_type(String sample_type) {
        this.sample_type = sample_type;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getInvestigation() {
        return investigation;
    }

    public void setInvestigation(String investigation) {
        this.investigation = investigation;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String getExperimentalStrategy() {
        return experimentalStrategy;
    }

    public void setExperimentalStrategy(String experimentalStrategy) {
        this.experimentalStrategy = experimentalStrategy;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "MetaData{" +
                "library_id='" + library_id + '\'' +
                ", reference_genome='" + reference_genome + '\'' +
                ", sample_type='" + sample_type + '\'' +
                ", platform='" + platform + '\'' +
                ", investigation='" + investigation + '\'' +
                ", caseId='" + caseId + '\'' +
                ", sampleId='" + sampleId + '\'' +
                ", experimentalStrategy='" + experimentalStrategy + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}

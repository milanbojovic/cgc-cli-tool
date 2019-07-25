package com.sevenbridges.http.json;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class UpdateFileRequest {
    String name;
    @SerializedName("metadata")
    MetaData metaData;
    String[] tags;

    public UpdateFileRequest() {}

    public UpdateFileRequest(String name, MetaData metaData, String[] tags) {
        this.name = name;
        this.metaData = metaData;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "UpdateFileRequest{" +
                "name='" + name + '\'' +
                ", metaData=" + metaData +
                ", tags=" + Arrays.toString(tags) +
                '}';
    }
}

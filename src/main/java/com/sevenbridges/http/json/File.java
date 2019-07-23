package com.sevenbridges.http.json;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class File {
    private String href;
    private String id;
    private String name;
    private int size;
    private String project;
    private String parent;
    private String type;
    @SerializedName("created_on")
    private String createdOn;
    @SerializedName("modified_on")
    private String modifiedOn;
    private Storage storage;
    private Origin origin;
    private String[] tags;
    @SerializedName("metadata")
    private MetaData metaData;

    public File(String href, String id, String name, int size, String project, String parent, String type, String createdOn, String modifiedOn, Storage storage, Origin origin, String[] tags, MetaData metaData) {
        this.href = href;
        this.id = id;
        this.name = name;
        this.size = size;
        this.project = project;
        this.parent = parent;
        this.type = type;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.storage = storage;
        this.origin = origin;
        this.tags = tags;
        this.metaData = metaData;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    @Override
    public String toString() {
        return "\nFile{" +
                "href='" + href + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", project='" + project + '\'' +
                ", parent='" + parent + '\'' +
                ", type='" + type + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", modifiedOn='" + modifiedOn + '\'' +
                ", storage=" + storage +
                ", origin=" + origin +
                ", tags=" + Arrays.toString(tags) +
                ", metaData=" + metaData +
                '}';
    }
}

package com.sevenbridges.http.json;

public class File {
    private String href;
    private String id;
    private String name;
    private String project;
    private String type;

    public File(String href, String id, String name, String project, String type) {
        this.href = href;
        this.id = id;
        this.name = name;
        this.project = project;
        this.type = type;
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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "\nFile{" +
                "href='" + href + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", project='" + project + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

package com.sevenbridges.http.json;

@SuppressWarnings("unused")
public class Project {
	private String href;
	private String id;
	private String name;
	private String created_by;
	private String created_on;
	private String modified_on;

	public Project(String href, String id, String name, String created_by, String created_on, String modified_on) {
		this.href = href;
		this.id = id;
		this.name = name;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_on = modified_on;
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

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public String getModified_on() {
		return modified_on;
	}

	public void setModified_on(String modified_on) {
		this.modified_on = modified_on;
	}

	@Override
	public String toString() {
		return "\nProject{" +
				"href='" + href + '\'' +
				", id='" + id + '\'' +
				", name='" + name + '\'' +
				", created_by='" + created_by + '\'' +
				", created_on='" + created_on + '\'' +
				", modified_on='" + modified_on + '\'' +
				'}';
	}
}

package com.example.jenkins.model;

import com.example.jenkins.bean.BaseModel;
import com.example.jenkins.bean.Build;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Mongo database annotation.
@Document(collection= "jobs")
public class Job extends BaseModel{

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    private int id;

    private String name;
    private String url;
    private String color;

    public List<Build> getBuilds() {
        return builds;
    }

    public void setBuilds(List<Build> builds) {
        this.builds = builds;
    }

    @JsonProperty("builds")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Build> builds = new ArrayList<Build>();

    public Job() {
    }
    
    public Job(String name, String url) {
        this();
        this.name = name;
        this.url = url;
        this.setColor(null);
    }

    public Job(String name, String url, String fullName) {
        this();
        this.name = name;
        this.url = url;
        this.setColor(fullName);
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
    
   

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Job job = (Job) o;

        if (name != null ? !name.equals(job.name) : job.name != null)
            return false;
        if (url != null ? !url.equals(job.url) : job.url != null)
            return false;
       
        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}

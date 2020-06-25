package com.example.jenkins.bean;

import java.util.List;

public class AllBuilds extends BaseModel {
    private List<Build> builds;

    public AllBuilds() {
    }

	public List<Build> getBuilds() {
		return builds;
	}

	public void setBuilds(List<Build> builds) {
		this.builds = builds;
	}

    
}
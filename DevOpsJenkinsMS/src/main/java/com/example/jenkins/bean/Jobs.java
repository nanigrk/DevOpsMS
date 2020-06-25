package com.example.jenkins.bean;

import java.util.List;

public class Jobs extends BaseModel {
    private List<Job> jobs;

    public Jobs() {
    }

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
}
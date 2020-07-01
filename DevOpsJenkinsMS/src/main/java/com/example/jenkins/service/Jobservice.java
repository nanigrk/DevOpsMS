package com.example.jenkins.service;



import com.example.jenkins.model.Job;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface Jobservice {

	/**
	 * Method to create new jobs in the db using mongo-db repository.
	 * @param job
	 */
	public void createJob(List<Job> job);

	/**
	 * Method to fetch all jobs from the db using mongo-db repository.
	 * @return
	 */
	public Collection<Job> getAllJobs();

	/**
	 * Method to fetch job by id using mongo-db repository.
	 * @param id
	 * @return
	 */
	public Optional<Job> findJobById(int id);

	/**
	 * Method to delete job by id using mongo-db repository.
	 * @param id
	 */
	public void deleteJobById(int id);

	/**
	 * Method to update job by id using mongo-db repository.
	 * @param job
	 */
	public void updateJob(Job job);

	/**
	 * Method to delete all jobs using mongo-db repository.
	 */
	public void deleteAllJobs();
}
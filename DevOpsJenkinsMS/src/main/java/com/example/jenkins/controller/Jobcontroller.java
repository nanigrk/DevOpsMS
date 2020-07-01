package com.example.jenkins.controller;


import com.example.jenkins.model.Job;
import com.example.jenkins.service.Jobservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value= "/api/mongo/job")
public class Jobcontroller {

	@Autowired
	Jobservice serv;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Method to save jobs in the db.
	 * @param job
	 * @return
	 */
	@PostMapping(value= "/create")
	public String create(@RequestBody List<Job> job) {
		logger.debug("Saving jobs.");
		serv.createJob(job);
		return "Job records created.";
	}

	/**
	 * Method to fetch all jobs from the db.
	 * @return
	 */
	@GetMapping(value= "/getall")
	public Collection<Job> getAll() {
		logger.debug("Getting all jobs.");
		return serv.getAllJobs();
	}

	/**
	 * Method to fetch job by id.
	 * @param id
	 * @return
	 */
	@GetMapping(value= "/getbyid/{job-id}")
	public Optional<Job> getById(@PathVariable(value= "job-id") int id) {
		logger.debug("Getting job with job-id= {}.", id);
		return serv.findJobById(id);
	}

	/**
	 * Method to update job by id.
	 * @param id
	 * @param e
	 * @return
	 */
	@PutMapping(value= "/update/{job-id}")
	public String update(@PathVariable(value= "job-id") int id, @RequestBody Job e) {
		logger.debug("Updating job with job-id= {}.", id);
		e.setId(id);
		serv.updateJob(e);
		return "Job record for job-id= " + id + " updated.";
	}

	/**
	 * Method to delete job by id.
	 * @param id
	 * @return
	 */
	@DeleteMapping(value= "/delete/{job-id}")
	public String delete(@PathVariable(value= "job-id") int id) {
		logger.debug("Deleting job with job-id= {}.", id);
		serv.deleteJobById(id);
		return "Job record for job-id= " + id + " deleted.";
	}

	/**
	 * Method to delete all jobs from the db.
	 * @return
	 */
	@DeleteMapping(value= "/deleteall")
	public String deleteAll() {
		logger.debug("Deleting all jobs.");
		serv.deleteAllJobs();
		return "All job records deleted.";
	}
}
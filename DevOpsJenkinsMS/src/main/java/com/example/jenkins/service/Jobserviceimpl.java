package com.example.jenkins.service;


import com.example.jenkins.dao.Jobdao;
import com.example.jenkins.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class Jobserviceimpl implements Jobservice {

	// The dao repository will use the Mongodb-Repository to perform the database operations.
	@Autowired
	Jobdao dao;

	/* (non-Javadoc)
	 * @see com.assignment.springboot.mongo.service.Jobservice#createJob(java.util.List)
	 */
	@Override
	public void createJob(List<Job> job) {
		dao.saveAll(job);
	}

	/* (non-Javadoc)
	 * @see com.assignment.springboot.mongo.service.Jobservice#getAllJobs()
	 */
	@Override
	public Collection<Job> getAllJobs() {
		return dao.findAll();
	}

	/* (non-Javadoc)
	 * @see com.assignment.springboot.mongo.service.Jobservice#findJobById(int)
	 */
	@Override
	public Optional<Job> findJobById(int id) {
		return dao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.assignment.springboot.mongo.service.Jobservice#deleteJobById(int)
	 */
	@Override
	public void deleteJobById(int id) {
		dao.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.assignment.springboot.mongo.service.Jobservice#updateJob(int)
	 */
	@Override
	public void updateJob(Job job) {
		dao.save(job);
	}

	/* (non-Javadoc)
	 * @see com.assignment.springboot.mongo.service.Jobservice#deleteAllJobs()
	 */
	@Override
	public void deleteAllJobs() {
		dao.deleteAll();
	}
}
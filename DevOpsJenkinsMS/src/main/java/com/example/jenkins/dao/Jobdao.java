package com.example.jenkins.dao;


import com.example.jenkins.model.Job;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Jobdao extends MongoRepository<Job, Integer> {

}
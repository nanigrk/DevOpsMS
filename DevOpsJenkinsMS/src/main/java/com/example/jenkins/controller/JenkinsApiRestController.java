package com.example.jenkins.controller;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.jenkins.bean.AllBuilds;
import com.example.jenkins.bean.Build;
import com.example.jenkins.bean.BuildWithDetails;
import com.example.jenkins.bean.Job;
import com.example.jenkins.bean.JobWithDetails;
import com.example.jenkins.bean.Jobs;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "JenkinsApiRestController", description = "REST Apis related to Jenkins!!!!", tags="Jenkins operations")
@RestController
@RequestMapping("/devops/jenkins")
public class JenkinsApiRestController {

	@Value("${application.jenkins.root.uri}")
	private String ROOT_URI;
	
	private final String API_URL = "/api/json";

	@Autowired
	private RestTemplate restTemplate;

	@ApiOperation(value = "Get list of jobs in the local jenkins", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })

	@RequestMapping(value = "/getJobs", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "ServiceUnavalible")
	public List<Job> getJobs() {
		System.out.println("getJobs  : " + ROOT_URI + "?tree=jobs[name,color,url]");
		ResponseEntity<Jobs> response = restTemplate.exchange(ROOT_URI + API_URL + "?tree=jobs[name,color,url]",
				HttpMethod.GET, new HttpEntity<Jobs>(createHeaders("Kishore", "Kishore")), Jobs.class);
		// Jobs jobs =
		// restTemplate.getForObject(ROOT_URI+"?pretty&tree=jobs[name,url,fullName]",
		// Jobs.class);
		return response.getBody().getJobs();
	}

	
	@ApiOperation(value = "Get specific JobDetails in the local jenkins ", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })

	@RequestMapping(value = "/getJobDetails/{jobName}", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod="ServiceUnavalible", commandProperties =    { @HystrixProperty(name="execution.isolation.thread.timeoutInMilliSeconds", value="500")})
	public JobWithDetails getJobDetails(@PathVariable("jobName") String jobName) {
		System.out.println("getJobDetails  : " + ROOT_URI + "/job/FirstTest");
		ResponseEntity<JobWithDetails> response = restTemplate.exchange(ROOT_URI + API_URL + "/job/"+jobName,
				HttpMethod.GET, new HttpEntity<Jobs>(createHeaders("Kishore", "Kishore")), JobWithDetails.class);
		return response.getBody();
	}

	@ApiOperation(value = "Get all builds for a specific job in the local jenkins ", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })

	@RequestMapping(value = "/getAllBuilds/{jobName}", method = RequestMethod.GET)
	public AllBuilds getAllBuilds(@PathVariable("jobName") String jobName) {
		System.out.println("getAllBuilds  : " +ROOT_URI + "/job/"+jobName+API_URL+"?tree=builds[number,timestamp,id,result]");
		ResponseEntity<AllBuilds> response = restTemplate.exchange(ROOT_URI + "/job/"+jobName+API_URL+"?tree=builds[number,timestamp,id,result,url]",
				HttpMethod.GET, new HttpEntity<AllBuilds>(createHeaders("Kishore", "Kishore")), AllBuilds.class);
		return response.getBody();
	}
	
	
	@SuppressWarnings({ "serial", "unchecked" })
	@ApiOperation(value = "Get all builds for a specific job by Ststus in the local jenkins ", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })

	@RequestMapping(value = "/getAllBuildsByStatus/{jobName}/ByStatus", method = RequestMethod.GET)
	public AllBuilds getAllBuildsByStatus(@PathVariable("jobName") String jobName,
			@RequestParam("status") String status) {
		System.out.println("getAllBuilds  : " + ROOT_URI + "/job/"+jobName+API_URL+"?tree=builds[number,timestamp,id,result]");
		ResponseEntity<AllBuilds> response = restTemplate.exchange(ROOT_URI + "/job/"+jobName+API_URL+"?tree=builds[number,timestamp,id,result,url]",
				HttpMethod.GET, new HttpEntity<AllBuilds>(createHeaders("Kishore", "Kishore")), AllBuilds.class);
		 AllBuilds allBuilds = new AllBuilds();
		 List<Build> builds = response.getBody().getBuilds();
		  builds =  builds
				.stream()
				.filter(b -> b.getResult().equalsIgnoreCase(status))
				.collect(Collectors.toList());
		  System.out.println(builds);
		  allBuilds.setBuilds(builds);
		  
		  return allBuilds;
	}
	
	@ApiOperation(value = "Get latestBuild details specific job in the local jenkins ", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })

	@RequestMapping(value = "/getLatestBuild/{jobName}", method = RequestMethod.GET)
	public BuildWithDetails getLatestBuild(@PathVariable("jobName") String jobName) {
		System.out.println("getAllBuilds  : " +ROOT_URI + "/job/"+jobName+"/lastBuild"+API_URL);
		ResponseEntity<BuildWithDetails> response = restTemplate.exchange(ROOT_URI + "/job/"+jobName+"/lastBuild"+API_URL,
				HttpMethod.GET, new HttpEntity<BuildWithDetails>(createHeaders("Kishore", "Kishore")), BuildWithDetails.class);
		
		
		return response.getBody();
	}
	
	@ApiOperation(value = "Trigger a build for specific job in the local jenkins ", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@RequestMapping(value = "/build/{jobName}", method = RequestMethod.POST)
	public String build(@PathVariable("jobName") String jobName) {
		System.out.println("getAllBuilds  : " +ROOT_URI + "/job/"+jobName+"/build?token=117295db43264acb81e011b151ea52a1ac");
		ResponseEntity<String> response = restTemplate.exchange(ROOT_URI + "/job/"+jobName+"/build?token=117295db43264acb81e011b151ea52a1ac",
				HttpMethod.POST, new HttpEntity<String>(createHeaders("Kishore", "117295db43264acb81e011b151ea52a1ac")), String.class);
		return response.getBody();
	}
	
	HttpHeaders createHeaders(String username, String password) {
		return new HttpHeaders() {
			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
				//set("token", "11388f4402888b6304f43828abd1e8ddcb");
			}
		};
	}
	
	HttpHeaders createPOSTHeaders(String username, String password) {
		return new HttpHeaders() {
			{
			
			}
		};
	}

	public List<Job> ServiceUnavalible(){
		/*Jobs jobs = new Jobs();
		jobs.setJobs();*/
		return Arrays.asList(new Job("Test1","url1"),new Job("Test1","Url2"));
	}
	
	 

}

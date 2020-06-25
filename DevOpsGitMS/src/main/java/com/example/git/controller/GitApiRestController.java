package com.example.git.controller;

import java.nio.charset.Charset;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "GitApiRestController", description = "REST Apis related to GIT!!!!", tags="GIT Operations")
@RestController
@RequestMapping("/devops/git")
public class GitApiRestController {

	@Value("${application.git.root.uri}")
	private String ROOT_URI;
	

	@Autowired
	private RestTemplate restTemplate;
	
	
	@ApiOperation(value = "Get list repos for user ", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })

	@RequestMapping(value = "/gitUser", method = RequestMethod.GET)
	public String gitUser() {
		System.out.println("gitUser  : " + ROOT_URI +"/user");
		ResponseEntity<String> response = restTemplate.exchange(ROOT_URI+"/user",
				HttpMethod.GET, new HttpEntity<String>(createHeaders("nanigrk@gmail.com", "Kishore@3875")), String.class);
		return response.getBody();
	}
	
	
	@ApiOperation(value = "Get list of repositories for that user", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })

	@RequestMapping(value = "getRepos/{user}", method = RequestMethod.GET)
	public String getRepos(@PathVariable("user") String user) {
		System.out.println("getRepos  : " + ROOT_URI +"/users/"+user+"/repos");
		ResponseEntity<String> response = restTemplate.exchange(ROOT_URI+"/users/"+user+"/repos",
				HttpMethod.GET, new HttpEntity<String>(createHeaders("nanigrk@gmail.com", "Kishore@3875")), String.class);
		return response.getBody();
	}
	
	@ApiOperation(value = "Get list of commits for repo ", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })

	@RequestMapping(value = "getCommits/{user}/{repoName}", method = RequestMethod.GET)
	public String getCommits(@PathVariable("repoName") String repoName,@PathVariable("user") String user) {
		System.out.println("getCommits  : " + ROOT_URI +"/repos/"+user+"/"+repoName+"/commits");
		ResponseEntity<String> response = restTemplate.exchange(ROOT_URI+"/repos/"+user+"/"+repoName+".com/commits",
				HttpMethod.GET, new HttpEntity<String>(createHeaders("nanigrk@gmail.com", "Kishore@3875")), String.class);
		return response.getBody();
	}
	
	
	@ApiOperation(value = "Get specific commit  form a repo ", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })

	@RequestMapping(value = "getCommits/{user}/{repoName}/{sha}", method = RequestMethod.GET)
	public String getCommit(@PathVariable("repoName") String repoName,@PathVariable("user") String user,@PathVariable("sha") String sha) {
		System.out.println("getCommit  : " + ROOT_URI +"/repos/"+user+"/"+repoName+"/commit/"+sha);
		ResponseEntity<String> response = restTemplate.exchange(ROOT_URI+"/repos/"+user+"/"+repoName+".com/commit/"+sha,
				HttpMethod.GET, new HttpEntity<String>(createHeaders("nanigrk@gmail.com", "Kishore@3875")), String.class);
		return response.getBody();
	}
	

	HttpHeaders createHeaders(String username, String password) {
		return new HttpHeaders() {
			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}

}

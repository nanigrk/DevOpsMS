package com.example.jira.controller;

import com.atlassian.jira.rest.client.api.domain.BasicVotes;
import com.atlassian.jira.rest.client.api.domain.Comment;
import com.atlassian.jira.rest.client.api.domain.Issue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Api(value = "JiraApiRestController", description = "REST Apis related to Jira!!!!", tags="JIRA Operations")
@RestController
@RequestMapping("/devops/jira")
public class JiraApiRestController {

	@Value("${application.jenkins.root.uri}")
	private String ROOT_URI;

	@Autowired
	private MyJiraClient myJiraClient;
	
	@ApiOperation(value = "Get issue by Key", response = Issue.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })

	@RequestMapping(value = "/issue", method = RequestMethod.GET)
	public Issue getIssue(@PathVariable("issueKey") String issueKey) {
		return myJiraClient.getIssue(issueKey);
	}
	
	@ApiOperation(value = "Vote for an issue")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })

	@RequestMapping(value = "/vote", method = RequestMethod.GET)
	public void vote(@PathVariable("issueKey") String issueKey) {
		Issue issue =  myJiraClient.getIssue(issueKey);
		myJiraClient.voteForAnIssue(issue);
	}
	
	@ApiOperation(value = "get total Votes for an issue", response = Integer.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })

	@RequestMapping(value = "/getVotes", method = RequestMethod.GET)
	public int getVotes(@PathVariable("issueKey") String issueKey) {
		BasicVotes votes =  myJiraClient.getIssue(issueKey).getVotes();
        return votes == null ? 0 : votes.getVotes();
	}
	
	@ApiOperation(value = "get total comments for an issue", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })

	@RequestMapping(value = "/comments", method = RequestMethod.GET)
	public  List<Comment> getComments(@PathVariable("issueKey") String issueKey) {
		 List<Comment> comments =  (List<Comment>) myJiraClient.getIssue(issueKey).getComments();
		 return StreamSupport.stream(comments.spliterator(), false)
		          .collect(Collectors.toList());
	}
	
	@ApiOperation(value = "add comment for an issue")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })

	@RequestMapping(value = "/addComments", method = RequestMethod.POST)
	public void postComments(@PathVariable("issueKey") String issueKey,
			@PathVariable("commentBody") String commentBody) {
		Issue issue =  myJiraClient.getIssue(issueKey);
		myJiraClient.addComment(issue,commentBody);
	}
	
	@ApiOperation(value = "delete issue by Key")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })

	@RequestMapping(value = "/deleteIssue", method = RequestMethod.DELETE)
	public void deleteIssue(@PathVariable("issueKey") String issueKey) {
		myJiraClient.deleteIssue(issueKey, true);
	}


}

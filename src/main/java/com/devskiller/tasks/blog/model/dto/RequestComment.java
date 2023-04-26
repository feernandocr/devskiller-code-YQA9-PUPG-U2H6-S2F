package com.devskiller.tasks.blog.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestComment {

	@JsonProperty("postId")
	private String postId;

	@JsonProperty("commentDto")
	private NewCommentDto commentDto;


	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public NewCommentDto getCommentDto() {
		return commentDto;
	}

	public void setCommentDto(NewCommentDto commentDto) {
		this.commentDto = commentDto;
	}
}

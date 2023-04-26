package com.devskiller.tasks.blog.rest;

import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.RequestComment;
import com.devskiller.tasks.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.devskiller.tasks.blog.model.dto.PostDto;
import com.devskiller.tasks.blog.service.PostService;

import java.util.List;

@Controller
@RestController
@RequestMapping("/posts")
public class PostController {

	private final PostService postService;
	private final CommentService commentService;

	public PostController(PostService postService, CommentService commentService) {
		this.postService = postService;
		this.commentService = commentService;
	}

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public PostDto getPost(@PathVariable String id) {

		return postService.getPost(id);
	}

	@PostMapping(value = "/addComment")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> addComment(@RequestBody RequestComment requestComment) {
		if (commentService.getCommentsForPost(requestComment.getPostId()).isEmpty()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("does not exist");
		}else{
			return ResponseEntity.status(HttpStatus.CREATED).body(commentService.addComment(requestComment.getPostId(), requestComment.getCommentDto()));
		}
	}

	@GetMapping(value = "/{id}/comments")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<CommentDto>> getComments(@PathVariable String id) {
		return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentsForPost(id)) ;
	}

	@GetMapping(value = "/posts/{postId}/comments")
	@ResponseStatus(HttpStatus.OK)
	public List<CommentDto> getCommentForPost(@PathVariable String postId){
		List<CommentDto> comments = commentService.getCommentsForPost(postId);
		return comments;
	}

}

package com.devskiller.tasks.blog.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devskiller.tasks.blog.model.Comment;
import com.devskiller.tasks.blog.model.Post;
import com.devskiller.tasks.blog.repository.CommentRepository;
import com.devskiller.tasks.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;

@Service
public class CommentService {

	private final PostRepository postRepository;
	private final CommentRepository commentRepository;

	public CommentService(PostRepository postRepository, CommentRepository commentRepository) {
		this.postRepository = postRepository;
		this.commentRepository = commentRepository;
	}
	/**
	 * Returns a list of all comments for a blog post with passed id.
	 *
	 * @param postId id of the post
	 * @return list of comments sorted by creation date descending - most recent first
	 */
	public List<CommentDto> getCommentsForPost(String postId) {
		List<Comment> comments = commentRepository.findByPostIdOrderByCreationDateDes(postId);
		List<CommentDto> commentDtos = (List<CommentDto>) comments.stream()
			.map(comment -> new CommentDto(comment.id(),comment.comment(),comment.author(),comment.creationDate()))
			.collect(Collectors.toList());
		return commentDtos;
	}

	/**
	 * Creates a new comment
	 *
	 * @param postId id of the post
	 * @param newCommentDto data of new comment
	 * @return id of the created comment
	 *
	 * @throws IllegalArgumentException if postId is null or there is no blog post for passed postId
	 */
	public String addComment(String postId, NewCommentDto newCommentDto) {
		Comment entityTemp = commentRepository.save(new Comment(postId, newCommentDto.content(), newCommentDto.author(), LocalDateTime.now()));
		return entityTemp.id();
	}
}

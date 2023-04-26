package com.devskiller.tasks.blog.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

public record Post(@Id String id, String title, String content, LocalDateTime creationDate, List<Comment> comments) {
}

package com.quizapp.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quizapp.model.Question;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizDto {
private Integer id;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("questions")
	private List<Question> questions;
}

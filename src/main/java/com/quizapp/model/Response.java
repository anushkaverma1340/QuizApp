package com.quizapp.model;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor // gives a constructor
public class Response {
	
	@Column(name="id")
	private Integer id;
	
	@Column(name="response")
	private String response;
	
	public Response() {
		super();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
}

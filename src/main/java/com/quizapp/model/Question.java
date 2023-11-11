package com.quizapp.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "questions") // for mysql db
public class Question {
	
	@Id // declaring primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
	@Column(name="id")
	private Integer id;
	
	@Column(name="question_title")
	private String questionTitle;
	
	@Column(name="option_1", columnDefinition = "VARCHAR(255)")
	private String option1=null;
	
	@Column(name="option_2", columnDefinition = "VARCHAR(255)")
	private String option2=null;
	
	@Column(name="option_3", columnDefinition = "VARCHAR(255)")
	private String option3=null;
	
	@Column(name="option_4", columnDefinition = "VARCHAR(255)")
	private String option4=null;
	
	@Column(name="right_answer")
	private String rightAnswer=null;
	
	@Column(name="difficulty_level")
	private String difficultyLevel=null;
	
	@Column(name="category")
	private String category=null;
	
//	@ManyToMany(mappedBy = "questions", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    private List<Quiz> quizzes;
	@ManyToMany(mappedBy = "questions", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Quiz> quizzes = new ArrayList<>(); // Default to an empty list
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getRightAnswer() {
		return rightAnswer;
	}
	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}
	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
    public List<Quiz> getQuizzes() {
        return quizzes;
    }
    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }
	
}
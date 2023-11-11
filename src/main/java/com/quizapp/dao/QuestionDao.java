package com.quizapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quizapp.dto.QuestionDto;
import com.quizapp.model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

	List<Question> findByCategory(String category); // as category is a part of the table, data jpa creates a method here

	@Query(value = "SELECT * FROM questions q Where q.category = :category ORDER BY RAND() LIMIT :numberOfQuestions", nativeQuery=true)
	List<Question> findRandomQuestionsByCategory(String category, int numberOfQuestions);

	void save(QuestionDto question);
	
}

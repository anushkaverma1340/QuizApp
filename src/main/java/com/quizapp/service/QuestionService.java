package com.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizapp.dao.QuestionDao;
import com.quizapp.dto.QuestionDto;
import com.quizapp.model.Question;

@Service
public class QuestionService {

	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<List<QuestionDto>> getAllQuestions() {
		try {
			// Getting questions from the database
			List<Question> questionsFromDb = questionDao.findAll();

			// Converting questionsFromDb to dto (QuestionDto)
			List<QuestionDto> questionsforClient = new ArrayList<>();
			for (Question question : questionsFromDb) {
				QuestionDto dto = new QuestionDto();
				dto.setId(question.getId());
				dto.setQuestionTitle(question.getQuestionTitle());
				dto.setOption1(question.getOption1());
				dto.setOption2(question.getOption2());
				dto.setOption3(question.getOption3());
				dto.setOption4(question.getOption4());
				dto.setRightAnswer(question.getRightAnswer());
				dto.setDifficultyLevel(question.getDifficultyLevel());
				dto.setCategory(question.getCategory());
				questionsforClient.add(dto);
			}

			// Returning the converted questions in the response with HTTP status 200 (OK)
			return new ResponseEntity<>(questionsforClient, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<QuestionDto>> getQuestionsByCategory(String category) {
		try {
			// get questions from database according to category
			List<Question> questionsFromDb = questionDao.findByCategory(category);

			// converting questionsFromDb to dto (QuestionDto)
			List<QuestionDto> questionsForClient = new ArrayList<>();
			for (Question question : questionsFromDb) {
				QuestionDto dto = new QuestionDto();
				dto.setId(question.getId());
				dto.setQuestionTitle(question.getQuestionTitle());
				dto.setOption1(question.getOption1());
				dto.setOption2(question.getOption2());
				dto.setOption3(question.getOption3());
				dto.setOption4(question.getOption4());
				dto.setRightAnswer(question.getRightAnswer());
				dto.setDifficultyLevel(question.getDifficultyLevel());
				dto.setCategory(question.getCategory());
				questionsForClient.add(dto);
			}

			// returning the converted questions in response object
			return new ResponseEntity<>(questionsForClient, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
	}

	public ResponseEntity<String> addQuestion(QuestionDto questionDto) {
		try {
			Question question = new Question();
			question.setId(questionDto.getId());
			question.setQuestionTitle(questionDto.getQuestionTitle());
			question.setOption1(questionDto.getOption1());
			question.setOption2(questionDto.getOption2());
			question.setOption3(questionDto.getOption3());
			question.setOption4(questionDto.getOption4());
			question.setRightAnswer(questionDto.getRightAnswer());
			question.setDifficultyLevel(questionDto.getDifficultyLevel());
			question.setCategory(questionDto.getCategory());

			// Save the Question entity using the QuestionDao
			questionDao.save(question);
			return new ResponseEntity<>("Question added", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Question not added", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> deleteQuestion(Integer id) {
		try {
			questionDao.deleteById(id);
			return new ResponseEntity<>("Question deleted", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Question not deleted", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> updateQuestion(Integer id, QuestionDto questionDto) {
		try {
			Optional<Question> questionToBeUpdated = questionDao.findById(id);
			if (questionToBeUpdated.isEmpty())
				return new ResponseEntity<>("Question not present in database", HttpStatus.NOT_FOUND);
			questionToBeUpdated.get().setQuestionTitle(questionDto.getQuestionTitle());
			questionToBeUpdated.get().setOption1(questionDto.getOption1());
			questionToBeUpdated.get().setOption2(questionDto.getOption2());
			questionToBeUpdated.get().setOption3(questionDto.getOption3());
			questionToBeUpdated.get().setOption4(questionDto.getOption4());
			questionToBeUpdated.get().setRightAnswer(questionDto.getRightAnswer());
			questionToBeUpdated.get().setDifficultyLevel(questionDto.getDifficultyLevel());
			questionToBeUpdated.get().setCategory(questionDto.getCategory());
			questionDao.save(questionToBeUpdated.get());
			return new ResponseEntity<>("Question updated", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Question not updated", HttpStatus.BAD_REQUEST);
	}

}

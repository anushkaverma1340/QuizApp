package com.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quizapp.dao.QuestionDao;
import com.quizapp.dao.QuizDao;
import com.quizapp.model.Question;
import com.quizapp.model.QuestionWrapper;
import com.quizapp.model.Quiz;
import com.quizapp.model.Response;

@Service
@Transactional
public class QuizService {
	
	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<String> createQuiz(String category, int numberOfQuestions, String title) {
		
		try {
			List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numberOfQuestions);
			
			Quiz quiz = new Quiz();
			quiz.setTitle(title);
			quiz.setQuestions(questions);
			quizDao.save(quiz);
			
			return new ResponseEntity<>("Quiz Created", HttpStatus.CREATED);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Quiz not created", HttpStatus.BAD_REQUEST);
		
	}
	
	@Transactional
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		
		try {
			// getting quiz by id
	        Optional<Quiz> quizOptional = quizDao.findById(id);
	        if (quizOptional.isPresent()) {
	            Quiz quiz = quizOptional.get();
	            // getting questions from obtained quiz
	            List<Question> questionsFromDB = quiz.getQuestions();
	        
	            // framing the questions to present to the user
	            List<QuestionWrapper> questionsForUser = new ArrayList<>();
	            for (Question q : questionsFromDB) {
	                QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
	                questionsForUser.add(qw);
	            }
	            
	            // returning
	            return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
	        } else {
	            // Quiz with the specified ID not found
	            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
	        }
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		
		try {
//			// getting quiz presented to user by id
//			Quiz quiz = quizDao.findById(id);
//			
//			// getting questions from obtained quiz
//			List<Question> questions = quiz.getQuestions();
//			
//			// getting the number of right answers
//			int numberOfRightAnswers = 0;
//			int i = 0;
//			for(Response response : responses) {
//				if(response.getResponse().equals(questions.get(i).getRightAnswer()))
//					numberOfRightAnswers++;
//				i++;
//			}
//			
//			// returning
//			return new ResponseEntity<>(numberOfRightAnswers, HttpStatus.OK);
			
			
			
			
			
			
			// getting quiz presented to user by id
	        Optional<Quiz> quizOptional = quizDao.findById(id);
	        if (quizOptional.isPresent()) {
	            Quiz quiz = quizOptional.get();
	            
	            // getting questions from obtained quiz
				List<Question> questions = quiz.getQuestions();
				
				// getting the number of right answers
				int numberOfRightAnswers = 0;
				int i = 0;
				for(Response response : responses) {
					if(response.getResponse().equals(questions.get(i).getRightAnswer())) 
						numberOfRightAnswers++;
					i++;
				}
	            
	            // returning
	            return new ResponseEntity<>(numberOfRightAnswers, HttpStatus.OK);
	        } else {
	            // Quiz with the specified ID not found
	            return new ResponseEntity<>(-1, HttpStatus.NOT_FOUND);
	        }
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(-1, HttpStatus.OK);
		
	}
}

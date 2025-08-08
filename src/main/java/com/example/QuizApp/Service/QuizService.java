package com.example.QuizApp.Service;

import com.example.QuizApp.Cpntroller.QuestionController;
import com.example.QuizApp.Model.Question;
import com.example.QuizApp.Model.QuestionWrapper;
import com.example.QuizApp.Model.Quiz;
import com.example.QuizApp.Model.Response;
import com.example.QuizApp.Repository.QuizRepo;
import com.example.QuizApp.Repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizRepo QuizRepo;
    @Autowired
    private Repo QuesRepo;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        try {
            // Fetch random questions by category
            List<Question> questions = QuesRepo.findRandomQuestionByCategory(category,numQ);

            // Check if enough questions are retrieved
            if (questions.isEmpty()) {
                return new ResponseEntity<>("Not enough questions available for the given category", HttpStatus.BAD_REQUEST);
            }
            Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        QuizRepo.save(quiz);

        return new ResponseEntity<>("succcess", HttpStatus.OK);


    }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("error occur", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {

            Optional<Quiz> quiz= QuizRepo.findById(id);
            if(quiz.isPresent()) {
                List<Question> s = quiz.get().getQuestions();
                List<QuestionWrapper> QW = new ArrayList<>();

                for (Question q : s) {
                    QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
                    QW.add(qw);
                }
                return new ResponseEntity<>(QW, HttpStatus.OK);

            }else {
               return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> res) {
        Optional<Quiz> quiz = QuizRepo.findById(id);
        List<Question> Ques=quiz.get().getQuestions();
        int i=0;
        int right = 0;
        for(Response r : res){
            if(r.getResponse().equals(Ques.get(i).getRightAnswer())){
                right++;}
            i++;
        }
         return  new ResponseEntity<>(right,HttpStatus.OK);

    }
}

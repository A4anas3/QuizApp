package com.example.QuizApp.Cpntroller;

import com.example.QuizApp.Model.Question;
import com.example.QuizApp.Model.QuestionWrapper;
import com.example.QuizApp.Model.Quiz;
import com.example.QuizApp.Model.Response;
import com.example.QuizApp.Service.QuizService;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Quiz")
public class QuizController {
    @Autowired
    private QuizService QService;


    @PostMapping("create")
    public ResponseEntity<String> create(@RequestParam String category, @RequestParam int numQ,@RequestParam String title ){
        return QService.createQuiz(category,numQ,title);



    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id){

            return QService.getQuizQuestion(id);

    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id ,@RequestBody List<Response> res){
        return  QService.calculateResult(id,res);

    }


}

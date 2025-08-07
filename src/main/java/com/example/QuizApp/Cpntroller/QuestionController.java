package com.example.QuizApp.Cpntroller;

import com.example.QuizApp.Model.Question;
import com.example.QuizApp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Question")
public class QuestionController {
    @Autowired
    private QuestionService Qservice;
    @GetMapping("/allQuestion")
    public List<Question> allQuestion(){
        return Qservice.getAllQuestion() ;
    }
}

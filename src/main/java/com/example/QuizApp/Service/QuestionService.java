package com.example.QuizApp.Service;

import com.example.QuizApp.Model.Question;
import com.example.QuizApp.Repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private Repo repo;

    public List<Question> getAllQuestion() {

        return repo.findAll();
    }

    public List<Question> getQuestionByCategory(String question) {
        return repo.findByCategory(question);
    }



    public String addData(Question question) {
        repo.save(question);
        return "data save successfully";
    }

    public boolean delete(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}

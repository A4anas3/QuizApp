package com.example.QuizApp.Repository;

import com.example.QuizApp.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Repo extends JpaRepository<Question,Integer> {
     @Query("SELECT q FROM Question q WHERE q.category = :category")
    List<Question> findByCategory(String category);


     @Query(value = "SELECT * FROM question q WHERE  q.category=:category ORDER BY RANDOM() LIMIT 5",nativeQuery = true)
    List<Question> findRandomQuestionByCategory(String category, int numQ);
}

package com.example.QuizApp.Cpntroller;

import com.example.QuizApp.Model.Question;
import com.example.QuizApp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Question")
public class QuestionController {
    @Autowired
    private QuestionService Qservice;
    @GetMapping("/allQuestion")
    public ResponseEntity<List<Question>> allQuestion(){
        try{
            return new ResponseEntity<>(Qservice.getAllQuestion(),HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }


@GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
    try {
        List<Question> l = Qservice.getQuestionByCategory(category);
        return  ResponseEntity.ok(l);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    }

     @PostMapping("add")
    public ResponseEntity<String> addData(@RequestBody Question question){
      ;
         try {

             String s =  Qservice.addData(question);
             return  ResponseEntity.ok(s);
         } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
         }
     }

     @GetMapping("delete/{del}")
    public ResponseEntity<String> delete(@PathVariable("del") int id){
        boolean deleted= Qservice.delete(id);
         if (deleted) {
             return ResponseEntity.ok("✅ Successfully deleted");
         } else {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ ID not found: " + id);
         }
     }
}

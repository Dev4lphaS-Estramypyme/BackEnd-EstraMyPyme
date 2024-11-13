/* package dev4lphas.estramypyme.estramypyme_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev4lphas.estramypyme.estramypyme_backend.model.Answer;
import dev4lphas.estramypyme.estramypyme_backend.service.AnswerService;

@RestController
@RequestMapping("/answers")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @GetMapping("/test/{testId}/question/{questionId}")
    public ResponseEntity<List<Answer>> getAnswersByTestIdAndQuestionId(@PathVariable Long testId, @PathVariable Long questionId) {
        List<Answer> answers = answerService.findByTestIdAndQuestionId(testId, questionId);
        return ResponseEntity.ok(answers);
    }

    @PostMapping
    public ResponseEntity<Answer> createAnswer(@RequestBody Answer answer) {
        Answer createdAnswer = answerService.save(answer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAnswer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long id) {
        answerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

 */
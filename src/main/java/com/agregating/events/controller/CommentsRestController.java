package com.agregating.events.controller;

import com.agregating.events.domain.Comment;
import com.agregating.events.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class CommentsRestController {

    private CommentService service;

    @Autowired
    public CommentsRestController(CommentService service) {
        this.service = service;
    }

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getAllComments(){
        List<Comment> comments = service.findAllComments();

        if (comments == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }
    }


    @PostMapping("/comments")
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment){
        List<Comment> comments = service.findAllComments();
        for (Comment singleComment : comments){
            if (singleComment.equals(comment)){
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        }

        Comment upcomingComment =  service.saveComment(comment);
        return new ResponseEntity<>(upcomingComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") long id){
        if (service.deleteComment(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

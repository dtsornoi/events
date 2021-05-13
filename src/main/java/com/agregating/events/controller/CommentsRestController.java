package com.agregating.events.controller;

import com.agregating.events.domain.Comment;
import com.agregating.events.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Rest controller for Comments
 *
 * @author Dmitri Tšornõi
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class CommentsRestController {

    private final CommentService service;

    @Autowired
    public CommentsRestController(CommentService service) {
        this.service = service;
    }

    /**
     * GET: <code>/comments</code>
     * @return List of all comments stored in DB
     */
    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getAllComments(){
        List<Comment> comments = service.findAllComments();

        if (comments == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }
    }

    /**
     * POST: <code>/comments</code>
     * @param comment Comment.class from client side form to be saved in DB
     * @return created new Comment
     */
    @PostMapping("/comments")
    public ResponseEntity<Comment> saveComment(@Valid @RequestBody Comment comment){
        List<Comment> comments = service.findAllComments();

        if(comments.isEmpty()){
            return new ResponseEntity<>(service.saveComment(comment), HttpStatus.CREATED);
        }else{
            for (Comment singleComment : comments){
                if (singleComment.getTitle().equals(comment.getTitle())
                        && singleComment.getComment().equals(comment.getComment())){
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                }
            }

        }
        return new ResponseEntity<>(service.saveComment(comment), HttpStatus.CREATED);
    }

    /**
     * DELETE: <code>/comments/id</code>
     * @param id of the Comment.class to be deleted from DB
     * @return ResponseEntity.ok if Comment was deleted or ResponseEntity.notFound if does not exist in DB
     */
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") long id){
        if (service.deleteComment(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

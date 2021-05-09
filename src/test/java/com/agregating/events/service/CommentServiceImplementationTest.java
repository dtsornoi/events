package com.agregating.events.service;

import com.agregating.events.domain.Comment;
import com.agregating.events.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplementationTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImplementation commentService;

    private Comment comment;

    @BeforeEach
    void init(){
        comment = new Comment();
        comment.setId(1L);
        comment.setTitle("comment");
        comment.setComment("comment text");
    }

    @Test
    @DisplayName("When findAllComments() is called, should return all comments")
    void whenFindAllComments_returnListOfComments() {
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment);

        Mockito.when(commentService.findAllComments()).thenReturn(commentList);

        List<Comment> result = commentService.findAllComments();

        assertEquals(commentList, result);
        assertNotNull(result);
    }

    @Test
    @DisplayName("When saveComment() called, should return saved Comment")
    void whenSaveCommentCalled_shouldReturnSavedComment() {
        Mockito.when(commentService.saveComment(comment)).thenReturn(comment);

        Comment result = commentService.saveComment(comment);

        assertEquals(comment, result);
        assertNotNull(result);
    }
}
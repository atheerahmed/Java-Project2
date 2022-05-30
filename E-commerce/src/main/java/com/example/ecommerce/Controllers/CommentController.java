package com.example.ecommerce.Controllers;

import com.example.ecommerce.Models.Comment;
import com.example.ecommerce.Services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
@RestController
@RequestMapping("api/v1/Comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @GetMapping
    public ResponseEntity<ArrayList<Comment>> getComments(){
        if (commentService.getComments()!=null)
            return ResponseEntity.status(200).body(commentService.getComments());
        return ResponseEntity.status(400).body(commentService.getComments());




    }

}

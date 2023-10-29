package com.example.controller;

import com.example.dao.CommentRepo;
import com.example.model.Comments;
import com.example.model.Post;
import com.example.model.User;
import com.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    @Autowired
    CommentService service;

    @PostMapping("add-comment")
    public String addcomment(@RequestParam int userid, @RequestParam int postid, @RequestBody Comments comments){
        Boolean issave = service.save(comments,userid,postid);
        if(issave){
            return "added";
        }
        return "id doesn't exist";
    }
}

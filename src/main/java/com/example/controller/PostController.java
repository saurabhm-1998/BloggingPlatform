package com.example.controller;

import com.example.dao.UserRepo;
import com.example.dto.PostDto;
import com.example.model.Post;
import com.example.model.User;
import com.example.service.PostService;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class PostController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    PostService postService;

    @PostMapping("/create-post")
    public ResponseEntity<String> savePost(@Valid @RequestBody PostDto postdata){
        Post post = setPost(postdata);
        if(post != null) {
            int postid = postService.save(post);
            return new ResponseEntity<>("post saved id " + postid, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("userid doesn't exist", HttpStatus.NOT_FOUND);

    }

    private Post setPost(PostDto postdata) {
        JSONObject jsonObject = new JSONObject(postdata);
        User user = null;
        int userid = postdata.getUserId();
        if(userRepo.findById(userid).isPresent()){
            user = userRepo.findById(userid).get();
        }else {
            return null;
        }
        Post post = new Post();
        post.setUser(user);
        post.setPostData(postdata.getPostData());
        return post;
    }
}

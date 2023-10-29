package com.example.service;

import com.example.dao.CommentRepo;
import com.example.dao.PostRepo;
import com.example.dao.UserRepo;
import com.example.model.Comments;
import com.example.model.Post;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {


    @Autowired
    CommentRepo commentRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    PostRepo postRepo;
    public Boolean save(Comments comments,int userid,int postid) {
        if(userRepo.findById(userid).isPresent() && postRepo.findById(postid).isPresent()){
            User user = userRepo.findById(userid).get();
            Post post = postRepo.findById(postid).get();
            comments.setUser(user);
            comments.setPost(post);
            commentRepo.save(comments);
        }else{
            return false;
        }
        return true;
    }
}

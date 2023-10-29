package com.example.controller;

import com.example.dao.FollowRepo;
import com.example.dao.UserRepo;
import com.example.dto.FollowDto;
import com.example.model.Follow;
import com.example.model.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FollowController {
    @Autowired
    FollowRepo followRepo;
    @Autowired
    UserRepo userRepo;

    @PostMapping("/follow-user")
    public ResponseEntity<String> followUser(@RequestBody FollowDto followDto) {
        Follow follow = new Follow();
        if (userRepo.findById(followDto.getFollowerId()).isPresent() && userRepo.findById(followDto.getFollowingId()).isPresent()) {
            User followeruser = userRepo.findById(followDto.getFollowerId()).get();
            User followinguser = userRepo.findById(followDto.getFollowingId()).get();
            follow.setFollowingId(followinguser);
            follow.setFollowerId(followeruser);
            followRepo.save(follow);
        } else {
            return new ResponseEntity<>("No such id exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }
    @GetMapping("search-follower/{userid}")
    public ResponseEntity<String> getfollowers(@PathVariable int userid){
        List<Follow> followList = followRepo.findAll();
        JSONObject json = new JSONObject();
        if(userRepo.findById(userid).isPresent()){
            for (Follow follow:followList){
                if (follow.getFollowerId().getUserId() == userid){
                    json.put("followedBy",follow.getFollowingId().getFullName());
                }
            }
        }else{
            return new ResponseEntity<>("userid doesn't exits",HttpStatus.NOT_FOUND);
        }
        if(json.isEmpty()){
            return new ResponseEntity<>("No user followed by this user id",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(json.toString(),HttpStatus.OK);
    }
}

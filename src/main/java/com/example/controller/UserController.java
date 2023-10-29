package com.example.controller;

import com.example.dao.UserRepo;
import com.example.model.User;
import com.example.service.UserService;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepo userRepo;
    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@Valid @RequestBody User requser){
        User olduser = userRepo.findByUserName(requser.getUserName());
        if(olduser != null){
            return new ResponseEntity<>("username already exist",HttpStatus.BAD_REQUEST);
        }
            int id = userService.add(requser);
            return new ResponseEntity<>("user created " + id, HttpStatus.CREATED);

    }
    @GetMapping("get-all-users")
    public List<User> getAll(){
        return userService.getAll();
    }
    @GetMapping("search-post/{userid}")
    public ResponseEntity<String> readPost(@PathVariable int userid){
        JSONObject jsonObject = userService.readPost(userid);
        if(!jsonObject.has("Error")){
            return new ResponseEntity<>(jsonObject.toString(),HttpStatus.OK);
        }
        return new ResponseEntity<>(jsonObject.toString(),HttpStatus.NOT_FOUND);
    }
    @PutMapping("/update-post")
    public String updatePost(@RequestParam int userid,@RequestParam int postid,@RequestBody String post){
        Boolean isupdate = userService.updatePost(userid,postid,post);
        if (isupdate){
            return "post updated";
        }
        return "No such user create this post";
    }
    @DeleteMapping("/delete-post")
    public String updatePost(@RequestParam int userid,@RequestParam int postid){
     Boolean isdelete = userService.deletePost(userid,postid);
        if (isdelete){
            return "post deleted";
        }
        return "No such user create this post";
    }
    @GetMapping("read-comment")
    public ResponseEntity<String> readComment(@RequestParam int userid,@RequestParam int postid){
        JSONObject json = userService.readComment(userid,postid);
        if(json.isEmpty()){
            return new ResponseEntity<>("No such post created by user",HttpStatus.NOT_FOUND);
        }else if(json.has("Error")){
            return new ResponseEntity<>(json.toString(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(json.toString(),HttpStatus.OK);
    }

}

package com.example.service;

import com.example.dao.CommentRepo;
import com.example.dao.PostRepo;
import com.example.dao.UserRepo;
import com.example.model.Comments;
import com.example.model.Post;
import com.example.model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    PostRepo postRepo;
    @Autowired
    CommentRepo commentRepo;

    public int add(User user) {
        User user1 = userRepo.save(user);
        return user.getUserId();
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public JSONObject readPost(int userid) {
        JSONObject json = new JSONObject();
        if(userRepo.findById(userid).isPresent()){
            User user = userRepo.findById(userid).get();
            json.put("name",user.getFullName());
            JSONArray array = new JSONArray();
            List<Post> postList = postRepo.findAll();
            for (Post post:postList) {
                if (post.getUser().getUserId() == userid) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("postId",post.getPostId());
                    jsonObject.put("postData",post.getPostData());
                    jsonObject.put("createdDate",post.getCreatedDate());
                    array.put(jsonObject);
                }

            }
            json.put("posts",array);
        }else{
            json.put("Error","user id doesn't exist");
        }
        return json;
    }

    public Boolean updatePost(int userid, int postid,String post) {
        if(postRepo.findById(postid).isPresent() && userRepo.findById(userid).isPresent()){
            Post oldpost = postRepo.findById(postid).get();
            if(oldpost.getUser().getUserId() == userid) {
                User user = userRepo.findById(userid).get();
                JSONObject json = new JSONObject(post);
                oldpost.setUser(user);
                oldpost.setPostData(json.getString("postData"));
                Timestamp updatetime = new Timestamp(System.currentTimeMillis());
                oldpost.setUpdatedDate(updatetime);
                postRepo.save(oldpost);
            }else {
                return false;
            }
        }else{
            return false;
        }
        return true;
    }

    public Boolean deletePost(int userid, int postid) {
        if(postRepo.findById(postid).isPresent() && userRepo.findById(userid).isPresent()) {
            Post oldpost = postRepo.findById(postid).get();
            if(oldpost.getUser().getUserId() == userid) {
               postRepo.deleteById(postid);
            }else {
                return false;
            }
        }else {
            return false;
        }
           return true;
        }

    public JSONObject readComment(int userid, int postid) {
       JSONObject json = new JSONObject();
       if(userRepo.findById(userid).isPresent() && postRepo.findById(postid).isPresent()){
           List<Comments> commentsList = commentRepo.findAll();

           JSONArray array = new JSONArray();
           for (Comments comments: commentsList) {
               if(comments.getUser().getUserId() == userid && comments.getPost().getPostId() == postid){
                   JSONObject response = new JSONObject();
                   response.put("commment",comments.getContent());
                   response.put("commentBy",comments.getUser().getFullName());
                   response.put("whichpost",comments.getPost().getPostId());
                   response.put("postData",comments.getPost().getPostData());
                   array.put(response);
               }

           }
           json.put("CommentDetaials",array);
       }else{
           json.put("Error","id doesn't exist");
       }
       return json;
    }

}

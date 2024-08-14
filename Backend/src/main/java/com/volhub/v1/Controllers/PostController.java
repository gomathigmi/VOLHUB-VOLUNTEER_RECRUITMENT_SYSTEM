package com.volhub.v1.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.volhub.v1.Modals.Post.PostModel;
import com.volhub.v1.Services.Post.PostService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "*")
@RequestMapping("/post")
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public PostModel postMethodName(@RequestBody PostModel post) {

        return postService.createPost(post);
    }

    @GetMapping("/getall")
    public List<PostModel> getAllPost() {
        return postService.getAllPost();
    }

}

package com.volhub.v1.Services.Post;

import java.util.List;

import com.volhub.v1.Modals.Post.PostModel;

public interface PostService {

    public PostModel createPost(PostModel post);

    public List<PostModel> getAllPost();


}

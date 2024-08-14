package com.volhub.v1.Services.Post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volhub.v1.Modals.Post.PostModel;
import com.volhub.v1.Repositories.Postrepo;

@Service
public class PostServiceImplements implements PostService {

    @Autowired
    private Postrepo postrepo;

    public PostModel createPost(PostModel post) {

        
            return  postrepo.save(post);
            
        
        

    }

    public List<PostModel> getAllPost()
    {
        return postrepo.findAll();
    }

   

}

package com.example.ecommerce.Services;

import com.example.ecommerce.Models.Cart;
import com.example.ecommerce.Models.Comment;
import com.example.ecommerce.Models.Product;
import com.example.ecommerce.Models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
@RequiredArgsConstructor
public class CommentService {
    private final ProductService productService;
    private final UserService userservice;


    private final ArrayList<Comment> comments=new ArrayList<>();

    public ArrayList<Comment> getComments(){
        return comments;
    }


}

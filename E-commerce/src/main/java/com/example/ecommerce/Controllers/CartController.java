package com.example.ecommerce.Controllers;

import com.example.ecommerce.Models.Api;
import com.example.ecommerce.Models.Cart;
import com.example.ecommerce.Models.Product;
import com.example.ecommerce.Models.User;
import com.example.ecommerce.Services.CartService;
import com.example.ecommerce.Services.ProductService;
import com.example.ecommerce.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartservice;

    @GetMapping
    public ResponseEntity<ArrayList<Cart>> getCarts(){
        if (cartservice.getCart()!=null)
            return ResponseEntity.status(200).body(cartservice.getCart());

        return ResponseEntity.status(400).body(cartservice.getCart());
            }



}




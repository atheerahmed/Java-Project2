package com.example.ecommerce.Controllers;

import com.example.ecommerce.Models.Api;
import com.example.ecommerce.Models.Product;
import com.example.ecommerce.Services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Product")
public class ProductController {
private final ProductService productService;
    @GetMapping
    public ResponseEntity<ArrayList<Product>> getProducts(){
        if (productService.getProduct()!=null)
            return ResponseEntity.status(200).body(productService.getProduct());
        return ResponseEntity.status(400).body(productService.getProduct());




    }

    @PostMapping
    public ResponseEntity<Api> addProduct(@RequestBody @Valid Product product , Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));

        if (productService.isAdd(product))
            return ResponseEntity.status(200).body(new Api("Added new product",200));

        return ResponseEntity.status(400).body(new Api("This product is already exist",400));

    }


    @PutMapping
    public ResponseEntity<Api> updateProduct(@RequestBody @Valid Product product , Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        if (productService.isUpdate(product))
            return ResponseEntity.status(200).body(new Api("Update the product",200));

        return ResponseEntity.status(400).body(new Api("This product is not exist",400));

    }


    @DeleteMapping
    public ResponseEntity<Api> deleteProduct(@RequestBody @Valid Product product , Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        if (productService.isDelete(product))
            return ResponseEntity.status(200).body(new Api("The product Deleted",200));

        return ResponseEntity.status(400).body(new Api("This product is not exist",400));

    }
}

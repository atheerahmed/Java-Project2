package com.example.ecommerce.Controllers;

import com.example.ecommerce.Models.Api;
import com.example.ecommerce.Models.Category;
import com.example.ecommerce.Services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    @GetMapping
    public ResponseEntity<ArrayList<Category>> getCategory(){
        if (categoryService.getCategories()!=null)
            return ResponseEntity.status(200).body(categoryService.getCategories());
        return ResponseEntity.status(400).body(categoryService.getCategories());




    }

    @PostMapping
    public ResponseEntity<Api> addCategory(@RequestBody @Valid Category category , Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));

        if (categoryService.isAdd(category))
            return ResponseEntity.status(200).body(new Api("Added new category",200));

        return ResponseEntity.status(400).body(new Api("This category is already exist",400));

    }


    @PutMapping
    public ResponseEntity<Api> updateCategory(@RequestBody @Valid Category category , Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        if (categoryService.isUpdate(category))
            return ResponseEntity.status(200).body(new Api("Update the category",200));

        return ResponseEntity.status(400).body(new Api("This category is not exist",400));

    }


    @DeleteMapping
    public ResponseEntity<Api> deleteCategory(@RequestBody @Valid Category category , Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        if (categoryService.isDelete(category))
            return ResponseEntity.status(200).body(new Api("The category Deleted",200));

        return ResponseEntity.status(400).body(new Api("This category is not exist",400));

    }
}

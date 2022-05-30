package com.example.ecommerce.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @NotEmpty(message = " 2categoryID" )
    @Size(min=3,message = " ID ID have to be 3 character long ")
    private String Id;
    @NotEmpty(message = " categoryID" )
    @Size(min=3,message = " Category ID have to be 3 character long ")
    private String categoryID  ;
    @NotEmpty(message = " Name" )
    @Length(min=6,message = " Name have to be 3 Length long ")
    private String name  ;
    @NotNull(message = "price must be positive")
    @Positive
    private Integer price  ;
    private ArrayList<Comment> comments=new ArrayList<>() ;

    public void addComment(Comment comment) {

        comments.add(comment);
    }

    public Product(String id, String categoryID, String name, Integer price) {
        Id = id;
        this.categoryID = categoryID;
        this.name = name;
        this.price = price;
    }
}

package com.example.ecommerce.Models;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
@Data
@AllArgsConstructor
public class PurchaseHistory {

    @NotEmpty
    @Size(min=3,message = " ID have to be 3 character long ")
    private String Id;
    @NotEmpty
    @Size(min=3,message = " User ID have to be 3 character long ")
    private String userid ;
    @NotEmpty
    @Size(min=3,message = " Product ID have to be 3 character long ")
    private String productid   ;
    @NotEmpty
    @Positive
    private Integer price  ;
}

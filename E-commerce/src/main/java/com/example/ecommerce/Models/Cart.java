package com.example.ecommerce.Models;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
@Data
public class Cart {
@NotEmpty
@Size(min=3,message = " ID have to be 3 character long ")
    private String ID;
    @NotEmpty
    @Size(min=3,message = " User ID have to be 3 character long ")
    private String userid;
    private ArrayList< Product > products = new ArrayList<>();


    public Cart(String id, String userid) {
        this.ID = id;
        this.userid = userid;
    }

    public void addProduct(Product product) {
        products.add(product);
    }
    public void removeProduct(Product product) {
        products.remove(product);
    }
    public String findProduct(String productid){
        for (Product product:products) {
            if (product.getId().equals(productid))
                return product.getId();
        }
        return null;
    }


    public Integer getPrices(ArrayList<Product> products) {
        Integer newprice=0;
        for (Product product:products) {
            newprice+=product.getPrice();

        }
        return newprice;


    }
}

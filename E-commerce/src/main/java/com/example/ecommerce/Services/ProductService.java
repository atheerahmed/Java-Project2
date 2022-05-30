package com.example.ecommerce.Services;

import com.example.ecommerce.Models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
@RequiredArgsConstructor
public class ProductService {
   private final UserService userservice;
   private final PurchaseHistoryService purchaseHistoryService;
    private ArrayList<Product> products=new ArrayList<>();

    public ArrayList<Product> getProduct(){
        return products;
    }

    public boolean isAdd(Product product) {
        return products.add(product);
    }

    public boolean isUpdate(Product product) {
        for (int i = 0; i < products.size() ; i++) {
            if (products.get(i).getId().equals(product.getId()))
                products.set(i,product);
            return true;
        }
        return false;
    }

    public boolean isDelete(Product product) {
        return products.remove(product);

    }


    public Product getProductByid(String productid) {
        for (Product product:products ) {
            if (productid.equals(product.getId()))
                return product;
        }
        return null;
    }
    public Integer addComment(String userid, String productid, Comment comment) {
        Product product = getProductByid(productid);
        User user=userservice.getUserByid(userid);
        if(product == null )
            return -1;
        if(user == null )
            return -2;
        if (!purchaseHistoryService.check(userid,productid))
            return -3;
        //if Product is exits
        for (Product product1:products) {
            if (product1.getId().equals(productid))
                product1.addComment(comment);
            return 1;
        }

return -4;

    }


    public ArrayList<Comment> getAllcomments(String productid) {
        ArrayList<Comment>comments=new ArrayList<>();
        Product product=getProductByid(productid);
        for (Comment comment:product.getComments()) {
                    comments.add(comment);
            }

        return comments;
    }

    public ArrayList<Product> getfivestar() {
        ArrayList<Product>products1=new ArrayList<>();
        for (Product product:products){
           for (Comment comment:product.getComments()) {
                if (comment.getRate()==5){
                    products1.add(product);
                    break;
            }
          }
        }

        return products1;
    }
}

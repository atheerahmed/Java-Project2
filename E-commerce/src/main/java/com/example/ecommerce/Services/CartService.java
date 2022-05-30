package com.example.ecommerce.Services;

import com.example.ecommerce.Models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private final UserService userservice;
    private final MerchantStockService merchantStock;
    private final PurchaseHistoryService purchaseHistoryService;

    ArrayList<Cart>carts=new ArrayList<>();

    public ArrayList<Cart> getCart(){
        return carts;
    }

    public Integer addCart(String userid, String productid) {

        Product product = productService.getProductByid(productid);
        User user=userservice.getUserByid(userid);
        if (product == null )
            return -1;


        if (user == null )
            return 0;

        //if user is exits
        for (Cart cart:carts) {
            if (cart.getUserid().equals(userid))
                cart.addProduct(product);
        }
         //if user is new

        Cart cart= new Cart(getID(),userid);
       cart.addProduct(product);
            carts.add(cart);
        return 1;

    }
    public boolean removeCart(String userid, String productid) {
        Product product = productService.getProductByid(productid);
        if (product==null)
            return false;
        Cart cart= getCart(userid,productid);
        cart.removeProduct(product);
        return true;


    }

    private Cart getCart(String userid, String productid) {
        for (Cart cart:carts) {
            if (cart.getUserid().equals(userid)&&cart.findProduct(productid).equals(productid))
                return cart;
        }
        return null;
    }

    //Create unique ID
    private String getID() {
     String uniqueID = UUID.randomUUID().toString();
     return uniqueID;
    }



    public Integer buy(Cart cart) {
        Integer Newprice=cart.getPrices(cart.getProducts());
        User user=userservice.getUserByid(cart.getUserid());
        Boolean inStock=merchantStock.inStock(cart.getProducts());
        Integer Oldbalance=user.getBalance();
        if (Newprice>Oldbalance)
            return -1;
        if (!inStock)
            return 0;
        user.setBalance(Oldbalance-Newprice);
        for (Product product:cart.getProducts() ) {
            purchaseHistoryService.addHistory(getID(), cart.getUserid(), product.getId(), Newprice);

        }

        return 2;
    }
}

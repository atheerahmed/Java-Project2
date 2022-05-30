package com.example.ecommerce.Controllers;

import com.example.ecommerce.Models.*;
import com.example.ecommerce.Services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final CartService cartservice;
    private final ProductService productService;
    private final UserService userservice;
    private final MerchantService merchantservice;
    private final MerchantStockService merchantStockService;
    private final CommentService commentService;
    private final PurchaseHistoryService purchaseHistoryService;



    @GetMapping
    public ResponseEntity<ArrayList<User>> getUser(){
        if (userservice.getUser()!=null)
        return ResponseEntity.status(200).body(userservice.getUser());
        return ResponseEntity.status(400).body(userservice.getUser());




    }
    @PostMapping
    public ResponseEntity<Api> addUser(@RequestBody @Valid User users , Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));

        if (userservice.isAdd(users))
            return ResponseEntity.status(200).body(new Api("Added new user",200));

        return ResponseEntity.status(400).body(new Api("This user is already exist",400));

    }
    @PutMapping
    public ResponseEntity<Api> updateUser(@RequestBody @Valid User user , Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        if (userservice.isUpdate(user))
            return ResponseEntity.status(200).body(new Api("Update the user",200));

        return ResponseEntity.status(400).body(new Api("This user is not exist",400));

    }
    @DeleteMapping
    public ResponseEntity<Api> deleteUser(@RequestBody @Valid User user , Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        if (userservice.isDelete(user))
            return ResponseEntity.status(200).body(new Api("The user Deleted",200));

        return ResponseEntity.status(400).body(new Api("This user is not exist",400));

    }
    @PostMapping("/addCart/{userid}/{productid}")
    public ResponseEntity<Api> addtoCart(@PathVariable String userid, @PathVariable String productid) {

        Integer addCart=cartservice.addCart(userid, productid);

        if (addCart == -1 )
            return ResponseEntity.status(400).body(new Api("This product is not exist", 400));


        if (addCart == 0 )
            return ResponseEntity.status(400).body(new Api("This user is not exist", 400));



        cartservice.addCart(userid, productid);
        return ResponseEntity.status(200).body(new Api("Added new product to cart", 200));

    }
    @DeleteMapping("/remove/{userid}/{productid}")
    public ResponseEntity<Api> DeletefromCart(@PathVariable String userid, @PathVariable String productid) {
        Product product = productService.getProductByid(productid);
        User user = userservice.getUserByid(userid);

        if (product == null )
            return ResponseEntity.status(400).body(new Api("This product is not exist", 400));


        if (user == null )
            return ResponseEntity.status(400).body(new Api("This user is not exist", 400));



        cartservice.removeCart(userid, productid);
        return ResponseEntity.status(200).body(new Api("Remove  product", 200));

    }
    @PostMapping("/addMS/{productid}/{merchantid}/{stock}")
    public ResponseEntity<Api> addtoMerchantStock(@PathVariable String productid ,@PathVariable String merchantid ,@PathVariable Integer stock) {
        Product product = productService.getProductByid(productid);
        Merchant merchant = merchantservice.getMerchantByid(merchantid);

        if (product == null )
            return ResponseEntity.status(400).body(new Api("This product is not exist", 400));


        if (merchant == null )
            return ResponseEntity.status(400).body(new Api("This merchant is not exist", 400));



        merchantStockService.addProduct(merchantid, productid,stock);
        return ResponseEntity.status(200).body(new Api("Added new product", 200));

    }
    @PostMapping("/buy/{userid}/{productid}/{merchantid}")
    public ResponseEntity<Api> BuyWithoutCart(@PathVariable String userid ,@PathVariable String productid,@PathVariable String merchantid) {
        Product product = productService.getProductByid(productid);
        Merchant merchant = merchantservice.getMerchantByid(merchantid);
        User user=userservice.getUserByid(userid);

        if (product == null )
            return ResponseEntity.status(400).body(new Api("This product is not exist", 400));


        if (merchant == null )
            return ResponseEntity.status(400).body(new Api("This merchant is not exist", 400));

        merchantStockService.getMerchantStock(product,merchant,user);

        return ResponseEntity.status(200).body(new Api("Buying succeeded", 200));

    }
    @PostMapping("/buycart")
    public ResponseEntity<Api> BuyWithCart(@RequestBody @Valid Cart cart, Errors errors){
       if(errors.hasErrors())
           return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));

       Integer ByCart= cartservice.buy(cart);
        if (ByCart==-1 )
            return ResponseEntity.status(400).body(new Api("The balance less than price", 400));

        if (ByCart==0)
            return ResponseEntity.status(400).body(new Api("This product is not exist in the stock", 400));


        return ResponseEntity.status(200).body(new Api("Buying with cart succeeded", 200));

    }
    @PostMapping("/comment/{userid}/{productid}/")
    public ResponseEntity<Api> buy(@PathVariable String userid ,@PathVariable String productid ,@RequestBody @Valid Comment comment){
        Integer addcomment=productService.addComment(userid, productid,comment);

        if(addcomment == -1 )
            return ResponseEntity.status(400).body(new Api("This product is not exist", 400));
        if(addcomment == -2 )
            return ResponseEntity.status(400).body(new Api("This user is not exist", 400));
        if (addcomment==-3)
            return ResponseEntity.status(400).body(new Api("This user didn't purchase this product", 400));
       if (addcomment==1)
           return ResponseEntity.status(200).body(new Api("Added new comment", 200));

           return ResponseEntity.status(400).body(new Api("This comment did add", 400));



    }
    @GetMapping("/getcomments/{productid}")
    public ResponseEntity<ArrayList<Comment>> getcomments(@PathVariable String productid){
        ArrayList<Comment> comments=productService.getAllcomments(productid);
        return ResponseEntity.status(200).body(comments);
    }

    @GetMapping("/getproduct")
    public ResponseEntity<ArrayList<Product>> getfivestar(){
        ArrayList<Product> products=productService.getfivestar();
        return ResponseEntity.status(200).body(products);
    }







}




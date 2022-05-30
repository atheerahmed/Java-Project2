package com.example.ecommerce.Controllers;

import com.example.ecommerce.Models.Api;
import com.example.ecommerce.Models.Merchant;
import com.example.ecommerce.Services.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;


@RestController
@RequestMapping("api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {
    private final MerchantService merchantService;

    @GetMapping
    public ResponseEntity<ArrayList<Merchant>> getMerchant(){
        if (merchantService.getMerchantse()!=null)
            return ResponseEntity.status(200).body(merchantService.getMerchantse());
        return ResponseEntity.status(400).body(merchantService.getMerchantse());




    }

    @PostMapping
    public ResponseEntity<Api> addMerchant(@RequestBody @Valid Merchant merchant , Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));

        if (merchantService.isAdd(merchant))
            return ResponseEntity.status(200).body(new Api("Added new merchant",200));

        return ResponseEntity.status(400).body(new Api("This merchant is already exist",400));

    }


    @PutMapping
    public ResponseEntity<Api> updateMerchant(@RequestBody @Valid Merchant merchant , Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        if (merchantService.isUpdate(merchant))
            return ResponseEntity.status(200).body(new Api("Update the merchant",200));

        return ResponseEntity.status(400).body(new Api("This merchant is not exist",400));

    }


    @DeleteMapping
    public ResponseEntity<Api> deleteMerchant(@RequestBody @Valid Merchant merchant , Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        if (merchantService.isDelete(merchant))
            return ResponseEntity.status(200).body(new Api("The merchant Deleted",200));

        return ResponseEntity.status(400).body(new Api("This merchant is not exist",400));

    }
}

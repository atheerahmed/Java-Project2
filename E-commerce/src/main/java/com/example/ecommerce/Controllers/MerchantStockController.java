package com.example.ecommerce.Controllers;

import com.example.ecommerce.Models.Api;
import com.example.ecommerce.Models.MerchantStock;
import com.example.ecommerce.Services.MerchantStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
@RestController
@RequestMapping("api/v1/merchantstock")
@RequiredArgsConstructor
public class MerchantStockController {
    private final MerchantStockService stockService;

    @GetMapping
    public ResponseEntity<ArrayList<MerchantStock>> getMerchantStock(){
        if (stockService.getMerchantStock()!=null)
            return ResponseEntity.status(200).body(stockService.getMerchantStock());
        return ResponseEntity.status(400).body(stockService.getMerchantStock());




    }

    @PostMapping
    public ResponseEntity<Api> addMerchantStock(@RequestBody @Valid MerchantStock merchantStock , Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));

        if (stockService.isAdd(merchantStock))
            return ResponseEntity.status(200).body(new Api("Added new user",200));

        return ResponseEntity.status(400).body(new Api("This user is already exist",400));

    }


    @PutMapping
    public ResponseEntity<Api> updateMerchantStock(@RequestBody @Valid MerchantStock merchantStock , Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        if (stockService.isUpdate(merchantStock))
            return ResponseEntity.status(200).body(new Api("merchantStock the user",200));

        return ResponseEntity.status(400).body(new Api("This merchantStock is not exist",400));

    }


    @DeleteMapping
    public ResponseEntity<Api> deleteMerchantStock(@RequestBody @Valid MerchantStock merchantStock , Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        if (stockService.isDelete(merchantStock))
            return ResponseEntity.status(200).body(new Api("The merchantStock Deleted",200));

        return ResponseEntity.status(400).body(new Api("This merchantStock is not exist",400));

    }
}

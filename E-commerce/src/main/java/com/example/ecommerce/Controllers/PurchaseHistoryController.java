package com.example.ecommerce.Controllers;

import com.example.ecommerce.Models.PurchaseHistory;
import com.example.ecommerce.Services.PurchaseHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/PurchaseHistory")
public class PurchaseHistoryController {
    private final PurchaseHistoryService purchaseHistoryService;

    @GetMapping
    public ResponseEntity<ArrayList<PurchaseHistory>> getPurchaseHistory(){
        ArrayList<PurchaseHistory> History=purchaseHistoryService.getPurchaseHistory();
        if (purchaseHistoryService.getPurchaseHistory()!=null)
            return ResponseEntity.status(200).body(History);
        return ResponseEntity.status(400).body(History);




    }

}

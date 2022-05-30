package com.example.ecommerce.Services;

import com.example.ecommerce.Models.PurchaseHistory;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.ArrayList;
@Service

public class PurchaseHistoryService {
    private ArrayList<PurchaseHistory> purchaseHistories=new ArrayList<>();

    public boolean addHistory(String Id,String userid,String productid,Integer price) {
        PurchaseHistory History=new PurchaseHistory(Id, userid,productid,price);
        return purchaseHistories.add(History);
    }

    public ArrayList<PurchaseHistory> getPurchaseHistory() {
        return purchaseHistories;
    }


    public boolean check(String userid, String productid) {
        for (PurchaseHistory history:purchaseHistories) {
            if (history.getUserid().equals(userid)&&history.getProductid().equals(productid))
                return true;

        }
        return false;
    }
}

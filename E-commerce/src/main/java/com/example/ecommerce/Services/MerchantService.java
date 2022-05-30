package com.example.ecommerce.Services;

import com.example.ecommerce.Models.Merchant;
import com.example.ecommerce.Models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class MerchantService {
    private ArrayList<Merchant> merchants=new ArrayList<>();

    public ArrayList<Merchant> getMerchantse(){
        return merchants;
    }

    public boolean isAdd(Merchant merchant) {
        return merchants.add(merchant);
    }

    public boolean isUpdate(Merchant merchant) {
        for (int i = 0; i < merchants.size() ; i++) {
            if (merchants.get(i).getId().equals(merchant.getId()))
                merchants.set(i,merchant);
            return true;
        }
        return false;
    }

    public boolean isDelete(Merchant merchant) {
        return merchants.remove(merchant);


    }
    public Merchant getMerchantByid(String merchantid) {
        for (Merchant merchant:merchants ) {
            if (merchantid.equals(merchant.getId()))
                return merchant;
        }
        return null;
    }
}

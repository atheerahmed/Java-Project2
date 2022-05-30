package com.example.ecommerce.Services;

import com.example.ecommerce.Models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MerchantStockService {
    private final ProductService productService;
    private final PurchaseHistoryService purchaseHistoryService;
    private ArrayList<MerchantStock> merchantStocks=new ArrayList<>();

    public ArrayList<MerchantStock> getMerchantStock(){
        return merchantStocks;
    }

    public boolean isAdd(MerchantStock merchantStock) {
        return merchantStocks.add(merchantStock);
    }

    public boolean isUpdate(MerchantStock merchantStock) {
        for (int i = 0; i < merchantStocks.size() ; i++) {
            if (merchantStocks.get(i).getId().equals(merchantStock.getId()))
                merchantStocks.set(i,merchantStock);
            return true;
        }
        return false;
    }

    public boolean isDelete(MerchantStock merchantStock) {
        return merchantStocks.remove(merchantStock);


    }


    private String getID() {
        String uniqueID = UUID.randomUUID().toString();
        return uniqueID;
    }

    public boolean addProduct(String merchantid, String productid,Integer stock) {
            Product product = productService.getProductByid(productid);
           if (product==null)
                return false;
         MerchantStock MS= new MerchantStock(getID(),productid,merchantid,stock);
        merchantStocks.add(MS);
            return true;


        }


    public Integer getMerchantStock(Product product, Merchant merchant,User user) {
        for (MerchantStock merchantStock:merchantStocks) {
            if (product.getId().equals(merchantStock.getProductId())) {
                if (merchant.getId().equals(merchantStock.getMerchantid())) {
                    if (merchantStock.getStock() < 0) {
                        return -1;}
                        Integer newstock = merchantStock.getStock() - 1;
                        merchantStock.setStock(newstock);
                        if (user.getBalance()< product.getPrice()){
                            return 0;  }
                        Integer newbalance = user.getBalance() - product.getPrice();
                        user.setBalance(newbalance);


                        purchaseHistoryService.addHistory(getID(), user.getId(), product.getId(), product.getPrice());
                    return 2;


                }
                }
            }
        return 3;
        }

    public Boolean inStock(ArrayList<Product> products) {
        for (Product product:products){
            if (getMerchantStockById(product.getId())){
               return true;

            }
        }
        return false;
    }

    private Boolean getMerchantStockById(String id) {
        for (MerchantStock merchantStock:merchantStocks ) {
            if(merchantStock.getProductId().equals(id))
               if ( merchantStock.getStock()>1){
                   Integer newstock=merchantStock.getStock()-1;
                   merchantStock.setStock(newstock);
                return true;}
            else
                return false;

        }
        return false;
    }
}




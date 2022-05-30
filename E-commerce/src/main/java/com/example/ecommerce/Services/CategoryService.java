package com.example.ecommerce.Services;

import com.example.ecommerce.Models.Category;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class CategoryService {
    private ArrayList<Category> categories=new ArrayList<>();

    public ArrayList<Category> getCategories(){
        return categories;
    }

    public boolean isAdd(Category category) {
        return categories.add(category);
    }

    public boolean isUpdate(Category category) {
        for (int i = 0; i < categories.size() ; i++) {
            if (categories.get(i).getId().equals(category.getId()))
                categories.set(i,category);
            return true;
        }
        return false;
    }

    public boolean isDelete(Category category) {
        return categories.remove(category);


    }
}

package com.example.ecommerce.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class Comment {

//        @NotEmpty(message = " ID" )
//        @Size(min=3,message = " ID have to be 3 character long ")
        private String id;
//        @NotEmpty(message = " userId" )
//        @Size(min=5,message = " User ID have to be 3 character long ")
        private String userId;
//        @NotEmpty(message = " message" )
//        @Size(min=6,message = " Message have to be 3 Length long ")
        private String message;
//        @NotNull(message = " rate" )
//        @Range(min=1,max = 5 ,message = " Rate must be a number between 1 - 5")
        private Integer rate;



}

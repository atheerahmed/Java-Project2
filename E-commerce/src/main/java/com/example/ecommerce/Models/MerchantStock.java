package com.example.ecommerce.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
@AllArgsConstructor
public class MerchantStock {
    @NotEmpty
    @Size(min=3,message = " ID have to be 3 character long ")
    private String Id;
    @NotEmpty
    @Size(min=5,message = " User ID have to be 3 character long ")
    private String productId ;
    @NotEmpty
    @Length(min=6,message = " Message have to be 3 Length long ")
    private String merchantid ;
    @NotNull
    @Min(value=1,message = " Stock must be more than 1 ")
    private Integer stock ;

}

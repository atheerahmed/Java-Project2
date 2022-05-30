package com.example.ecommerce.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class Merchant {
    @NotEmpty
    @Size(min=3,message = " ID have to be 3 character long ")
    private String Id;

    @NotEmpty
    @Length(min=3,message = " Name have to be 3 length  long ")
    private String name;
}

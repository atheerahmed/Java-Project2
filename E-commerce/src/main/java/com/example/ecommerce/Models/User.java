package com.example.ecommerce.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class User {
    @NotEmpty
    @Size(min=3,message = " ID have to be 3 character long ")
    private String Id;
    @NotEmpty
    @Length(min=5,message = " User ID have to be 5 Length long ")
    private String username  ;
    @NotEmpty
    @Length(min=3,message = " Password have to be 3 Length long ")
    private String password    ;
    @Email
    private String email     ;
    @NotEmpty
    @Pattern(regexp = "(Admin|Customer)",message = " have to be in (Admin or Customer)")
    private String role      ;
    @NotNull
    @Positive
    private Integer balance  ;
}

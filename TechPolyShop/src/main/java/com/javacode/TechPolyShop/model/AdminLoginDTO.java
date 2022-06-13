package com.javacode.TechPolyShop.model;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminLoginDTO {
	@NotEmpty
    private String username;
	@NotEmpty
    private String password;
	private Boolean rememberMe = false;
}

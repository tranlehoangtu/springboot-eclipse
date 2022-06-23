package com.javacode.model;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO implements Serializable{
	@NotEmpty
	@Length(min = 6)
	String username;
	@NotEmpty
	@Length(min = 6)
	String password;
	String confirmPassword;
}

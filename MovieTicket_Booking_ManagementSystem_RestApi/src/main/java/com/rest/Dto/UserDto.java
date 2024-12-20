package com.rest.Dto;

import com.rest.Enum.UserModule;

import lombok.Data;

@Data
public class UserDto {

	private int id;

	private String name;

	private String email;

	private String password;

	private String phoneNumber;

	private UserModule user;



}

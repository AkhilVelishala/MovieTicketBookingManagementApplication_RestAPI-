package com.rest.Entity;

import com.rest.Enum.UserModule;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="user_Module123")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private int id;

	@Column(name = "user_name",nullable = false)
	private String name;

	@Column(name="user_email",nullable = false)
	private String email;

	@Column(name="user_password",nullable = false)
	private String password;

	@Column(name="user_phoneNumber", nullable = false,updatable = false)
	private String phoneNumber;

	@Enumerated(EnumType.STRING)
	private UserModule user;



}

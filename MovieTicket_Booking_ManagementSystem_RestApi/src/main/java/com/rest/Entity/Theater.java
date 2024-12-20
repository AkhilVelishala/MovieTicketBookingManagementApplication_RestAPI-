package com.rest.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "theater_Module123")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Theater {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "theater_id")
	private int id;

	@Column(name = "theater_name")
	private String name;

	@Column(name = "theater_location")
	private String location;

	@ManyToMany(mappedBy = "theater")
	private List<Movie> movies;

	@OneToMany(mappedBy = "theater",  orphanRemoval = true)
	@JsonIgnore
	private List<Screen> screens;


}

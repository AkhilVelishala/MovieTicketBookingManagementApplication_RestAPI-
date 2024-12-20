package com.rest.Dto;

import com.rest.Enum.SeatModule;

import lombok.Data;

@Data
public class SeatDto {

	private int id;

	private String row;

	private int number;

	private SeatModule type;

	private boolean availability;





}

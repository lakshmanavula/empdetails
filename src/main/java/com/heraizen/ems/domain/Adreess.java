package com.heraizen.ems.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Adreess {
	private String street;
	private String city;
	private String state;
	private String country;
	private String pincode;

}

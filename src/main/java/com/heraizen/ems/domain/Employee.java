package com.heraizen.ems.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    
	@Id
	private String empno;
	private String ename;
	private String email;
	private String mobile;
	private LocalDateTime dob;
	private Adreess address;
	private LocalDateTime doj;
}

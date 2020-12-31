package com.heraizen.ems.web;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
	private int status;
	private String message;
	private LocalDateTime dateTime;
}

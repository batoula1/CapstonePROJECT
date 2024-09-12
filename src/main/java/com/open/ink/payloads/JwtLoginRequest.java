package com.open.ink.payloads;

import lombok.Data;

@Data
public class JwtLoginRequest {
	private String userName;
	private String password;
}

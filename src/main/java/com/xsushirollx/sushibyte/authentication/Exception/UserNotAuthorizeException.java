package com.xsushirollx.sushibyte.authentication.Exception;
import javax.naming.AuthenticationException;

public class UserNotAuthorizeException extends AuthenticationException {
	private static final long serialVersionUID = -5441031416998630150L;

	public UserNotAuthorizeException(String msg) {
		super(msg);
	}

}

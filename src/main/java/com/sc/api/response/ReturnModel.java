package com.sc.api.response;

import java.io.Serializable;

public class ReturnModel implements Serializable {

	private static final long serialVersionUID = -3754706680805810719L;

	private boolean flag = true;
	private String code = "0";
	private String message = "Success";
	private Object data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}

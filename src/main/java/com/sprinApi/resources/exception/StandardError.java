package com.sprinApi.resources.exception;

import java.io.Serializable;

public class StandardError implements Serializable {

	private static final long serialVersionUID = 1774970578386379358L;

	private Integer status;
	private String msg;
	private Long timeStamp;

	public StandardError(Integer status, String msg, Long timeStamp) {
		super();
		this.setStatus(status);
		this.setMsg(msg);
		this.setTimeStamp(timeStamp);
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}

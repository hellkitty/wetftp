package org.iptime.dinky.domain;

public class ReturnMsgObj {

	private boolean result;
	private String msg;
	
	public ReturnMsgObj() {}

	public ReturnMsgObj(boolean result, String msg) {
		super();
		this.result = result;
		this.msg = msg;
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "ReturnMsgObj [result=" + result + ", msg=" + msg + "]";
	}
	
	
}

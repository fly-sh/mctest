package com.example.demo.info.model;

public class CallModel {
	
	String phoneNumber;
	String message;
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
    public String toString() {
        return "CallModel{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", message=" + message +
                '}';
    }
}

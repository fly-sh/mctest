package com.example.demo.info.model;

import java.util.Arrays;
import java.util.HashMap;

public class inArguments {
	
	public inArguments() {};
	
	public inArguments(String phoneNumber, dataExtensionObj dataExtensionObj, String[] personalFieldArr, t_data tdata) {

		System.out.println("tdata");
		
		this.phoneNumber = phoneNumber;
		this.personalFieldArr = personalFieldArr;
		
		this.dataExtensionObj = dataExtensionObj;
		this.t_data = tdata;
		
	};
	
	
	private String phoneNumber;
	private dataExtensionObj dataExtensionObj;
	private String[] personalFieldArr;
	private t_data t_data;
	
	public class dataExtensionObj{
		private String phoneNumber;
		private String ID;
	}

	public class t_data{
		private String message;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getMessage() {
		
		String msg = "";
		
		return msg;
	}

	@Override
	public String toString() {
		return "inArguments [phoneNumber=" + phoneNumber + ", personalFieldArr=" + Arrays.toString(personalFieldArr)
				+ ", dataExtensionObj=" + dataExtensionObj + ", t_data=" + t_data + "]";
	}
}


//{
//	"mode":0,
//	"activityId":"a5c0de71-2634-4e04-8f27-5f9b78c9f9f7",
//	"activityObjectID":"a5c0de71-2634-4e04-8f27-5f9b78c9f9f7",
//	"definitionInstanceId":"375ee341-d99b-45ac-817b-5b6cc4e4d9f6",
//	"keyValue":"7116630e-c67e-4dfc-aa88-3fc94450a73e",
//	"outArguments":[],
//	"inArguments":[
//				{"phoneNumber":"01050569266",
//				 "dataExtensionObj":{"phoneNumber":"01050569266","ID":"7116630e-c67e-4dfcaa88-3fc94450a73e"},
//				"personalFieldArr":["message","ID"],
//				"t_data":{"message":"testtest"}
//				}
//			],
//	"activityInstanceId":"798b2d83-0c27-43fa-bb99-32d977789ffa",
//	"journeyId":"ed4b872a-a0ce-4ea6-9d42-5e3f63b094ba"
//}
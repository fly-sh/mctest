package com.example.demo.info.model;

import java.util.Arrays;

import com.example.demo.info.model.inArguments;

public class model {

	public model() {};

	public model(Integer mode, String activityId, String activityObjectID, String definitionInstanceId, String keyValue, String[] outArguments, inArguments[] inAr,String activityInstanceId,String journeyId) {
		
		this.mode = mode;
		this.activityId = activityId;
		this.activityObjectID = activityObjectID;
		this.definitionInstanceId = definitionInstanceId;
		this.keyValue = keyValue;
		this.outArguments = outArguments;
		
		this.inAr = inAr;
	};
	
	private Integer mode;
	private String activityId;
	private String activityObjectID;
	private String definitionInstanceId;
	private String keyValue;
	private String[] outArguments;
	private inArguments[] inAr;
	private String activityInstanceId;
	private String journeyId;
	
	public inArguments getInArguments() {
		return inAr[0];
	}

	@Override
	public String toString() {
		return "model [mode=" + mode + ", activityId=" + activityId + ", activityObjectID=" + activityObjectID
				+ ", definitionInstanceId=" + definitionInstanceId + ", keyValue=" + keyValue + ", outArguments="
				+ Arrays.toString(outArguments) + ", inAr=" + Arrays.toString(inAr) + ", activityInstanceId="
				+ activityInstanceId + ", journeyId=" + journeyId + "]";
	}

	
	
}

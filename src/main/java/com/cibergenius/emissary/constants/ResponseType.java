package com.cibergenius.emissary.constants;

import org.springframework.http.HttpStatus;

import com.cibergenius.emissary.components.ResponseObject;
import com.google.gson.Gson;

public enum ResponseType 
{
	SYSTEM_GENERAL_ERROR("-99","FAILED, TRY AGAIN", HttpStatus.INTERNAL_SERVER_ERROR),
	SYSTEM_3ER_ERROR("-98","Unable to connect to the service", HttpStatus.BAD_REQUEST),
	SYSTEM_SUCCESS("00","SUCCESS",HttpStatus.OK);
	
	private final String code;
	private final String detail;
	private final HttpStatus httpCode;
	private final Gson gson = new Gson();
	
	
	ResponseType(String code, String detail, HttpStatus httpCode) {
		this.code = code;
		this.detail = detail;
		this.httpCode = httpCode;
	}


	public static ResponseType getById(String code)
	{
		for(ResponseType e : values())
		{
			if(e.code.equals(code)) {
				return e;
			}
		}
		return SYSTEM_GENERAL_ERROR;
	}
	
	public String getJson()
	{
		ResponseObject responseObject = new ResponseObject(code, detail);
		return gson.toJson(responseObject);
	}
	
	
	public String getCode() {
		return code;
	}
	public String getDetail() {
		return detail;
	}
	public HttpStatus getHttpCode() {
		return httpCode;
	}	
}

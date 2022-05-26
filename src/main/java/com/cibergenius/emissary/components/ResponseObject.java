package com.cibergenius.emissary.components;

import com.cibergenius.emissary.constants.ResponseType;

public class ResponseObject {
	private String responseCode;
	private String responseMessage;
	private Object responseData;
	
	public ResponseObject(String code, String detail)
	{
		this.responseCode = code;
		this.responseMessage = detail;
	}
	
	public ResponseObject(String code, String detail, Object data)
	{
		this(code, detail);
		this.responseData = data;
	}
	
	public static ResponseObject success()
	{
		return new ResponseObject(ResponseType.SYSTEM_SUCCESS.getCode(), ResponseType.SYSTEM_SUCCESS.getDetail());
	}
	
	public static ResponseObject success(Object data)
	{
		return new ResponseObject(ResponseType.SYSTEM_SUCCESS.getCode(), ResponseType.SYSTEM_SUCCESS.getDetail(), data);
	}
	
	public static ResponseObject error()
	{
		return new ResponseObject(ResponseType.SYSTEM_GENERAL_ERROR.getCode(), ResponseType.SYSTEM_GENERAL_ERROR.getDetail());
	}
	
	public static ResponseObject error(Object data)
	{
		return new ResponseObject(ResponseType.SYSTEM_GENERAL_ERROR.getCode(), ResponseType.SYSTEM_GENERAL_ERROR.getDetail(), data);
	}
	
	public static ResponseObject error(String code, Object data)
	{
		return new ResponseObject(ResponseType.getById(code).getCode(), ResponseType.getById(code).getDetail(), data);
	}
	
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public Object getResponseData() {
		return responseData;
	}
	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}
}

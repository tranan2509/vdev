package org.vdev.api.response;

import org.vdev.constants.Constants;
import org.vdev.utils.DataChecker;

public class BaseResponse<T> {
	
	private String message;
	private boolean success;
	private T object;
	
	public BaseResponse() {
		super();
	}
	public BaseResponse(T object, String message) {
		super();
		this.message = message;
		this.object = object;
	}
	
	public BaseResponse(T object, boolean success, String message) {
		super();
		this.message = message;
		this.success = success;
		this.object = object;
	}
	public String getMessage() {
		return DataChecker.isEmpty(message) ? Constants.EMPTY : message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(T object) {
		this.object = object;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
}

package org.team.framework.tcl.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回Json结果 状态码： 200 成功 400 错误 401 用户认证
 * @author liuliang
 */
public class JsonResult implements Serializable {
	private static final long serialVersionUID = 2326159352932738196L;
	public static Integer OK = 200;
	public static Integer FAILURE = 300;
	public static Integer ERROR = 400;
	public static Integer UNLOGIN = 401;
	public static Integer BADREQUEST = 500;
	public static Integer NOAUTHORITY = 403;
	
	private Integer statusCode;

	private String message;

	private final Map<String, Object> result;

	public JsonResult() {
		result = new HashMap<String, Object>();
		this.statusCode = OK;
		this.message = "";
	}

	public JsonResult(Object data) {
		result = new HashMap<String, Object>();
		result.put("data", data);
		this.statusCode = OK;
		this.message = "";
	}
	
	public JsonResult(Integer statucCode,String errMsg) {
		result = new HashMap<String, Object>();
		this.statusCode = statucCode;
		this.message = errMsg;
	}
	
	public JsonResult(String errMsg) {
		result = new HashMap<String, Object>();
		this.statusCode = FAILURE;
		this.message = errMsg;
	}

	public void put(String key, Object value) {
		result.put(key, value);
	}

	public void putAll(Map<String, ?> map) {
		result.putAll(map);
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getResult() {
		return result;
	}
}

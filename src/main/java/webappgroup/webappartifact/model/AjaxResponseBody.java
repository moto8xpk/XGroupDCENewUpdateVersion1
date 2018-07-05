package webappgroup.webappartifact.model;

import com.fasterxml.jackson.annotation.JsonView;

import webappgroup.webappartfact.web.jsonview.Views;

public class AjaxResponseBody {
	
	@JsonView(Views.Public.class)
	String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "AjaxResponseResult [msg=" + msg + "]";
	}
}

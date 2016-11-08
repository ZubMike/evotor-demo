package ru.zubmike.demo.evotor.types.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlRootElement(name = "request")
public class UserRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "request-type")
	private String requestType;

	@XmlPath("extra[@name='login']/text()")
	private String login;

	@XmlPath("extra[@name='password']/text()")
	private String password;

	public UserRequest() {

	}

	public UserRequest(String requestType, String login, String password) {
		this.requestType = requestType;
		this.login = login;
		this.password = password;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

package ru.zubmike.demo.evotor.types.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class SimpleResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "result-code")
	private int resultCode;
	
	public SimpleResponse() {
	
	}

	public SimpleResponse(int resultCode) {
		this.resultCode = resultCode;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

}

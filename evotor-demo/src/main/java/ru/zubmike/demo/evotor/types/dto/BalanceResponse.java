package ru.zubmike.demo.evotor.types.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class BalanceResponse extends SimpleResponse {

	private static final long serialVersionUID = 1L;

	@XmlPath("extra[@name='balance']/text()")
	private double balance;

	public BalanceResponse() {

	}

	public BalanceResponse(int resultCode) {
		this(resultCode, 0);
	}

	public BalanceResponse(int resultCode, double balance) {
		super(resultCode);
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}

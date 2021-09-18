package com.orange.usageconsumption.objects.response;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
public class CustomerDetailedInquiry {
	@XmlElement
	@JsonProperty("TransactionID")
	String transactionID;
	@JsonProperty("Errcode")
	int errcode;
	@JsonProperty("consumptionDates")
	ConsumptionDates consumptionDates;

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public ConsumptionDates getConsumptionDates() {
		return consumptionDates;
	}

	public void setConsumptionDates(ConsumptionDates consumptionDates) {
		this.consumptionDates = consumptionDates;
	}
}

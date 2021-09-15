package com.orange.usageconsumption.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDetailedInquiry {
	String dial;
	String fromdate;
	String todate;
	@JsonProperty("Lang")
	String lang;
	@JsonProperty("TransactionID")
	String transactionID;
	String callername;

	public String getDial() {
		return dial;
	}

	public void setDial(String dial) {
		this.dial = dial;
	}

	public String getFromdate() {
		return fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getTodate() {
		return todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getCallername() {
		return callername;
	}

	public void setCallername(String callername) {
		this.callername = callername;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
}

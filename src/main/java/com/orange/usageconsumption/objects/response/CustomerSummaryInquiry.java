package com.orange.usageconsumption.objects.response;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class CustomerSummaryInquiry {
	String transactionId;
	int errCode;
	@JsonProperty("Summary")
	@JacksonXmlElementWrapper(useWrapping = false)
	ArrayList<Summary> summary;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public ArrayList<Summary> getSummary() {
		return summary;
	}

	public void setSummary(ArrayList<Summary> summary) {
		this.summary = summary;
	}
}

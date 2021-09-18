package com.orange.usageconsumption.objects.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
public class Detailed {
	@JsonProperty("CALLINGNUMBER")
	String callingNumber;
	@JsonProperty("CALLEDNUMBER")
	String calledNumber;
	@JsonProperty("Usage")
	String usage;
	@JsonProperty("UsageTypeID")
	String usageTypeID;
	@JsonProperty("Type")
	String type;
	@JsonProperty("Price")
	String price;
	@JsonProperty("TransDate")
	String transDate;
	@JsonProperty("Destination")
	String destination;
	@JsonProperty("DESCRIPTION")
	String description;
	@JsonProperty("Info1")
	String info1;
	@JsonProperty("Info2")
	String info2;
	@JsonProperty("Info3")
	String info3;
	@JsonProperty("Info4")
	String info4;
	@JsonProperty("Info5")
	String info5;
	@JsonIgnore
	String charged_amount_sum;
	@JsonIgnore
	String charged_amount_total_sum;

	public String getCallingNumber() {
		return callingNumber;
	}

	public void setCallingNumber(String callingNumber) {
		this.callingNumber = callingNumber;
	}

	public String getCalledNumber() {
		return calledNumber;
	}

	public void setCalledNumber(String calledNumber) {
		this.calledNumber = calledNumber;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public String getUsageTypeID() {
		return usageTypeID;
	}

	public void setUsageTypeID(String usageTypeID) {
		this.usageTypeID = usageTypeID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInfo1() {
		return info1;
	}

	public void setInfo1(String info1) {
		this.info1 = info1;
	}

	public String getInfo2() {
		return info2;
	}

	public void setInfo2(String info2) {
		this.info2 = info2;
	}

	public String getInfo3() {
		return info3;
	}

	public void setInfo3(String info3) {
		this.info3 = info3;
	}

	public String getInfo4() {
		return info4;
	}

	public void setInfo4(String info4) {
		this.info4 = info4;
	}

	public String getInfo5() {
		return info5;
	}

	public void setInfo5(String info5) {
		this.info5 = info5;
	}

	public String getCharged_amount_sum() {
		return charged_amount_sum;
	}

	public void setCharged_amount_sum(String charged_amount_sum) {
		this.charged_amount_sum = charged_amount_sum;
	}

	public String getCharged_amount_total_sum() {
		return charged_amount_total_sum;
	}

	public void setCharged_amount_total_sum(String charged_amount_total_sum) {
		this.charged_amount_total_sum = charged_amount_total_sum;
	}

}

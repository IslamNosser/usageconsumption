package com.orange.usageconsumption.objects.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
public class Summary {
	@JsonProperty("Date")
	String date;
	@JsonProperty("Usage")
	String usage;
	@JsonProperty("Type")
	String type;
	@JsonProperty("UsageTypeID")
	String usageTypeId;
	@JsonProperty("Price")
	String price;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsageTypeId() {
		return usageTypeId;
	}

	public void setUsageTypeId(String usageTypeId) {
		this.usageTypeId = usageTypeId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}

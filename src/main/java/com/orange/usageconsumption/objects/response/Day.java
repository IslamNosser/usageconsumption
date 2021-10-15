package com.orange.usageconsumption.objects.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
public class Day {
	@JsonProperty("Date")
	String date;
	@JsonProperty("TotalSumOfDate")
	String totalSumOfDate;
	@JsonProperty("Voice")
	Voice voice;
	@JsonProperty("Internet")
	Internet internet;
	@JsonProperty("SMS")
	SMS sms;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTotalSumOfDate() {
		return totalSumOfDate;
	}

	public void setTotalSumOfDate(String totalSumOfDate) {
		this.totalSumOfDate = totalSumOfDate;
	}

	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}

	public Internet getInternet() {
		return internet;
	}

	public void setInternet(Internet internet) {
		this.internet = internet;
	}

	public SMS getSms() {
		return sms;
	}

	public void setSms(SMS sms) {
		this.sms = sms;
	}
}

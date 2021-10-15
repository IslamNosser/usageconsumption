package com.orange.usageconsumption.objects.response;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@XmlRootElement
public class SMS {
	@JsonProperty("TotalSumofSMS")
	String totalSumofSMS;
	@JsonProperty("detailed")
	@JacksonXmlElementWrapper(useWrapping = false)
	ArrayList<Detailed> detailed;

	public String getTotalSumofSMS() {
		return totalSumofSMS;
	}

	public void setTotalSumofSMS(String totalSumofSMS) {
		this.totalSumofSMS = totalSumofSMS;
	}

	public ArrayList<Detailed> getDetailed() {
		return detailed;
	}

	public void setDetailed(ArrayList<Detailed> detailed) {
		this.detailed = detailed;
	}
}

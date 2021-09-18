package com.orange.usageconsumption.objects.response;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@XmlRootElement
public class Internet {
	@JsonProperty("TotalSumofInternet")
	String totalSumofInternet;
	@JsonProperty("detailed")
	@JacksonXmlElementWrapper(useWrapping = false)
	ArrayList<Detailed> detailed;

	public String getTotalSumofInternet() {
		return totalSumofInternet;
	}

	public void setTotalSumofInternet(String totalSumofInternet) {
		this.totalSumofInternet = totalSumofInternet;
	}

	public ArrayList<Detailed> getDetailed() {
		return detailed;
	}

	public void setDetailed(ArrayList<Detailed> detailed) {
		this.detailed = detailed;
	}
}

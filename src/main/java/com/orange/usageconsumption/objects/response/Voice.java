package com.orange.usageconsumption.objects.response;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@XmlRootElement
public class Voice {
	@JsonProperty("TotalSumofVoice")
	String totalSumofVoice;
	@JsonProperty("detailed")
	@JacksonXmlElementWrapper(useWrapping = false)
	ArrayList<Detailed> detailed;

	public String getTotalSumofVoice() {
		return totalSumofVoice;
	}

	public void setTotalSumofVoice(String totalSumofVoice) {
		this.totalSumofVoice = totalSumofVoice;
	}

	public ArrayList<Detailed> getDetailed() {
		return detailed;
	}

	public void setDetailed(ArrayList<Detailed> detailed) {
		this.detailed = detailed;
	}
}

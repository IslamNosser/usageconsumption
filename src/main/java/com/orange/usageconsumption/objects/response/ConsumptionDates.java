package com.orange.usageconsumption.objects.response;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@XmlRootElement
public class ConsumptionDates {
	@JsonProperty("Day")
	@JacksonXmlElementWrapper(useWrapping = false)  
	ArrayList<Day> day;

	public ArrayList<Day> getDay() {
		return day;
	}

	public void setDay(ArrayList<Day> day) {
		this.day = day;
	}
}

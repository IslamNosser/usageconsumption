package com.orange.usageconsumption.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.orange.usageconsumption.objects.response.ConsumptionDates;
import com.orange.usageconsumption.objects.response.CustomerDetailedInquiry;
import com.orange.usageconsumption.objects.response.Day;
import com.orange.usageconsumption.objects.response.Detailed;
import com.orange.usageconsumption.objects.response.Internet;
import com.orange.usageconsumption.objects.response.Voice;

public class UsageUtils {
	public static HashMap<String, ArrayList<Detailed>> formatDetailedUsg(ArrayList<Object> detailedUsgList) {
		HashMap<String, ArrayList<Detailed>> formattedUsg = new HashMap<>();
		for (Object single_usage_object : detailedUsgList) {
			Detailed temp_detailed = new Detailed();
			Object[] single_usg_details_arr = (Object[]) single_usage_object;
			String day = String.valueOf(single_usg_details_arr[0]).substring(0, 10);
			if (formattedUsg.get(day) == null) {
				ArrayList<Detailed> detailed_list = new ArrayList<Detailed>();
				temp_detailed.setTransDate(String.valueOf(single_usg_details_arr[0]));
				temp_detailed.setType(String.valueOf(single_usg_details_arr[1]));
				temp_detailed.setUsageTypeID(String.valueOf(single_usg_details_arr[1]));
				if (single_usg_details_arr[5] != null) {
					temp_detailed.setPrice(String.valueOf(single_usg_details_arr[5]));
				} else {
					temp_detailed.setPrice("0");
				}
				temp_detailed.setCallingNumber(String.valueOf(single_usg_details_arr[3]));
				temp_detailed.setCalledNumber(String.valueOf(single_usg_details_arr[2]));
				temp_detailed.setDescription(String.valueOf(single_usg_details_arr[7]));
				temp_detailed.setDestination(String.valueOf(single_usg_details_arr[6]));
				temp_detailed.setUsage(String.valueOf(single_usg_details_arr[4]));
				if (single_usg_details_arr[9] != null) {
					temp_detailed.setCharged_amount_sum(String.valueOf(single_usg_details_arr[9]));
				} else {
					temp_detailed.setCharged_amount_sum("0");
				}
				if (single_usg_details_arr[10] != null) {
					temp_detailed.setCharged_amount_total_sum(String.valueOf(single_usg_details_arr[10]));
				} else {
					temp_detailed.setCharged_amount_sum("0");
				}
				detailed_list.add(temp_detailed);
				formattedUsg.put(day, detailed_list);
			} else {
				temp_detailed.setTransDate(String.valueOf(single_usg_details_arr[0]));
				temp_detailed.setType(String.valueOf(single_usg_details_arr[1]));
				temp_detailed.setUsageTypeID(String.valueOf(single_usg_details_arr[1]));
				if (single_usg_details_arr[5] != null) {
					temp_detailed.setPrice(String.valueOf(single_usg_details_arr[5]));
				} else {
					temp_detailed.setPrice("0");
				}
				temp_detailed.setCallingNumber(String.valueOf(single_usg_details_arr[3]));
				temp_detailed.setCalledNumber(String.valueOf(single_usg_details_arr[2]));
				temp_detailed.setDescription(String.valueOf(single_usg_details_arr[7]));
				temp_detailed.setDestination(String.valueOf(single_usg_details_arr[6]));
				temp_detailed.setUsage(String.valueOf(single_usg_details_arr[4]));
				if (single_usg_details_arr[9] != null) {
					temp_detailed.setCharged_amount_sum(String.valueOf(single_usg_details_arr[9]));
				} else {
					temp_detailed.setCharged_amount_sum("0");
				}
				if (single_usg_details_arr[10] != null) {
					temp_detailed.setCharged_amount_total_sum(String.valueOf(single_usg_details_arr[10]));
				} else {
					temp_detailed.setCharged_amount_sum("0");
				}
				formattedUsg.get(day).add(temp_detailed);
			}
		}
		return formattedUsg;
	}

	public static CustomerDetailedInquiry formatResponse(HashMap<String, ArrayList<Detailed>> detailed_usg) {
		CustomerDetailedInquiry response = new CustomerDetailedInquiry();
		try {
			ConsumptionDates consumption_dates = new ConsumptionDates();
			ArrayList<Day> day_list = new ArrayList<>();
			for (Map.Entry<String, ArrayList<Detailed>> day_details : detailed_usg.entrySet()) {
				Day day = new Day();
				day.setDate(day_details.getKey());
				Voice voice = new Voice();
				Internet internet = new Internet();
				ArrayList<Detailed> voice_details = new ArrayList<>();
				ArrayList<Detailed> internet_details = new ArrayList<>();
				for (Detailed detailed : day_details.getValue()) {
					if (detailed.getUsageTypeID().equalsIgnoreCase("1")) {
						voice_details.add(detailed);
						voice.setTotalSumofVoice(detailed.getCharged_amount_sum());
						if (detailed.getUsage() != null && !detailed.getUsage().equalsIgnoreCase("null")) {
							int usage = Integer.valueOf(detailed.getUsage()) / 60;
							detailed.setUsage(String.valueOf(usage) + " MIN");
						}
						detailed.setType("VOICE");
						detailed.setUsageTypeID("VOICE");
					} else if (detailed.getUsageTypeID().equalsIgnoreCase("2")) {
						internet_details.add(detailed);
						internet.setTotalSumofInternet(detailed.getCharged_amount_sum());
						if (detailed.getUsage() != null && !detailed.getUsage().equalsIgnoreCase("null")) {
							int usage = Integer.valueOf(detailed.getUsage()) / 1000 / 1000;
							detailed.setUsage(String.valueOf(usage) + " MBs");
						}
						detailed.setType("INTERNET");
						detailed.setUsageTypeID("INTERNET");
					}
				}
				voice.setDetailed(voice_details);
				internet.setDetailed(internet_details);
				day.setVoice(voice);
				day.setInternet(internet);
				day.setTotalSumOfDate(String.valueOf(day_details.getValue().get(0).getCharged_amount_total_sum()));
				day_list.add(day);
			}
			consumption_dates.setDay(day_list);
			response.setConsumptionDates(consumption_dates);
			response.setErrcode(0);
		} catch (Exception ex) {
			response.setErrcode(-100);
			ex.printStackTrace();
		}
		return response;
	}
}

package com.orange.usageconsumption.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

//import javax.persistence.EntityManager;
//import javax.persistence.ParameterMode;
//import javax.persistence.PersistenceContext;
//import javax.persistence.StoredProcedureQuery;

//import javax.persistence.EntityManager;
//import javax.persistence.ParameterMode;
//import javax.persistence.PersistenceContext;
//import javax.persistence.StoredProcedureQuery;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.orange.usageconsumption.objects.request.CustomerDetailedInquiryRequest;
import com.orange.usageconsumption.objects.response.ConsumptionDates;
import com.orange.usageconsumption.objects.response.CustomerDetailedInquiry;
import com.orange.usageconsumption.objects.response.Day;
import com.orange.usageconsumption.objects.response.Detailed;
import com.orange.usageconsumption.utils.UsageUtils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UsageConsumptionDetails {
	@PersistenceContext
	private EntityManager entityManager;
	XmlMapper xmlMapper = new XmlMapper();
	private Logger logger = LogManager.getLogger(UsageConsumptionDetails.class);

	@RequestMapping(value = "/services/consumption_details", consumes = { "application/xml" }, produces = {
			"application/xml" }, method = RequestMethod.POST)
	@ResponseBody
	public CustomerDetailedInquiry getUsage(@RequestBody String request)
			throws JsonMappingException, JsonProcessingException {
		long start = System.currentTimeMillis();
		CustomerDetailedInquiryRequest request_obj = xmlMapper.readValue(request, CustomerDetailedInquiryRequest.class);
		CustomerDetailedInquiry response = new CustomerDetailedInquiry();
		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("USAGE_DETAILS_T");

			// Declare the parameters in the same order
			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(4, Class.class, ParameterMode.REF_CURSOR);

			query.setParameter(1, request_obj.getDial());
			query.setParameter(2, request_obj.getFromdate());
			query.setParameter(3, request_obj.getTodate());
//		// Execute query
			query.execute();
			Iterator queryIterator = query.getResultList().iterator();
			ArrayList<Object> outputArray = new ArrayList<Object>();
			while (queryIterator.hasNext()) {
				Object st = (Object) queryIterator.next();
				outputArray.add(st);
			}
			HashMap<String, ArrayList<Detailed>> usageMap = UsageUtils.formatDetailedUsg(outputArray);
			response = UsageUtils.formatResponse(usageMap);
			response.setTransactionID(request_obj.getTransactionID());
			if (response.getConsumptionDates() == null || response.getConsumptionDates().getDay() == null
					|| response.getConsumptionDates().getDay().isEmpty()) {
				response.setErrcode(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.setErrcode(2);
		}
		Collections.sort(response.getConsumptionDates().getDay(), new Comparator<Day>() {
			public int compare(Day o1, Day o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
		});
		long end = System.currentTimeMillis();

		logger.info(request_obj.getTransactionID() + " | " 
				+ xmlMapper.writeValueAsString(request_obj) + " | " 
				+ xmlMapper.writeValueAsString(response)
				+ response.getErrcode() + " | "
				+ (end-start) + " | "
				+ "-" + " | "
				+ request_obj.getDial() + " | "
				+ request_obj.getCallername() + " | "
				+ "DetailsAPI" + " | "
				+ UsageUtils.getHostname() + " | "
				+ "8080"
				);
		return response;
	}
}
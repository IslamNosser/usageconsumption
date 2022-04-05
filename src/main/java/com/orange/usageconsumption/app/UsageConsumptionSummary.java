package com.orange.usageconsumption.app;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

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
import com.orange.usageconsumption.objects.response.CustomerSummaryInquiry;
import com.orange.usageconsumption.objects.response.Summary;
import com.orange.usageconsumption.utils.UsageUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UsageConsumptionSummary {
	@PersistenceContext
	private EntityManager entityManager;
	XmlMapper xmlMapper = new XmlMapper();
	private Logger logger = LogManager.getLogger(UsageConsumptionSummary.class);

	@RequestMapping(value = "/services/consumption_summary", consumes = { "application/xml" }, produces = {
			"application/xml" }, method = RequestMethod.POST)
	@ResponseBody
	public CustomerSummaryInquiry getSummary(@RequestBody String request)
			throws JsonMappingException, JsonProcessingException {
		long start = System.currentTimeMillis();
		CustomerSummaryInquiry summary = new CustomerSummaryInquiry();
		CustomerDetailedInquiryRequest request_obj = xmlMapper.readValue(request, CustomerDetailedInquiryRequest.class);
		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("USAGE_SUMMARY_T");
			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(4, Class.class, ParameterMode.REF_CURSOR);

			query.setParameter(1, request_obj.getDial());
			query.setParameter(2, request_obj.getFromdate());
			query.setParameter(3, request_obj.getTodate());
			query.execute();
			Iterator queryIterator = query.getResultList().iterator();
			ArrayList<Object> outputArray = new ArrayList<Object>();
			while (queryIterator.hasNext()) {
				Object st = (Object) queryIterator.next();
				outputArray.add(st);
			}
			summary = UsageUtils.formatUsgSummary(outputArray);
			summary.setTransactionId(request_obj.getTransactionID());
			if (summary == null || summary.getSummary() == null || summary.getSummary().isEmpty()) {
				summary.setErrCode(1);
			} else {
				summary.setErrCode(0);
			}
		} catch (Exception ex) {
			summary.setErrCode(-100);
			ex.printStackTrace();
		}
		Collections.sort(summary.getSummary(), new Comparator<Summary>() {
			public int compare(Summary o1, Summary o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
		});
		long end = System.currentTimeMillis();
		logger.info(request_obj.getTransactionID() + " | " 
				+ xmlMapper.writeValueAsString(request_obj) + " | " 
				+ xmlMapper.writeValueAsString(summary)
				+ summary.getErrCode() + " | "
				+ (end-start) + " | "
				+ "-" + " | "
				+ request_obj.getDial() + " | "
				+ request_obj.getCallername() + " | "
				+ "SummaryAPI" + " | "
				+ UsageUtils.getHostname() + " | "
				+ "8080"
				);
		return summary;
	}
}

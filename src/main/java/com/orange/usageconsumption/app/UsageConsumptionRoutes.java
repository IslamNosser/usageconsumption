package com.orange.usageconsumption.app;

import java.util.ArrayList;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.http.MediaType;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.orange.usageconsumption.objects.response.Detailed;
import com.orange.usageconsumption.utils.UsageUtils;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UsageConsumptionRoutes extends SpringBootServletInitializer {
	@PersistenceContext
	private EntityManager entityManager;
	XmlMapper xmlMapper = new XmlMapper();

	@RequestMapping(value = "/services/consumption_details", consumes = { "application/xml" }, produces = {
			"application/xml" }, method = RequestMethod.POST)
	@ResponseBody
	public CustomerDetailedInquiry getUsage(@RequestBody String request)
			throws JsonMappingException, JsonProcessingException {
		CustomerDetailedInquiry response = new CustomerDetailedInquiry();
		try {
			CustomerDetailedInquiryRequest request_obj = xmlMapper.readValue(request,
					CustomerDetailedInquiryRequest.class);
			System.out.println(request_obj.getDial() + "," + request_obj.getFromdate() + "," + request_obj.getTodate()
					+ "," + request_obj.getLang() + "," + request_obj.getTransactionID() + ","
					+ request_obj.getCallername());
			;
			System.out.println("Test");
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
		} catch (Exception ex) {
			response.setErrcode(-100);
		}
		return response;
	}
}
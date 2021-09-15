package com.orange.usageconsumption.app;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.orange.usageconsumption.objects.CustomerDetailedInquiry;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UsageConsumptionRoutes extends SpringBootServletInitializer {
//	@Autowired
//	JdbcTemplate jdbc;
	@PersistenceContext
	private EntityManager entityManager;
	XmlMapper xmlMapper = new XmlMapper();

	@RequestMapping(value = "/services/consumption_details", consumes = {
			"application/xml" }, method = RequestMethod.POST)
	public String getUsage(@RequestBody String request) throws JsonMappingException, JsonProcessingException {
		CustomerDetailedInquiry request_obj = xmlMapper.readValue(request, CustomerDetailedInquiry.class);
		System.out.println("Test");
//		String prc = "execute usage_summary('12345','1','1');";
//		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("usage_summary"); 
//
//        //Declare the parameters in the same order
//        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
//        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
//        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
//        query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.OUT);
//        query.registerStoredProcedureParameter(5, String.class, ParameterMode.OUT);
//
//        //Pass the parameter values
//        query.setParameter(1, "12345");
//        query.setParameter(2, "1");
//        query.setParameter(3, "1");
//
//        //Execute query
//        query.execute();
//
//        //Get output parameters
//        Integer outCode = (Integer) query.getOutputParameterValue(3);
//        String outMessage = (String) query.getOutputParameterValue(4);
		return "done";
	}
}

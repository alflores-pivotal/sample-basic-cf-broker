package io.pivotal.sample.broker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cloudfoundry.community.servicebroker.model.BrokerApiVersion;
import org.cloudfoundry.community.servicebroker.model.Catalog;
import org.cloudfoundry.community.servicebroker.model.Plan;
import org.cloudfoundry.community.servicebroker.model.ServiceDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBrokerConfig {

	@Bean
	public Catalog catalog() {		
		return new Catalog( Arrays.asList(
				new ServiceDefinition(
					"my-sample-service", 
					"your-sample-service", 
					"A simple string service implementation based on the Apache Lang project.", 
					true, 
					Arrays.asList(
							new Plan("string-plan", 
									"plan1", 
									"This is a default String plan.  All services are created equally.",
									getPlanMetadata())),
					Arrays.asList("StringService", "document"),
					getServiceDefinitionMetadata(), null,
					null)));
	}
	
	private Map<String,Object> getServiceDefinitionMetadata() {
		Map<String,Object> sdMetadata = new HashMap<String,Object>();
		sdMetadata.put("displayName", "Sample String Service");
		sdMetadata.put("imageUrl","http://icons.iconseeker.com/png/fullsize/climb-on/rope-2.png");
		sdMetadata.put("longDescription","Sample string service to operate on strings.");
		sdMetadata.put("providerDisplayName","Pivotal");
		sdMetadata.put("documentationUrl","http://docs.cloudfoundry.org/services/managing-service-brokers.html");
		sdMetadata.put("supportUrl","http://docs.cloudfoundry.org/services/access-control.html");
		return sdMetadata;
	}
	
	private Map<String,Object> getPlanMetadata() {		
		Map<String,Object> planMetadata = new HashMap<String,Object>();
		planMetadata.put("costs", getCosts());
		planMetadata.put("bullets", getBullets());
		return planMetadata;
	}
	
	private List<Map<String,Object>> getCosts() {
		Map<String,Object> costsMap = new HashMap<String,Object>();
		
		Map<String,Object> amount = new HashMap<String,Object>();
		amount.put("usd", new Double(0.0));
	
		costsMap.put("amount", amount);
		costsMap.put("unit", "MONTHLY");
		
		return Arrays.asList(costsMap);
	}
	
	private List<String> getBullets() {
		return Arrays.asList("Handles String operations.", 
				"Follows StringUtils library from Apache Commons.", 
				"Perform effective String operations.");
	}
	
	@Bean
	public BrokerApiVersion brokerApiVersion() {
	    return new BrokerApiVersion();
	}
}

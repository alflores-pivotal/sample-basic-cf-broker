package io.pivotal.sample.broker;

import java.util.HashMap;
import java.util.Map;

import org.cloudfoundry.community.servicebroker.exception.ServiceBrokerException;
import org.cloudfoundry.community.servicebroker.exception.ServiceInstanceExistsException;
import org.cloudfoundry.community.servicebroker.model.ServiceDefinition;
import org.cloudfoundry.community.servicebroker.model.ServiceInstance;
import org.cloudfoundry.community.servicebroker.service.ServiceInstanceService;
import org.springframework.stereotype.Service;

@Service
public class SampleServiceInstanceService implements ServiceInstanceService {
	private Map<String, ServiceInstance> repo = new HashMap<>();
	
	@Override
	public ServiceInstance createServiceInstance(ServiceDefinition service, String serviceInstanceId, 
												 String planId, String organizationGuid,
												 String spaceGuid) throws ServiceInstanceExistsException,
												 						  ServiceBrokerException {
		ServiceInstance instance = repo.get(serviceInstanceId);
		
		if (instance != null) {
			throw new ServiceInstanceExistsException(instance);
		} else {
			instance = new ServiceInstance(serviceInstanceId, service.getId(),
					   					   planId, organizationGuid, spaceGuid, null);
			repo.put(serviceInstanceId, instance);
		}
		return instance;
	}

	@Override
	public ServiceInstance getServiceInstance(String id) {
		return repo.get(id);
	}

	@Override
	public ServiceInstance deleteServiceInstance(String id, String serviceId,
			String planId) throws ServiceBrokerException {
		return repo.remove(id);
	}

}

package io.pivotal.sample.broker;

import java.util.HashMap;
import java.util.Map;

import org.cloudfoundry.community.servicebroker.exception.ServiceBrokerException;
import org.cloudfoundry.community.servicebroker.exception.ServiceInstanceBindingExistsException;
import org.cloudfoundry.community.servicebroker.model.ServiceInstance;
import org.cloudfoundry.community.servicebroker.model.ServiceInstanceBinding;
import org.cloudfoundry.community.servicebroker.service.ServiceInstanceBindingService;
import org.springframework.stereotype.Service;

@Service
public class SampleServiceInstanceBindingService implements ServiceInstanceBindingService {
	private Map<String, ServiceInstanceBinding> repo = new HashMap<>();

	@Override
	public ServiceInstanceBinding createServiceInstanceBinding(String bindingId, ServiceInstance serviceInstance,
															   String serviceId, String planId, String appGuid)
														throws ServiceInstanceBindingExistsException, ServiceBrokerException {
		ServiceInstanceBinding binding = repo.get(bindingId);
		if (binding != null) {
			throw new ServiceInstanceBindingExistsException(binding);
		}
		
		Map<String,Object> credentials = new HashMap<String,Object>();
		credentials.put("foo", "bar");
		
		binding = new ServiceInstanceBinding(bindingId, serviceInstance.getId(), credentials, null, appGuid);
		repo.put(bindingId, binding);
		
		return binding;
	}

	@Override
	public ServiceInstanceBinding deleteServiceInstanceBinding(String bindingId, ServiceInstance instance, String serviceId,
															   String planId) throws ServiceBrokerException {
		return repo.remove(bindingId);
	}

}

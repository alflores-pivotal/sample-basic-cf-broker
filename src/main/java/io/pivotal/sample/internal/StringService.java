package io.pivotal.sample.internal;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StringService {

	@RequestMapping(method=RequestMethod.GET, value="string-service/{buffer}")
	public ServiceResponse revertString(@PathVariable String buffer) {
		ServiceResponse response = new ServiceResponse();
		response.setResponse(StringUtils.reverse(buffer));
		return response;
	}
}

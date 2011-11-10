package com.redsun.platf.web.webservice;

import java.util.Date;

import javax.jws.WebService;

@WebService(endpointInterface = "com.batchcard.webservice.HelloWorld")

public class HelloWebServiceImpl implements HelloWebService {

	@Override
	public String sayHi(String message) {
		return message+(new Date());
	}

}

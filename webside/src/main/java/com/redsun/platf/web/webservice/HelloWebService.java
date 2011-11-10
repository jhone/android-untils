package com.redsun.platf.web.webservice;

import javax.jws.WebService;

@WebService
public interface HelloWebService {
	String sayHi(String message);
}

package com.hubciti.generaluse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.servicefactory.ServiceFactory;
import com.hubciti.generaluse.service.GeneralUseService;

public class GeneralUseRestEasyControllerImpl implements GeneralUseRestEasyController {

	private static final Logger LOG = (Logger) LoggerFactory.getLogger(GeneralUseRestEasyControllerImpl.class);

	private GeneralUseService generalUseService = ServiceFactory.getGeneralUseService();

	public String testResponse() {
		String response = null;
		response = generalUseService.temp();
		return response;
	}

}

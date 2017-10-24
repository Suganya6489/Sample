package com.hubciti.generaluse.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.generaluse.dao.GeneralUseDao;

public class GeneralUseServiceImpl implements GeneralUseService {

	private static final Logger LOG = LoggerFactory.getLogger(GeneralUseServiceImpl.class);

	private GeneralUseDao generalUseDao;

	public void setGeneralUseDao(GeneralUseDao generalUseDao) {
		this.generalUseDao = generalUseDao;
	}

	public String temp() {
		String response;
		response = generalUseDao.temp();
		return response;
	}

}

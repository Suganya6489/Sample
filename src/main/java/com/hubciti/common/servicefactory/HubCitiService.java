package com.hubciti.common.servicefactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HubCitiService {

	/**
	 * context declared as ClassPathXmlApplicationContext.
	 */
	private static ClassPathXmlApplicationContext context;

	/**
	 * Getting the logger instance.
	 */
	private static Logger LOG = LoggerFactory.getLogger(HubCitiService.class.getName());

	private HubCitiService() {

	}

	private static ClassPathXmlApplicationContext getContext() {

		if (context == null) {
			context = new ClassPathXmlApplicationContext("HubCiti-Service.xml");
			//context = new ClassPathXmlApplicationContext("HubCiti-Service.xml", "net/bull/javamelody/monitoring-spring.xml");
		}
		return context;
	}

	/**
	 * The method for get bean .
	 * 
	 * @param property
	 * @param clazz
	 *            are request parameters.
	 * @return getContext().getBean(property, clazz).
	 */
	public static <T> Object getBean(final String property, final Class<T> clazz) {
		LOG.info("Inside getBean() method");
		return getContext().getBean(property, clazz);
	}

}

package com.hubciti.rest.controller;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.alertevent.controller.AlertEventControllerImpl;
import com.hubciti.band.controller.BandControllerImpl;
import com.hubciti.find.controller.FindControllerImpl;
import com.hubciti.firstuse.controller.FirstUseControllerImpl;
import com.hubciti.gallery.controller.GalleryControllerImpl;
import com.hubciti.generaluse.controller.GeneralUseRestEasyControllerImpl;
import com.hubciti.govqa.controller.GovQAControllerImpl;
import com.hubciti.hotdeals.controller.HotDealsControllerImpl;
import com.hubciti.ratereview.controller.RateReviewControllerImpl;
import com.hubciti.scannow.controller.ScanNowControllerImpl;
import com.hubciti.thislocation.controller.ThisLocationControllerImpl;

public class AttachRestEasyControllersToURI extends Application {

	private static final Logger LOG = LoggerFactory.getLogger(AttachRestEasyControllersToURI.class);
	/**
	 * Getting Set instance.
	 */
	private Set<Object> singletons = new HashSet<Object>();

	public AttachRestEasyControllersToURI() {
		initilizeService();
	}

	/**
	 * This method is used to get singleton objects.
	 * 
	 * @return singletons -a set of root resource and provider instances
	 */
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

	private void initilizeService() {
		
		singletons.add(new GovQAControllerImpl());
		singletons.add(new GeneralUseRestEasyControllerImpl());
		singletons.add(new FirstUseControllerImpl());
		singletons.add(new ThisLocationControllerImpl());
		singletons.add(new FindControllerImpl());
		singletons.add(new HotDealsControllerImpl());
		singletons.add(new RateReviewControllerImpl());
		singletons.add(new GalleryControllerImpl());
		singletons.add(new ScanNowControllerImpl());
		singletons.add(new AlertEventControllerImpl());
		singletons.add(new BandControllerImpl());
		
	}
}

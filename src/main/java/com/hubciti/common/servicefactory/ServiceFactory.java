package com.hubciti.common.servicefactory;

import com.hubciti.alertevent.service.AlertEventService;
import com.hubciti.alertevent.service.AlertEventServiceImpl;
import com.hubciti.band.service.BandService;
import com.hubciti.band.service.BandServiceImpl;
import com.hubciti.find.service.FindService;
import com.hubciti.find.service.FindServiceImpl;
import com.hubciti.firstuse.service.FirstUseService;
import com.hubciti.firstuse.service.FirstUseServiceImpl;
import com.hubciti.gallery.service.GalleryService;
import com.hubciti.gallery.service.GalleryServiceImpl;
import com.hubciti.generaluse.service.GeneralUseService;
import com.hubciti.generaluse.service.GeneralUseServiceImpl;
import com.hubciti.govqa.service.GovQAService;
import com.hubciti.govqa.service.GovQAServiceImpl;
import com.hubciti.hotdeals.service.HotDealsService;
import com.hubciti.hotdeals.service.HotDealsServiceImpl;
import com.hubciti.ratereview.service.RateReviewService;
import com.hubciti.ratereview.service.RateReviewServiceImpl;
import com.hubciti.scannow.service.ScanNowService;
import com.hubciti.scannow.service.ScanNowServiceImpl;
import com.hubciti.thislocation.service.ThisLocationService;
import com.hubciti.thislocation.service.ThisLocationServiceImpl;

public class ServiceFactory {

	private ServiceFactory() {

	}

	public static GeneralUseService getGeneralUseService() {
		return (GeneralUseService) HubCitiService.getBean("generalUseService", GeneralUseServiceImpl.class);
	}

	public static FirstUseService getFirstUseService() {
		return (FirstUseService) HubCitiService.getBean("firstUseService", FirstUseServiceImpl.class);
	}

	/**
	 * @return the ThisLocationService
	 */
	public static ThisLocationService getThisLocationService() {
		return (ThisLocationService) HubCitiService.getBean("thisLocationService", ThisLocationServiceImpl.class);
	}

	public static FindService getFindService() {
		return (FindService) HubCitiService.getBean("findService", FindServiceImpl.class);
	}

	public static HotDealsService getHotDealsService() {
		return (HotDealsService) HubCitiService.getBean("hotDealsService", HotDealsServiceImpl.class);
	}

	public static RateReviewService getRateReviewService() {
		return (RateReviewService) HubCitiService.getBean("rateReviewService", RateReviewServiceImpl.class);
	}

	public static GalleryService getGalleryService() {
		return (GalleryService) HubCitiService.getBean("galleryService", GalleryServiceImpl.class);
	}

	public static ScanNowService getScanNowService() {
		return (ScanNowService) HubCitiService.getBean("scanNowService", ScanNowServiceImpl.class);
	}

	public static AlertEventService getAlertEventService() {
		return (AlertEventService) HubCitiService.getBean("alertEventService", AlertEventServiceImpl.class);
	}
	
	public static GovQAService getGovQAService(){
		return (GovQAService) HubCitiService.getBean("govQAService",GovQAServiceImpl.class);
	}
	
	public static BandService getBandService() {
		return (BandService) HubCitiService.getBean("bandService", BandServiceImpl.class);
	}
}

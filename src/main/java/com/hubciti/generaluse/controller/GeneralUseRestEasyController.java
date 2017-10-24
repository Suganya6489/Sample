package com.hubciti.generaluse.controller;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * The interface for GeneralUse
 * 
 * @author dhruvanath_mm
 */
@Path("/general")
public interface GeneralUseRestEasyController {

	/**
	 * Method for user login
	 * 
	 * @return
	 */
	@GET
	@Path("/temp")
	@Produces("text/xml")
	public String testResponse();
}

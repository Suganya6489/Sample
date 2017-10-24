package com.hubciti.scannow.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.hubciti.common.constants.HubCitiURLPath;

import static com.hubciti.common.constants.HubCitiURLPath.SCANNOWBASEURL;

/**
 * The ScanNowRestEasy Interface.
 * 
 * @author Kumar D
 */
@Path(SCANNOWBASEURL)
public interface ScanNowController {
	/**
	 * This Controller method for fetching product information for The given bar
	 * code,scan type,user id. Method Type:POST Method Type: POST
	 * 
	 * @param xml
	 *            contains bar code,scan type and user id needed to fetch
	 *            product information.
	 * @return XML containing product information in the response.
	 */

	@POST
	@Path(HubCitiURLPath.GETPRODFORBARCODE)
	@Produces("text/xml")
	@Consumes("text/xml")
	String scanProductForBarCode(String xml);

	/**
	 * This Controller method for smart search for fetching product information
	 * for the given search key,user id. Method Type:POST
	 * 
	 * @param xml
	 *            contains search key and user id needed to fetch product
	 *            information. If search key and user id is null then it is
	 *            invalid request.
	 * @return XML containing product information in the response.
	 */

	@POST
	@Path(HubCitiURLPath.GETSMARTSEARCHPRODS)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getSmartSearchProdlist(String xml);

	/**
	 * This Controller method for smart search for fetching product information
	 * for the given search key,user id. Method Type:POST
	 * 
	 * @param xml
	 *            contains search key and user id needed to fetch product
	 *            information. If search key and user id is null then it is
	 *            invalid request.
	 * @return XML containing product information in the response.
	 */

	@POST
	@Path(HubCitiURLPath.GETSMARTSEARCHCOUNT)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getSmartSearchCount(String xml);

	/**
	 * This Controller method for fetching MainMenuID from Module ID. Method
	 * Type:GET
	 * 
	 * @param No
	 *            parameter
	 * @return XML containing MainMenuID
	 */
	@POST
	@Path(HubCitiURLPath.UTGETMAINMENID)
	@Produces("text/xml")
	@Consumes("text/xml")
	String userTrackingGetMainMenuID(String xml);

	/**
	 * This Controller method for displaying smart search products by category
	 * wise the given search key,user id. Method Type:POST
	 * 
	 * @param xml
	 *            contains search key and user id needed to fetch product
	 *            information. If search key and user id is null then it is
	 *            invalid request.
	 * @return XML containing product information in the response.
	 */

	@POST
	@Path(HubCitiURLPath.GETSMARTSEARCHPRODUCTS)
	@Produces("text/xml")
	@Consumes("text/xml")
	String smartSearchProduct(String xml);

	/**
	 * This is a RestEasy WebService Method for fetching product information for
	 * the given search key,user id. Method Type:POST
	 * 
	 * @param userId
	 *            as request parameter in which scan history to be fetched.
	 * @param lastVisitedRecord
	 *            as request parameter for pagination.
	 * @return XML containing scan history information in the response.
	 */
	@GET
	@Path(HubCitiURLPath.GETSCANHISTORY)
	@Produces("text/xml;charset=UTF-8")
	String getScanHistory(@QueryParam("userId") Integer userId, @QueryParam("lastVisitedRecord") Integer lastVisitedRecord,
			@QueryParam("hubCitiId") Integer hubCitiId);

	/**
	 * This is a RestEasy WebService Method to delete scan history items.
	 * 
	 * @param xml
	 *            for which wish list Product to be delete.
	 * @return XML based on success or failure
	 */
	@POST
	@Path(HubCitiURLPath.DELETESCANHISTORYITEM)
	@Produces("text/xml")
	@Consumes("text/xml")
	String deleteScanHistoryItem(String xml);

	/**
	 * This is a RestEasy WebService Method for fetching product information for
	 * the given search key,user id. Method Type:POST
	 * 
	 * @param xml
	 *            contains search key and user id needed to fetch product
	 *            information. If search key and user id is null then it is
	 *            invalid request.
	 * @return XML containing product information in the response.
	 */
	@POST
	@Path(HubCitiURLPath.GETSEARCHPRODFORNAME)
	@Produces("text/xml;charset=UTF-8")
	@Consumes("text/xml")
	String searchAddProductForName(String xml);
}
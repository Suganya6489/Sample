package com.hubciti.ratereview.query;

public class RateReviewQueries {

	/**
	 * For fetching product review details.
	 */
	public static final String GETPRODUCTREVIEWS = "SELECT ProductReviewsID productReviewsID,ProductID productID,ReviewURL reviewURL,ReviewComments reviewComments FROM ProductReviews WHERE  ProductID=?";

	/**
	 * This query used to fetch retailer information with retail id and product
	 * id.
	 */
	public static final String FETCHRETAILERINFOWITHRETAILERINFO = "SELECT R.RetailName retailerName ,RL.Address1 retaileraddress1,RL.Address2 retaileraddress2,"
			+ "RL.Address3 retaileraddress3,RL.Address4 retaileraddress4,RL.City city,RL.State state,RL.PostalCode postalCode,RLP.SalePrice salePrice "
			+ "FROM Retailer R inner join RetailLocation RL on R.RetailID=RL.RetailID inner join RetailLocationProduct RLP on RL.RetailLocationID=RLP.RetailLocationID"
			+ " where RL.RetailID=? and RL.RetailLocationID=? and RLP.ProductID=?";

	/**
	 * This query used to fetch the User email id.
	 */
	public static final String FETCHUSEREMAILID = "SELECT email FROM HcUser WHERE HcUserID = ? ";

}

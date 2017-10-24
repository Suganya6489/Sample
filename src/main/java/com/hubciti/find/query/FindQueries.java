package com.hubciti.find.query;

public class FindQueries {

	/**
	 * This query used to fetch the product information.
	 */
	public static final String PRODUCTINOQUERY = "SELECT p.ProductID, p.ProductName"
			+ " ,p.ManufacturerID, p.ModelNumber, p.ProductShortDescription,p.ProductLongDescription,"
			+ " p.ProductExpirationDate productExpDate, p.ProductImagePath imagePath, p.SuggestedRetailPrice,p.SKUNumber skuCode "
			+ ", p.Weight productWeight,p.ScanCode barCode, p.WeightUnits,M.ManufName manufacturersName FROM Product p LEFT JOIN Manufacturer M ON M.ManufacturerID = P.ManufacturerID WHERE ProductID =?";

	/**
	 * This query used to insert external api call url.
	 */
	public static final String INSERTEXTERNALAPICALURL = "insert into FindNearByLog values(?,?,?)";
}

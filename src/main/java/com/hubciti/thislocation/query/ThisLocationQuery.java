package com.hubciti.thislocation.query;

public class ThisLocationQuery {

	/**
	 * For fetching Lat and Long for the given zipcode.
	 */
	public static final String FETCHLATLONG = "SELECT Latitude, Longitude FROM GeoPosition WHERE PostalCode = ?";

	/**
	 * Query for fetching user latitude and longitude from Database.
	 */
	public static final String FETCHUSERLOCATIONPOINTSQUERY = "SELECT Latitude latitude, Longitude longitude,PostalCode postalCode FROM GeoPosition WHERE PostalCode = (SELECT PostalCode FROM HcUser WHERE HcUserID =?)";
}

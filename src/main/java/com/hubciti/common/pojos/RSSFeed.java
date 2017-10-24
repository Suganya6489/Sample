package com.hubciti.common.pojos;
import java.util.ArrayList;
import java.util.List;

public class RSSFeed {

	private String title;
	
	private String link;
	
	private String description;
	
	private List<RSSFeedMessage> rssFeedMsg = new ArrayList<RSSFeedMessage>();
	
	/**
	 * Constructer
	 */
//	public RSSFeed() { }

	/**
	 * Constructer
	 * 
	 * @param title
	 * @param link
	 * @param description
	 */
	public RSSFeed(String title, String link, String description)	{
		this.title = title;
		this.link = link;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}
	
	/*public void setTitle(String title) {
		this.title = title;
	}*/

	public String getLink() {
		return link;
	}
	
	/*public void setLink(String link) {
		this.link = link;
	}*/

	public String getDescription() {
		return description;
	}
	
	/*public void setDescription(String description) {
		this.description = description;
	}*/

	public List<RSSFeedMessage> getRssFeedMsg() {
		return rssFeedMsg;
	}

	/*public void setRssFeedMsg(List<RSSFeedMessage> rssFeedMsg) {
		this.rssFeedMsg = rssFeedMsg;
	}*/
}

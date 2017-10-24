package com.hubciti.common.helper;

import java.util.Comparator;

import com.net.webqa.WebQAIssue;

public class SortBySummary implements Comparator<WebQAIssue> {

	@Override
	public int compare(WebQAIssue faq1, WebQAIssue faq2) {

		return faq1.getSummary().compareTo(faq2.getSummary());
	}

}

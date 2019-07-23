package com.sevenbridges.http;

import java.util.logging.Logger;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class HttpClient {
	private static final Logger LOGGER = Logger.getLogger(HttpClient.class.getName());

	final static String HTTP_HEADER_AUTHORIZATION = "X-SBG-Auth-Token";

	public static HttpResponse<String> httpGetWithToken(String accessToken, String requestUrl) throws UnirestException {
		LOGGER.fine("Executing http GET request: " + requestUrl);
		HttpResponse<String> httpResponse = Unirest.get(requestUrl)
				.header(HTTP_HEADER_AUTHORIZATION,  accessToken)
				.asString();
		return httpResponse;
	}
}

package com.sevenbridges.http;

import java.util.logging.Logger;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class HttpClient {
	private static final Logger LOGGER = Logger.getLogger(HttpClient.class.getName());

	final static String HTTP_HEADER_AUTHORIZATION = "X-SBG-Auth-Token";
	final static String HTTP_CONTENT_TYPE = "CONTENT-TYPE";
	final static String APPLICATION_JSON = "application/json";

	public static HttpResponse<String> httpGet(String accessToken, String requestUrl) throws UnirestException {
		LOGGER.fine("Executing http GET request: " + requestUrl);
		HttpResponse<String> httpResponse = Unirest.get(requestUrl)
				.header(HTTP_HEADER_AUTHORIZATION,  accessToken)
				.asString();
		return httpResponse;
	}

	public static HttpResponse<String> httpPatch(String accessToken, String requestUrl, String jsonBody) throws UnirestException {
		LOGGER.fine("Executing http PATCH request: " + requestUrl);
		HttpResponse<String> httpResponse = Unirest.patch(requestUrl)
				.header(HTTP_HEADER_AUTHORIZATION,  accessToken)
				.header(HTTP_CONTENT_TYPE, APPLICATION_JSON)
				.body(jsonBody)
				.asString();
		return httpResponse;
	}
}

package com.sevenbridges;

import com.google.gson.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sevenbridges.http.HttpClient;
import com.sevenbridges.http.json.File;
import com.sevenbridges.http.json.Project;
import org.apache.http.client.utils.URIBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CGCClient {
	private static final Logger LOGGER = Logger.getLogger(CGCClient.class.getName());

	public static String V2 = "/v2";
	public static String HTTP_ENDPOINT_PROJECTS = V2 + "/projects";
	public static String HTTP_ENDPOINT_FILES = V2 + "/files";

	String webAddress;
	HttpClient httpClient;
	JsonParser parser;
	Gson gson;

	public CGCClient(String webAddress) {
		this.webAddress = webAddress;
		this.httpClient = new HttpClient();
		this.parser = new JsonParser();
		this.gson = new Gson();
	}

	public List<Project> listProjects(String accessToken) {
		List<Project> result = new ArrayList<Project>();

		try {
			LOGGER.fine("Listing all projects with token");
			String requestUrl = webAddress + HTTP_ENDPOINT_PROJECTS;
			HttpResponse<String> httpResponse = httpClient.httpGetWithToken(accessToken, requestUrl);

			checkHttpResponseCode(httpResponse, 200, "Error while listing projects");
			JsonArray items = getItems(httpResponse);

			for(JsonElement item : items) {
				result.add(gson.fromJson(item, Project.class));
			}

		} catch (Exception e) {
			LOGGER.severe(e.toString());
			e.printStackTrace();
		}
		return result;
	}


	public List<File> listFiles(String accessToken, String projectId) {
		List<File> result = new ArrayList<File>();

		try {
			LOGGER.fine("Listing all files with token");

			URIBuilder builder = new URIBuilder(webAddress + HTTP_ENDPOINT_FILES);
			builder.setParameter("project", projectId);
			String requestUrl = builder.build().toString();

			HttpResponse<String> httpResponse = httpClient.httpGetWithToken(accessToken, requestUrl);

			checkHttpResponseCode(httpResponse, 200, "Error while listing files for project: " + projectId);
			JsonArray items = getItems(httpResponse);

			for(JsonElement item : items) {
				result.add(gson.fromJson(item, File.class));
			}

		} catch (Exception e) {
			LOGGER.severe(e.toString());
			e.printStackTrace();
		}
		return result;
	}

	public File listFileDetails(String accessToken, String fileId) {
		File result = null;

		try {
			LOGGER.fine("Listing defails for file: " + fileId);

			String requestUrl = webAddress + HTTP_ENDPOINT_FILES + "/" + fileId;
			HttpResponse<String> httpResponse = httpClient.httpGetWithToken(accessToken, requestUrl);

			checkHttpResponseCode(httpResponse, 200, "Error while listing file details: file[" + fileId + "]");
			JsonObject item = parser.parse(httpResponse.getBody()).getAsJsonObject();
			result = gson.fromJson(item, File.class);
		} catch (Exception e) {
			LOGGER.severe(e.toString());
			e.printStackTrace();
		}
		return result;
	}

	private JsonArray getItems(HttpResponse<String> httpResponse) {
		JsonObject jsonResponse = parser.parse(httpResponse.getBody()).getAsJsonObject();
		JsonArray data = jsonResponse.get("items").getAsJsonArray();
		return data;
	}

	private void checkHttpResponseCode(HttpResponse<String> httpResponse, int expectedCode, String errorMsg)
			throws UnirestException {
		if (httpResponse.getStatus() != expectedCode) {
			throw new UnirestException(errorMsg);
		}
	}
}

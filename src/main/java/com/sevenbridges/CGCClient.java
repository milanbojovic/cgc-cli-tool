package com.sevenbridges;

import com.google.gson.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sevenbridges.http.HttpClient;
import com.sevenbridges.http.json.File;
import com.sevenbridges.http.json.MetaData;
import com.sevenbridges.http.json.Project;
import com.sevenbridges.http.json.UpdateFileRequest;
import org.apache.http.client.utils.URIBuilder;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CGCClient {
	private static final Logger LOGGER = Logger.getLogger(CGCClient.class.getName());

	public static String V2 = "/v2";
	public static String HTTP_ENDPOINT_PROJECTS = V2 + "/projects";
	public static String HTTP_ENDPOINT_FILES = V2 + "/files";
	public static String HTTP_ENDPOINT_DOWNLOAD_FILE = HTTP_ENDPOINT_FILES + "/{file.id}/download_info";

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
		LOGGER.fine("Listing all projects with token");
		try {
			String requestUrl = webAddress + HTTP_ENDPOINT_PROJECTS;
			HttpResponse<String> httpResponse = httpClient.httpGet(accessToken, requestUrl);

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
		LOGGER.fine("Listing all files with token");
		try {
			URIBuilder builder = new URIBuilder(webAddress + HTTP_ENDPOINT_FILES);
			builder.setParameter("project", projectId);
			String requestUrl = builder.build().toString();

			HttpResponse<String> httpResponse = httpClient.httpGet(accessToken, requestUrl);
			checkHttpResponseCode(httpResponse, 200, "Error while listing files for project " + projectId);
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
		LOGGER.fine("Listing defails for file: " + fileId);
		try {
			String requestUrl = webAddress + HTTP_ENDPOINT_FILES + "/" + fileId;
			HttpResponse<String> httpResponse = httpClient.httpGet(accessToken, requestUrl);

			checkHttpResponseCode(httpResponse, 200, "Error while listing file details for file [" + fileId + "]");
			JsonObject item = parser.parse(httpResponse.getBody()).getAsJsonObject();
			result = gson.fromJson(item, File.class);
		} catch (Exception e) {
			LOGGER.severe(e.toString());
			e.printStackTrace();
		}
		return result;
	}

	public File updateFileDetails(String accessToken, String fileId, UpdateFileRequest updateFileRequest) {
		File result = null;
		LOGGER.fine("Updating defails for file: " + fileId);
		try {
			String requestUrl = webAddress + HTTP_ENDPOINT_FILES + "/" + fileId;
			HttpResponse<String> httpResponse = httpClient.httpPatch(accessToken, requestUrl, gson.toJson(updateFileRequest));

			checkHttpResponseCode(httpResponse, 200, "Error while updating file details for file [" + fileId + "] with body: " + gson.toJson(updateFileRequest));
			JsonObject item = parser.parse(httpResponse.getBody()).getAsJsonObject();
			result = gson.fromJson(item, File.class);
		} catch (Exception e) {
			LOGGER.severe(e.toString());
			e.printStackTrace();
		}
		return result;
	}

	public void downloadFile(String accessToken, String fileId, String destination) {
		LOGGER.fine("Getting file download url : " + fileId);
		try {
			String requestUrl = webAddress + HTTP_ENDPOINT_DOWNLOAD_FILE.replace("{file.id}", fileId);
			HttpResponse<String> httpResponse = httpClient.httpGet(accessToken, requestUrl);

			checkHttpResponseCode(httpResponse, 200, "Error while fetching download URL for file [" + fileId + "]");
			String url = parser.parse(httpResponse.getBody()).getAsJsonObject().get("url").toString();
			URL fileUrl = new URL(url.replaceAll("^\"|\"$", ""));
			java.io.File dstFile = new java.io.File(destination);
			org.apache.commons.io.FileUtils.copyURLToFile(fileUrl, dstFile);

			System.out.println("File download completed.");

		} catch (Exception e) {
			LOGGER.severe(e.toString());
			e.printStackTrace();
		}
	}

	private JsonArray getItems(HttpResponse<String> httpResponse) {
		JsonObject jsonResponse = parser.parse(httpResponse.getBody()).getAsJsonObject();
		JsonArray data = jsonResponse.get("items").getAsJsonArray();
		return data;
	}

	private void checkHttpResponseCode(HttpResponse<String> httpResponse, int expectedCode, String errorMsg)
			throws UnirestException {
		if (httpResponse.getStatus() != expectedCode) {
			throw new UnirestException(errorMsg + httpResponse.getBody());
		}
	}
}

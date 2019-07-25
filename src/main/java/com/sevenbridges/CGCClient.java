package com.sevenbridges;

import com.google.gson.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sevenbridges.http.HttpClient;
import com.sevenbridges.http.json.File;
import com.sevenbridges.http.json.Project;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
		LOGGER.fine("Listing all files with token");
		try {
			URIBuilder builder = new URIBuilder(webAddress + HTTP_ENDPOINT_FILES);
			builder.setParameter("project", projectId);
			String requestUrl = builder.build().toString();

			HttpResponse<String> httpResponse = httpClient.httpGetWithToken(accessToken, requestUrl);
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
			HttpResponse<String> httpResponse = httpClient.httpGetWithToken(accessToken, requestUrl);

			checkHttpResponseCode(httpResponse, 200, "Error while listing file details for file [" + fileId + "]");
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
			HttpResponse<String> httpResponse = httpClient.httpGetWithToken(accessToken, requestUrl);

			checkHttpResponseCode(httpResponse, 200, "Error while fetching download URL for file [" + fileId + "]");
			String url = parser.parse(httpResponse.getBody()).getAsJsonObject().get("url").toString();
			java.io.File dstFile = new java.io.File(destination);

			//URL fileUrl = new URL("https:\\/\\/sb-datasets-us-east-1.s3.amazonaws.com\\/cgl-sgdd-reorg\\/SGDP\\/REMAP_hs37d5\\/LP6005441-DNA_A01.annotated.nh.vcf.gz?x-username=milanbojovic&x-requestId=22f5a357-a429-4495-ba18-58c04d06b38e&x-project=milanbojovic%2Fmilans-genome-diversity-project-mgdp&response-content-disposition=attachment%3Bfilename%3DLP6005441-DNA_A01.annotated.nh.vcf.gz&response-content-type=application%2Foctet-stream&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20190723T160437Z&X-Amz-SignedHeaders=host&X-Amz-Expires=172800&X-Amz-Credential=AKIAJQD4ZMI5SNVG2A2A%2F20190723%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=2c7c47e16a7cd0ba662e0a193667dfcb53875583e1b38b88bc0d3b9f24c4ae59");
			//String url1 = url.split("\\?")[0];
			//String url2 = url.split("\\?")[1];
			//URL fileUrl = new URL(url1 +"?" + URLEncoder.encode(url2, "UTF-8"));

			URL fileUrl = new URL("https://sb-datasets-us-east-1.s3.amazonaws.com/cgl-sgdd-reorg/SGDP/REMAP_hs37d5/LP6005441-DNA_A01.annotated.nh.vcf.gz.tbi?x-username=milanbojovic&x-requestId=9fd0fd66-111c-4279-bd4e-791099d883ad&x-project=milanbojovic%2Fmilans-genome-diversity-project-mgdp&response-content-disposition=attachment%3Bfilename%3DLP6005441-DNA_A01.annotated.nh.vcf.gz.tbi&response-content-type=application%2Foctet-stream&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20190723T184207Z&X-Amz-SignedHeaders=host&X-Amz-Expires=172800&X-Amz-Credential=AKIAJQD4ZMI5SNVG2A2A%2F20190723%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=add7fbe2a7ad85775ac83fbf1a69539cef23c8f7919f89c443eed1b871d90941");
			fileUrl = new URL(StandardCharsets.UTF_8.encode(url).toString());

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
			throw new UnirestException(errorMsg);
		}
	}
}

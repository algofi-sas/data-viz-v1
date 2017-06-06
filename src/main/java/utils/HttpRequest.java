package utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpRequest {

	public static final String JSON_FORMAT = "application/json";
	
	
	private URL url;
	private String content = "";
	private HttpURLConnection con;
	private String method;
	private String stringParams;
	private String contentType;
	private int responseCode;
	private String responseMessage;

	private static String mapToQueryParam(Map<String, String> params){
		String query = "";
		for(String key : params.keySet()){
			query += key + "=" + params.get(key) + "&";
		}
		query = query.substring(0, query.length()-1);
		return query;
	}

	public HttpRequest(String path, String method, String params, String contentType) {
		try {
			this.url = new URL(path);
			this.contentType = contentType;
			this.stringParams = params;
			this.setMethod(method);
			this.con = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public HttpRequest(String path, String method, Map<String, String> params, String contentType) {
		this(path, method, mapToQueryParam(params), contentType);
	}

	public String send() throws IOException {
		con.setRequestProperty("Content-Type", this.contentType);
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(stringParams);
		wr.flush();
		wr.close();
		responseCode = con.getResponseCode();
		responseMessage = con.getResponseMessage();
		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			this.content+=inputLine;
		}
		in.close();
		return this.content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getStringParams() {
		return stringParams;
	}

	public void setStringParams(String stringParams) {
		this.stringParams = stringParams;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

}

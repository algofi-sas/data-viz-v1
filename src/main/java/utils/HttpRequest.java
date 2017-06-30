package utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class HttpRequest {

	public static final String JSON_FORMAT = "application/json";
	
	private String path;
	private URL url;
	private String content = "";
	private HttpURLConnection con;
	private String method;
	private String stringParams;
	private String contentType;
	private int responseCode;
	private String responseMessage;
	private Map<String, String> queryParams;
	
	private static String mapToQueryParam(Map<String, String> params){
		String query = "";
		for(String key : params.keySet()){
			query += key + "=" + params.get(key) + "&";
		}
		query = query.substring(0, query.length()-1);
		return query;
	}

	public HttpRequest(String path, String method, String params, String contentType) {
		init(path, method, params, contentType);
	}

	public HttpRequest(String path, String method, Map<String, String> params, String contentType) {
		this.queryParams = params;
		init(path, method, mapToQueryParam(params), contentType);
	}

	private void init(String path, String method, String params, String contentType){
		this.path = path;
		this.contentType = contentType;
		this.stringParams = params;
		this.setMethod(method);
	}
	
	public String send() throws IOException {
		if(queryParams!=null){
			this.path += "?" + stringParams;
		}
		this.url = new URL(path);
		this.con = (HttpURLConnection) url.openConnection();
		if(this.contentType!=null){
			con.setRequestProperty("Content-Type", this.contentType);
		}
		if(queryParams==null){
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(stringParams);
			wr.flush();
			wr.close();
		}
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

	public static void main(String[] args) {
		String url = "https://www.quandl.com/api/v3/datasets/WIKI/AAPL.json";
		String apiKey = "DKczFdjuL_16KZVxeZKk";
		String startDate = "1985-05-01";
		String endDate = "1997-07-01";
		String limit = "5";
		String order = "asc";
		String collapse = "quarterly";
		String transformation = "rdiff";
		
		Map<String,String> params = new HashMap<String, String>();
		
		params.put("api_key", apiKey);
		params.put("transformation", transformation);
		params.put("collapse", collapse);
		params.put("order", order);
		params.put("end_date", endDate);
		params.put("start_date", startDate);
		params.put("limit", limit);
		
		HttpRequest httpRequest = new HttpRequest(
				url,
				"GET", 
				params,
				null);
		try {
			String result = httpRequest.send();
			JSONObject json = new JSONObject(result);
			System.out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

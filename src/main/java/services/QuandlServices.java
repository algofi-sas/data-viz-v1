package services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import utils.HttpRequest;

@Path("/")
public class QuandlServices {
		
	@GET
	@Path("/dummyRequest")
	@Produces(MediaType.APPLICATION_JSON)
	public Response testQueryParamRESTService(	@QueryParam("dataset_code") String databaseCode,
												@QueryParam("database_code") String datasetCode,
												@QueryParam("start_date") String startDate,
												@QueryParam("end_date") String endDate,
												@QueryParam("limit") String limit,
												@QueryParam("columnIndex") String columnIndex,
												@QueryParam("order") String order,
												@QueryParam("collapse") String collapse,
												@QueryParam("transformation") String transformation) {
		
		String apiKey = "DKczFdjuL_16KZVxeZKk";
		
		
		String url = "https://www.quandl.com/api/v3/datasets/" + datasetCode + "/" + databaseCode + ".json" ;
		Map<String,String> params = new HashMap<String, String>();
		params.put("api_key", apiKey);
		params.put("transformation", transformation);
		params.put("collapse", collapse);
		params.put("order", order);
		params.put("end_date", endDate);
		params.put("start_date", startDate);
		
		
		if(limit!=null){
			if(Integer.valueOf(limit)>0){
				params.put("limit", limit);
			}
		}
		if(columnIndex!=null){
			if(Integer.valueOf(columnIndex)>-1){
				params.put("column_index", columnIndex);
			}
		}
		
		JSONObject jsonResponse = new JSONObject(sendRequest(url, params));
		
		return Response.status(200).header("Access-Control-Allow-Origin", "*").type(MediaType.APPLICATION_JSON).entity(jsonResponse.toString()).build();
	}
	
	public String sendRequest(String url, Map<String,String> params){
		HttpRequest httpRequest = new HttpRequest(
				url,
				"GET", 
				params,
				null);
		String result = "";
		try {
			result = httpRequest.send();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@GET
	@Path("/bitcoinBaseEUR")
	@Produces(MediaType.APPLICATION_JSON)
	public Response bitcoinBaseEURRESTService(	@QueryParam("dataset_code") String databaseCode,
												@QueryParam("database_code") String datasetCode,
												@QueryParam("start_date") String startDate,
												@QueryParam("end_date") String endDate,
												@QueryParam("order") String order,
												@QueryParam("collapse") String collapse,
												@QueryParam("transformation") String transformation) {
		
		String apiKey = "DKczFdjuL_16KZVxeZKk";
		
		String url = "https://www.quandl.com/api/v3/datasets/" + datasetCode + "/" + databaseCode + ".json" ;
		Map<String,String> params = new HashMap<String, String>();
		params.put("api_key", apiKey);
		params.put("transformation", transformation);
		params.put("collapse", collapse);
		params.put("order", order);
		params.put("end_date", endDate);
		params.put("start_date", startDate);
		
		JSONObject jsonResponse = new JSONObject(sendRequest(url, params));
		
		return Response.status(200).header("Access-Control-Allow-Origin", "*").type(MediaType.APPLICATION_JSON).entity(jsonResponse.toString()).build();
	}
	
}

package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import utils.HttpRequest;

/**
 * Servlet implementation class DummyRequest
 */
public class DummyRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DummyRequest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		String apiKey = "DKczFdjuL_16KZVxeZKk";
		
		String databaseCode = request.getParameter("database_code");
		String datasetCode = request.getParameter("dataset_code");
		String startDate = request.getParameter("start_date");
		String endDate = request.getParameter("end_date");
		String limit = request.getParameter("limit");
		String columnIndex = request.getParameter("column_index");
		String order = request.getParameter("order");
		String collapse = request.getParameter("collapse");
		String transformation = request.getParameter("transformation");
		
		
		String url = "https://www.quandl.com/api/v3/datasets/" + databaseCode + "/" + datasetCode + ".json" ;
		
		JSONObject jsonResponse = null;
		
		
		
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
		
		
		HttpRequest httpRequest = new HttpRequest(
				url,
				"GET", 
				params,
				null);
		try {
			String result = httpRequest.send();
			jsonResponse = new JSONObject(result);	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		response.getWriter().append(jsonResponse.toString());
	}

}

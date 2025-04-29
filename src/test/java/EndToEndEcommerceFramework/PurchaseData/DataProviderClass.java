package EndToEndEcommerceFramework.PurchaseData;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataProviderClass {
	
	public List<HashMap<String, String>> getJsonToMap() throws IOException
	{
		
		//Convert json to String
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+ "//src//test//java//EndToEndEcommerceFramework//PurchaseData//Purchase.json")
				, StandardCharsets.UTF_8);
	
		//Converting String to HashMap
		//U need to have JackSon DataBind
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		
		return data;
	
	}
	
	


}

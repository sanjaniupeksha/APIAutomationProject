package Util;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

import java.io.IOException;


public class JasonHandler {

    private RequestSpecification httpRequest;

    public JSONObject ReadJson(String FilePath) throws IOException, ParseException {
        //read json file and save to a jason object
        JSONParser jsonParser = new JSONParser();
        java.io.FileReader reader = new java.io.FileReader(FilePath);
        Object obj = jsonParser.parse(reader);
        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject;
    }

    public RequestSpecification BuildRequest(JSONArray ParameterSet, JSONArray headers )throws Exception{

        JSONObject objects;
        httpRequest = RestAssured.given();

        for(int i = 0; i < headers.size(); i++) // set request header
        {
            objects = (JSONObject) headers.get(i);
            httpRequest.header((String) objects.get("key"),(String)objects.get("value"));
        }

        JSONObject requestParams = new JSONObject(); // set request parameters
        for(int i = 0; i < ParameterSet.size(); i++) {
            objects = (JSONObject) ParameterSet.get(i);
            String value = objects.get("value").toString();

             requestParams.put((String) objects.get("parameter"), (String) objects.get("value"));
        }

        httpRequest.body(requestParams.toString());
        return httpRequest;
    }



    public void validateResponse(Response HttpResponse , String ResponseNode, String ResponeMessage )
    {
        JsonPath JsonResponse = HttpResponse.jsonPath();
        String NodeValue = JsonResponse.get(ResponseNode);
        Assert.assertEquals("success response received",ResponeMessage, NodeValue);
    }

    // Update json request body parameters
    public JSONArray UpdateArrayObjectProperty(JSONArray arrayToBeUpdate, String propKey, String newValue, String updatetype){
        for(int i = 0; i < arrayToBeUpdate.size(); i++)
        {
            if(updatetype.equalsIgnoreCase("invalid_parameter")) {
                if ((((JSONObject) arrayToBeUpdate.get(i)).get("parameter").toString()).equalsIgnoreCase(propKey)) {
                    ((JSONObject) arrayToBeUpdate.get(i)).put("value", newValue);
                    break;
                }
            }else{

                if ((((JSONObject) arrayToBeUpdate.get(i)).get("key").toString()).equalsIgnoreCase(propKey)) {
                    ((JSONObject) arrayToBeUpdate.get(i)).put("value", newValue);
                    break;
                }
            }
        }

        return arrayToBeUpdate;
    }


}

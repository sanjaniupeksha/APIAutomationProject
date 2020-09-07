package Step;

import Util.JasonHandler;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseSteps {

    protected JasonHandler httpReqBuilder =new JasonHandler();
    protected JSONObject APIDataSet;
    protected JSONArray headers;
    protected JSONArray parametersConst;
    protected JSONObject ApiObject;
    protected RequestSpecification httpRequest;
    protected Response httpResponse;
    protected String URL;


    private Logger logger = LoggerFactory.getLogger(BaseSteps.class);

    protected void loadApiConfigurations(String jsonConfigFilePath)  {
        try {
            APIDataSet = this.httpReqBuilder.ReadJson(jsonConfigFilePath);
        }catch (Exception ex)
        {
            logger.error("loadApiConfigurations - >", ex);
        }

    }

    protected JSONArray loadHeaders()   {
          try {
              headers = (JSONArray) APIDataSet.get("header");

          }catch (Exception ex)
        {
            logger.error("loadHeaders - >", ex);
        }
        return headers;
    }

    protected void readConstApiParms(String APIName)
    {
        try {
            // Extract URL and Parameters from Json Object
            ApiObject = (JSONObject)APIDataSet.get(APIName);
            URL = ApiObject.get("apiUrl").toString();
            parametersConst = (JSONArray) ApiObject.get("parameters");

        }catch (Exception ex)
        {
            logger.error("readConstApiParms - >", ex);
        }


    }


    protected void buildApiRequest(JSONArray parameters , JSONArray header)
    {
        try {
            httpRequest = this.httpReqBuilder.BuildRequest(parameters,header);

        }catch (Exception ex)
        {
            logger.error("buildApiRequest - >", ex);
        }

    }

    // update json request parameters before building request
    protected void buildApiRequest(String type , String key , String value)
    {
        try {
            if(type.equalsIgnoreCase("invalid_header"))
            {
                JSONArray header_duplicate = this.httpReqBuilder.UpdateArrayObjectProperty(headers,key,value,type);
                httpRequest = this.httpReqBuilder.BuildRequest(parametersConst,header_duplicate);
            }
            else if(type.equalsIgnoreCase("invalid_parameter"))
            {
                JSONArray parm_duplicate = this.httpReqBuilder.UpdateArrayObjectProperty(parametersConst, key, value,type);
                httpRequest = this.httpReqBuilder.BuildRequest(parm_duplicate,headers);
            }

        }catch (Exception ex)
        {
            logger.error("buildApiRequest - >", ex);
        }


    }

    protected void sendRequest(String Node , String Message)  {
        try {
            httpResponse = httpRequest.post(URL);
            this.httpReqBuilder.validateResponse(httpResponse,Node ,Message);

        }catch (Exception ex)
        {
            logger.error("sendRequest - >", ex);
        }

    }






}

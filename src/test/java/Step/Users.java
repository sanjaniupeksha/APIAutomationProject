package Step;

import Common.FilePaths;
import io.cucumber.java8.En;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Users extends BaseSteps implements En  {

    private Logger logger = LoggerFactory.getLogger(Users.class);

    public Users() {

        Given("^I have \"([^\"]*)\" POST request API endpoint$", (String ApiName) -> {
            try {
                loadApiConfigurations(FilePaths.getFilePath("Partner"));
                loadHeaders();
                readConstApiParms(ApiName);

            }catch (Exception ex)
            {
                logger.error("Users - Given - >", ex);
            }

        });

        When("^I send request HEADER and parameters relates to the request$", () -> {

            try {
                buildApiRequest(parametersConst, headers);

            }catch (Exception ex)
            {
                logger.error("Users - When - >", ex);
            }


        });

        Then("^I should receive response \"([^\"]*)\" as \"([^\"]*)\"$", (String NodeName, String Message) -> {

                sendRequest(NodeName, Message);
        });
        When("^I add \"([^\"]*)\" as \"([^\"]*)\" and \"([^\"]*)\" pairs to the request$", (String RequestParmtype, String Parmkey, String ParmValue) -> {

            try {
                buildApiRequest(RequestParmtype, Parmkey,ParmValue);

            }catch (Exception ex)
            {
                logger.error("Users - When - >", ex);
            }



        });
        Then("^I should receive \"([^\"]*)\" as \"([^\"]*)\" from Partner$", (String NodeName, String Message) -> {
            sendRequest(NodeName, Message);
        });


    }
}

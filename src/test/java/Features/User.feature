Feature:  New User


  Scenario: Create_new_User Success Scenario
    Given I have "createNewUser" POST request API endpoint
    When  I send request HEADER and parameters relates to the request
    Then  I should receive response "result" as "SUCCESS"


  Scenario Outline:Send request with invalid parameters - GetProvisioning
    Given I have "createNewUser" POST request API endpoint
    When  I add "invalid_parameter" as "<Key>" and "<Value>" pairs to the request
    Then  I should receive "result" as "<errorMessage>" from Partner
    Examples:
      | Key    | Value        | errorMessage |
      | name   |              | Invalid      |
      | name   | 234%&*       | Invalid      |
      | name   | 22222222222  | Invalid      |
      | name   | Siresh       | SUCCESS      |
      | gender | Male         | SUCCESS      |
      | gender | Female       | SUCCESS      |
      | gender | RTYU         | Invalid      |
      | gender | R%^^FF       | Invalid      |
      | email  | SLTff        | Invalid      |
      | email  |su@gmail.com  | SUCCESS      |
      | email  | WERFD#$$     | Invalid      |
      | status |              | Invalid      |
      | status | FGHJ%&#@@@@@ | Invalid      |

    # Can have more negative scenarios if written to a actaul Requirnment with an API Spec







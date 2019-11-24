@api
Feature:-Test Automation for Gojek API
As an Gojek user
user should be aple to submit a GET request
So that user can test the GET api response

  @compareapi
  Scenario: Validate Two API Json response
     Given User want to execute API Request as "apiRequest"
      When  User Execute a GET API request
      Then User validate Status Code
      And User validate APIs response for the Excel File one and File two into JSON format
 
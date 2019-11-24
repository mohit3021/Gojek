@api2
Feature: -Test Automation for Gojek API
  As an Gojek user
  user should be aple to submit a GET request
  So that user can test the GET api response

  @compareapi2
  Scenario Outline: Validate Two API Json response
    Given User want to execute API Request as from fileA "<apiRequest1>" and from fileB "<apiRequest2>"
    Then User validate Status Code as "200"
    And User validate APIs response into JSON format

    Examples: 
      | apiRequest1                      | apiRequest2                       |
      | https://reqres.in/api/users/3    | https://reqres.in/api/users/2     |
      | https://reqres.in/api/users/1    | https://reqres.in/api/users/3     |
      | https://reqres.in/api/users/2    | /api/unknown/2                    |
      | https://reqres.in/api/user?page2 | https://reqres.in/api/user?page=2 |
      | https://reqres.in/api/user?page1 | https://reqres.in/api/user?page=1 |

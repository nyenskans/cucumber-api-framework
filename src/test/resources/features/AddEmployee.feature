Feature: This feature is  going to add employees in HRMS application

  Background:
    When user enters valid admin username and password
    And user clicks on login button
    Then admin user is successfully logged in
    When user clicks on PIM option
    And user clicks on add employee option

  @smoke
  Scenario: Add an employee
    When user enters firstName , middleName and lastName
    And user clicks on save button
    Then employee added successfully

  @override
  Scenario: Adding one employee from cucumber feature file
    When user enters "oman" , "tagai" and "gihid"
    And user clicks on save button
    Then employee added successfully

  @dataprovider
  Scenario Outline: Adding multiple employees from cucumber feature file
    When user enter "<firstName>" , "<middleName>" and "<lastName>"
    And user clicks on save button
    Then employee added successfully
    Examples:
      |firstName|middleName|lastName|
      |romid    |MS        |zarif   |
      |rokan    |MS        |elisa   |
      |mama     |tarindi   |jamu    |
      # we pass all the sets of data (values of keys) in a table
      # the amount of data we can pass is still limited (we cannot pass a vast amount in a feature file tr)

  @datatable
  Scenario: Adding multiple employees using data table
    When user adds multiple employees and verify they are added
    |firstname|middleName|lastName|
    |Loki     | Lokac    |Pecikoza|
    |Risi     | Risko    |Sibinski|
    |Tedi     | Teda     |Susic   |
    |Boban    | Bobi     |Susic   |
  # Only one test will be executed for all data sets, not three
  # When we use data tables, the background, hooks, will only be executed once for the entire scenario, not once for
  # each key-value pair


  # Scenario: Verify dashboard tabs--> new feature file
 @excel
  Scenario: Adding employees from excel file
    When user adds multiple employees from excel file using "employeeData" sheet and verify the employees has added



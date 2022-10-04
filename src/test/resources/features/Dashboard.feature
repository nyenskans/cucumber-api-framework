Feature:  Dashboard functionality

  @dashboard
Scenario: Verify dashboard tabs
    When user enters valid admin username and password
    And user clicks on login button
    Then admin user is successfully logged in
    # now we need to implement a data table without keys (no header) only values--> tabs
    # we will only have a list (not list of maps) of tabs
    # it needs to be a list that will maintain the order so we can verify
    # data table works for only one step so we write it righ after that step and then the rest of steps if any
    Then verify all the dashboard tabs
    |Admin|PIM|Leave|Time|Recruitment|Performance|Dashboard|Directory|


Feature: API workflow resource
  Background:
    Given a JWT is generated

  @api
  Scenario: Create the employee
    # we want this step as the first step of each scenario:
    # Given a JWT is generated
    Given a request is prepared for creating an employee
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the employee created contains key "Message" and value "Employee Created"
        # we need to capture the employee id to be able to perform further actions
    And the employee id "Employee.employee_id" is stored as global variable

    @api
  Scenario: Retrieving recently created employee
  #  Given a JWT is generated
    Given a request is prepared for getting the created employee
    When a GET call is made to get the details of the employee
    Then the status code is 200
    And the employee id "employee.employee_id" must match the globally stored employee id
    And the received data from "employee" object matches the data used to create that employee
    # data used to create the employee is in APIPayloadConstants (body of the post request)
    # now we use a datatable with the values from the payload
    # when we use the datatable in feature file, in steps we use a list of maps to get those values and verify them to the actual ones
    |emp_firstname|emp_lastname|emp_middle_name|emp_gender|emp_birthday|emp_status|emp_job_title|
    |Azeddine     |Sterling    |M              |Male      |2022-10-03  |Hired     |QA           |


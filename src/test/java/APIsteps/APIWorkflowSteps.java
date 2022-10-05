package APIsteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.APIPayloadConstants;
import utils.APIconstants;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class APIWorkflowSteps {
    // we need to define the following variables on a class level to be reusable in each of the step definitions:
    RequestSpecification request;
    Response response;
    public static String employee_ID; // this is a global variable so we declare it on class level

    @Given("a request is prepared for creating an employee")
    public void a_request_is_prepared_for_creating_an_employee() {
        request = given().header(APIconstants.HEADER_CONTENT_TYPE, APIconstants.CONTENT_TYPE_VALUE).
                header(APIconstants.HEADER_AUTHORIZATION, GenerateTokenSteps.token).
                body(APIPayloadConstants.createEmployeePayload());
    }

    @When("a POST call is made to create an employee")
    public void a_post_call_is_made_to_create_an_employee() {
        // now in our post() we call the CREATE_EMPLOYEE_URI variable from APIconstants class
        response = request.when().post(APIconstants.CREATE_EMPLOYEE_URI);
    }
    // instead of hardcoding the status code we are passing it as a parameter in the step definition

    @Then("the status code for creating an employee is {int}")
    public void the_status_code_for_creating_an_employee_is(Integer statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("the employee created contains key {string} and value {string}")
    // make sure the appropriate message is displayed in the body of the response:
    public void the_employee_created_contains_key_and_value(String key, String value) {
        response.then().body(key, equalTo(value));
    }

    @Then("the employee id {string} is stored as global variable")
    public void the_employee_id_is_stored_as_global_variable(String empID) {
        // this is how we retrieve the value of the key employeeID - by using .jsonPath()
        employee_ID = response.jsonPath().getString(empID);
        System.out.println(employee_ID);
    }


    // Second scenario:
    // Scenario: Retrieving recently created employee

    @Given("a request is prepared for getting the created employee")
    public void a_request_is_prepared_for_getting_the_created_employee() {
        request = given().
                header(APIconstants.HEADER_CONTENT_TYPE, APIconstants.CONTENT_TYPE_VALUE).
                header(APIconstants.HEADER_AUTHORIZATION, GenerateTokenSteps.token).
                param("employee_id", employee_ID);
    }

    @When("a GET call is made to get the details of the employee")
    public void a_get_call_is_made_to_get_the_details_of_the_employee() {
        response = request.when().get(APIconstants.GET_ONE_EMPLOYEE_URI);
    }

    @Then("the status code is {int}")
    public void the_status_code_is(int statusCode) {
        // now we are making sure that the status code from response equals to parameter statusCOde
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("the employee id {string} must match the globally stored employee id")
    public void the_employee_id_must_match_the_globally_stored_employee_id(String employeeID) {
        String tempEmployeeId = response.jsonPath().getString(employeeID);
        Assert.assertEquals(tempEmployeeId, employee_ID);
    }

    @Then("the received data from {string} object matches the data used to create that employee")
    public void the_received_data_from_object_matches_the_data_used_to_create_that_employee(String employeeObject, io.cucumber.datatable.DataTable dataTable) {


        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.

        // this is the data that we get from the feature file data table
        List<Map<String, String>> expectedData = dataTable.asMaps();
        // this way we get the entire body from the response i.e. the entire Employee object
        // and store it in a map
        Map<String, String> actualData = response.body().jsonPath().get(employeeObject);

        // now we iterate through the expected data
        for (Map<String, String> expectedDataMap : expectedData) {
            // and then we get all the keys and save it into a Set (because set doesn't contain duplicates)
            Set<String> keys = expectedDataMap.keySet();
            // now we use another loop to get keys one by one:
            for (String key : keys) {
                // next line gives us the value of the key - from the feature file
                String expectedValue = expectedDataMap.get(key);
                // next line gives us the value of keys in actual values - from response
                String actualValue = actualData.get(key);
                Assert.assertEquals(actualValue, expectedValue);
            }
        }
    }

    @Given("a request is prepared for creating an employee via json payload")
    public void aRequestIsPreparedForCreatingAnEmployeeViaJsonPayload() {
        request = given().
                header(APIconstants.HEADER_AUTHORIZATION, GenerateTokenSteps.token).
                header(APIconstants.HEADER_CONTENT_TYPE, APIconstants.CONTENT_TYPE_VALUE).
                body(APIPayloadConstants.createEmployeePayloadJson());
    }
    @Given("a request is prepared for creating an employee using more dynamic payload {string}, {string}, " +
            "{string}, {string}, {string} , {string} , {string}")
    public void a_request_is_prepared_for_creating_an_employee_using_more_dynamic_payload
            (String firstName, String lastName, String middleName,
             String gender, String birthday,
             String empStatus, String jobTitle) {

      request = given().header(APIconstants.HEADER_CONTENT_TYPE, APIconstants.CONTENT_TYPE_VALUE).
                        header(APIconstants.HEADER_AUTHORIZATION, GenerateTokenSteps.token).
                        body(APIPayloadConstants.createDynamicEmployeePayloadJson(firstName, lastName,
                        middleName, gender,birthday, empStatus, jobTitle));
    }

    @Given("a request is prepared for updating an employee")
    public void aRequestIsPreparedForUpdatingAnEmployee() {
        request = given().header(APIconstants.HEADER_AUTHORIZATION, GenerateTokenSteps.token).
                header(APIconstants.HEADER_CONTENT_TYPE, APIconstants.CONTENT_TYPE_VALUE).
        body(APIPayloadConstants.updateEmployee());
    }

    @When("a PUT call is made to updating an employee")
    public void aPUTCallIsMadeToUpdatingAnEmployee() {
        response = request.when().put(APIconstants.UPDATE_EMPLOYEE_URI);
    }
}




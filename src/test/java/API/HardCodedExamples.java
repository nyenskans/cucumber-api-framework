package API;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

// In jUnit, tests are executed in order; to change that we use annotation @FixMethodOrder before Class:
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {
    String BaseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjQ4ODQxNzAsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY2NDkyNzM3MCwidXNlcklkIjoiNDU4OSJ9.K1_eNfsP5F7NJ9VkIDSx2nVbcISJplfaAgGYIxHoPUE";
    static String employeeId; // we declare empId var (that we got bellow in line 61) so that we can use it in the future anywhere
    // we use test annotation from jUnit to run the tests
    // to create a regular user
    @Test
    public void aCreateEmployee() {
        // prepare a request
        // Post: we pass all the tabs in given() part
        // And we have to save it in a variable
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json").
                header("Authorization", token).body("{\n" +
                        "  \"emp_firstname\": \"Azeddine\",\n" +
                        "  \"emp_lastname\": \"Sterling\",\n" +
                        "  \"emp_middle_name\": \"M\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2022-10-03\",\n" +
                        "  \"emp_status\": \"hired\",\n" +
                        "  \"emp_job_title\": \"QA\"\n" +
                        "}");

        // Next we hit the endpoint:
        Response response = preparedRequest.when().post("/createEmployee.php");
        // as a result, we get the response

        // in the console we use plugin pretty to print the response:
        response.prettyPrint();

        // when we run this class, the response is identical to the one in Postman when we create an employee

        // We use assertions to compare the values from our requests to data we get in response
        // Assertions and validation --> assertions are always written in test (check the status code of the request)
        //                             --> assertions are always written in .then() - result
        response.then().assertThat().statusCode(201);
        // how to get the keys-values from json objects in Postman response:
        String middleName = "Employee.emp_middle_name";
        // Assert the middle name is the same as expected
        response.then().assertThat().body(middleName, equalTo("M"));
        // mouse over equalTo-> import static method->matchers method --> import static org.hamcrest.CoreMatchers.equalTo;
        // Assert that the firstname is same as expected (employee is an object)
        response.then().assertThat().body("Employee.emp_firstname", equalTo("Azeddine"));
        // Assert that the message is the same as expected: message is part of the body, not an object
        response.then().assertThat().body("Message", equalTo("Employee Created"));
        // verify the response headers --> when we execute the POST we see the response headers in the console:
        response.then().assertThat().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");
        // we don't use equalTo because this is not in the response body
        // to get an employee we need to use the id used to create it
        // get employee id from the body
        employeeId = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employeeId);
    }
        @Test
         public void bGetCreatedEmployee(){
             // prepare the request
            RequestSpecification preparedRequest = given().
                    header("Content-Type", "application/json").
                    header("Authorization", token).
                    queryParam("employee_id", employeeId);
            // hit the endpoint
            Response response = preparedRequest.when().get("/getOneEmployee.php");
            response.prettyPrint();
            response.then().assertThat().statusCode(200);
        }

        @Test
        public void cUpdateEmployee(){
            RequestSpecification preparedRequest = given().
                    header("Authorization", token).
                    header("Content-Type", "application/json").
                    body("{\n" +
                            "  \"employee_id\": \""+ employeeId+"\",\n" +
                            "  \"emp_firstname\": \"John\",\n" +
                            "  \"emp_lastname\": \"Smith\",\n" +
                            "  \"emp_middle_name\": \"KK\",\n" +
                            "  \"emp_gender\": \"M\",\n" +
                            "  \"emp_birthday\": \"2022-10-03\",\n" +
                            "  \"emp_status\": \"hired\",\n" +
                            "  \"emp_job_title\": \"QA\"\n" +
                            "}");
            Response response = preparedRequest.when().put("/updateEmployee.php");
            response.then().assertThat().statusCode(200);
        }
        @Test
        public void dGetUpdatedEmployee(){
        RequestSpecification preparedRequest = given().
                header("Authorization", token).
                header("Content-Type", "application/json").queryParam("employee_id", employeeId);
        Response response = preparedRequest.when().get("/getOneEmployee.php");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("employee.emp_firstname", equalTo("John"));
        }
    }



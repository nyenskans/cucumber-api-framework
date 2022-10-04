package utils;

public class APIPayloadConstants {
    // here we store everything that goes into the body of the request in Postman
    // so that when we are implementing a given step  request.given().header().body()
    // the parameters passed in .body() look more readable
    public static String createEmployeePayload(){
        String createEmployeePayload = "{\n" +
                "  \"emp_firstname\": \"Azeddine\",\n" +
                "  \"emp_lastname\": \"Sterling\",\n" +
                "  \"emp_middle_name\": \"M\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"2022-10-03\",\n" +
                "  \"emp_status\": \"hired\",\n" +
                "  \"emp_job_title\": \"QA\"\n" +
                "}";
        return createEmployeePayload;
    }
}

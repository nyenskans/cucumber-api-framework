package utils;

import APIsteps.APIWorkflowSteps;
import org.json.JSONObject;

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


    // we are rewriting the above method to be more readable
    // Json object is in a form of a map- key and value pairs
    // we use put method for maps to insert the keys and values
    public static String createEmployeePayloadJson(){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", "Azeddine");
        obj.put("emp_lastname", "Sterling");
        obj.put("emp_middle_name", "M");
        obj.put("emp_gender", "M");
        obj.put("emp_birthday", "2020-10-04");
        obj.put("emp_status", "Hired");
        obj.put("emp_job_title", "QA");
        return obj.toString();
    }
    public static String createDynamicEmployeePayloadJson(String firstName, String lastName, String middleName,
             String gender, String birthDay, String empStatus, String jobTitle){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", firstName);
        obj.put("emp_lastname", lastName);
        obj.put("emp_middle_name", middleName);
        obj.put("emp_gender", gender);
        obj.put("emp_birthday", birthDay);
        obj.put("emp_status", empStatus);
        obj.put("emp_job_title", jobTitle);
        return obj.toString();
    }

    public static String updateEmployee(){
        JSONObject obj = new JSONObject();
        obj.put("employee_id", "43555A");
        obj.put("emp_firstname", "John");
        obj.put("emp_lastname", "Smith");
        obj.put("emp_middle_name", "KK");
        obj.put("emp_gender", "M");
        obj.put("emp_birthday", "2020-10-03");
        obj.put("emp_status", "hired");
        obj.put("emp_job_title", "QA");
        return obj.toString();
    }
}

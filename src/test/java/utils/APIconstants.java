package utils;

public class APIconstants {
    // can store baseUri and all the endpoints in constants
    // everything related to the api
    public static final String baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    public static final String GENERATE_TOKEN_URI = baseURI + "/generateToken.php";
    public static final String CREATE_EMPLOYEE_URI = baseURI + "/createEmployee.php";
    public static final String GET_ONE_EMPLOYEE_URI = baseURI + "/getOneEmployee.php";
    public static final String GET_ALL_EMPLOYEES_URI = baseURI + "/getAllEmployees.php";
    public static final String UPDATE_EMPLOYEE_URI = baseURI + "/updateEmployee.php";
    public static final String GET_EMPLOYEE_STATUS_URI = baseURI + "employeementStatus.php";
    public static final String DELETE_EMPLOYEE_URI = baseURI + "/deleteEmployee.php";

    // header content type
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    // value of that header
    public static final String CONTENT_TYPE_VALUE = "application/json";
    // header authorization
    public static final String HEADER_AUTHORIZATION = "Authorization";
    // value of that header we have already defined as variable token in GenerateTokenSteps class


}

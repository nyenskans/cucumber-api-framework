package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.AddEmployeePage;
import utils.CommonMethods;
import utils.Constants;
import utils.ExcelReader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {
    @When("user clicks on add employee option")
    public void user_clicks_on_add_employee_option() {
        click(dash.addEmployeeOption);
    }

    @When("user enters firstName , middleName and lastName")
    public void user_enters_first_name_middle_name_and_last_name() {
        sendText(addEmployeePage.firstName, "gisel");
        sendText(addEmployeePage.middleName, "francis");
        sendText(addEmployeePage.lastName, "arif");
    }

    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        click(addEmployeePage.saveButton);
    }

    @Then("employee added successfully")
    public void employee_added_successfully() {
        //homework - verify added employee
        System.out.println("Employee added");
    }

    @When("user enters {string} , {string} and {string}")
    public void user_enters_and(String firstName, String middleName, String lastName) {
        sendText(addEmployeePage.firstName, firstName);
        sendText(addEmployeePage.middleName, middleName);
        sendText(addEmployeePage.lastName, lastName);
    }

    @When("user enter {string} , {string} and {string}")
    public void user_enter_and(String fn, String mn, String ln) {
        sendText(addEmployeePage.firstName, fn);
        sendText(addEmployeePage.middleName, mn);
        sendText(addEmployeePage.lastName, ln);
    }


    @When("user adds multiple employees and verify they are added")
    public void user_adds_multiple_employees_and_verify_they_are_added(DataTable dataTable) throws InterruptedException {
        // we use cucumber built in method which converts the data table from feature file into a list of Map --> .asMaps()
        // we store that data in a List of maps () called employeeNames
        List<Map<String, String>> employeeNames = dataTable.asMaps();
        // to iterate over elements of a list we use for each loop:
        // it returns multiple maps (multiple key-value pairs)
        for (Map<String, String> emp : employeeNames) {
            String firstNameValue = emp.get("firstName");
            String middleNameValue = emp.get("middleName");
            String lastNameValue = emp.get("lastName");
            sendText(addEmployeePage.firstName, firstNameValue);
            sendText(addEmployeePage.middleName, middleNameValue);
            sendText(addEmployeePage.lastName, lastNameValue);
            click(addEmployeePage.saveButton);

            // we don't want to execute hooks multiple times
            // we don't want to execute background multiple times so we have to:
            // click on Add employee option to add another employee
            // until then we will be getting fresh values from feature file

            Thread.sleep(2000);
            click(dash.addEmployeeOption);
            Thread.sleep(2000);

            // verify that the employee is added using Assertion


        }
    }
    @When("user adds multiple employees from excel file using {string} sheet and verify the employees has added")
    public void user_adds_multiple_employees_from_excel_file_using_sheet_and_verify_the_employees_has_added(String sheetName) throws InterruptedException {
        List<Map<String, String>> newEmployees = ExcelReader.excelListIntoMap(Constants.TEST_DATA_FILEPATH, sheetName);
        Iterator<Map<String, String>> itr = newEmployees.iterator();
        while(itr.hasNext()){
            // returns one map in each iteration:
            Map<String, String> mapNewEmp = itr.next();
            // pass the data from excel file in the fields for
//            String fn = mapNewEmp.get("firstName");
//            String mn = mapNewEmp.get("middleName");
//            String ln = mapNewEmp.get("lastName");

            // OR:

            sendText(addEmployeePage.firstName, mapNewEmp.get("firstName"));
            sendText(addEmployeePage.middleName, mapNewEmp.get("middleName"));
            sendText(addEmployeePage.lastName, mapNewEmp.get("lastName"));
            // upload photograph
            sendText(addEmployeePage.photograph, mapNewEmp.get("photograph"));

            // check if checkbox is selected--> if not click:
        if(!addEmployeePage.checkBox.isSelected()){
            click(addEmployeePage.checkBox);
        }
        sendText(addEmployeePage.usernameEmployee, mapNewEmp.get("username"));
        sendText(addEmployeePage.passwordEmployee, mapNewEmp.get("password"));
        sendText(addEmployeePage.confirmPasswordEmployee, mapNewEmp.get("confirmPassword"));

        // Homework Assertion

        // first: capture employee id for each employee and save it in variable:
            String empIdValue = addEmployeePage.empIdLoc.getAttribute("value");
            System.out.println(empIdValue);
            Thread.sleep(3000);
        // next click on save for each employee we create (in each iteration)
            jsClick(addEmployeePage.saveButton);
            Thread.sleep(3000);
            // till this point we have created an employee, got their id and saved
        // now click add employee
        // click(dash.addEmployeeOption); doesn't work, we have to use jscript click:
            jsClick(dash.employeeListOption);
            Thread.sleep(5000);
        // now we want to search for the employee by the id we saved:
            sendText(emp.idEmployeeSearch, empIdValue);
            jsClick(emp.searchButton);
         // verify the details of the employee:
         // when we run a search for employee by id, we should only get one result - one employee (in the form of a table)
         // we will check that by verifying there is only one row : (one tr in dom)
        List<WebElement> rowData = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));
        // if there is multiple results for our search --> we will get the list and the assertion will fail
        // we write a loop to get all the data from the search
            for(int i=0; i< rowData.size(); i++){
                System.out.println("I am inside the loop");
                // this line returns text for each row for available elements we got as result of the search
                String rowText = rowData.get(i).getText();
                System.out.println(rowText);
                String expectedData = empIdValue + " " + mapNewEmp.get("firstName") + " " + mapNewEmp.get("middleName")
                        + " " + mapNewEmp.get("lastName");

                System.out.println(expectedData);
                Assert.assertEquals(expectedData, rowText);
            }

            Thread.sleep(2000);
            // in each iteration we have to click on this button
            click(dash.addEmployeeOption);

        }
    }

        // Homework Personal details page - modify the details of employee after adding it in the hrms
}

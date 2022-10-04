package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;

import java.util.ArrayList;
import java.util.List;

public class DashboardSteps extends CommonMethods {
    @Then("verify all the dashboard tabs")
    public void verify_all_the_dashboard_tabs(DataTable dataTable) { // pass DataTable object
        // list of expected values --> we take them from feature file
        // it will be stored in the form of list (not list of maps) because it contains only values not keys
        List<String> expectedTabs = dataTable.asList();

        // List of actual tab values --> we are getting them from the web page
        //we are just declaring it right now and the data we get in for loop:
        List<String> actualTabs = new ArrayList<>();

            // now we are iterating through the tabs (WebElements from the dashboard page)
        for (WebElement element : dash.dashboardTabs){
            // then we get the text from each element and we add it to the list of actual tabs to be able to compare actual and expected
            actualTabs.add(element.getText());
        }
        System.out.println(actualTabs);
        System.out.println(expectedTabs);
        Assert.assertTrue(expectedTabs.equals(actualTabs));
    }



}

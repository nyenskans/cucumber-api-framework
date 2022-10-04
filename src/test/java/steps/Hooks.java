package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.CommonMethods;

public class Hooks extends CommonMethods {
        //import io.cucumber and not junit

        @Before
        public void start(){
            openBrowserAndLaunchApplication();
        }

        @After
        public void end(Scenario scenario){ // when we add screenshots, we pass and import this Scenario class from cucumber
            // Scenario class holds complete the information about the execution in Cucumber
            byte[] pic;
            // first we need to save the screenshot in our framework:
            if(scenario.isFailed()){
                pic = takeScreenshot("failed/"+scenario.getName());
            }else{
                pic = takeScreenshot("passed/"+scenario.getName());
            }
            // then we have to attach the screenshot into the report:
            scenario.attach(pic, "image/png", scenario.getName());
            // we want to save screenshots of passed tests in one location, and failed in another
            closeBrowser();
        }


}

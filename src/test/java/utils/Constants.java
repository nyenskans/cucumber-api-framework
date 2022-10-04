package utils;


public class Constants {
    public static final String CONFIGURATION_FILEPATH = "/Users/katarinasusic/IdeaProjects/Cucumber/src/test/resources/config/config.properties";
    //  public static final String CONFIGURATION_FILEPATH = System.getProperty("user.dir") + "/src/test/resources/config/config.properties";
    //  "src/test/java/utils/Constants" -- > this is relative path (path of file in project)
    // (System.getProperty("user.dir") --> it gives you the location of the project on the computer
    public static final int IMPLICIT_WAIT = 20;
    public static final int EXPLICIT_WAIT = 10;
    // path for our excel file with test data
    public static final String TEST_DATA_FILEPATH = "/Users/katarinasusic/IdeaProjects/Cucumber/src/test/resources/testData/Batch13ExcelFile.xlsx";
    public static final String SCREENSHOT_FILEPATH = System.getProperty("user.dir")+"/screenshots/";
}

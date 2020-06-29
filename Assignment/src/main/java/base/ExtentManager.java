package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.util.Date;

public class ExtentManager {

    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;
    public static String reportName;

    public static void createReporter(){
        Date date=java.util.Calendar.getInstance().getTime();
        reportName=date.toString().replaceAll(" ",".").replaceAll(":",".");

        htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/extentReports/"+reportName+"_report.html");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Functional testing");
        htmlReporter.config().setTheme(Theme.DARK);

        if(extent==null)
             extent=new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host name","localHost");
        extent.setSystemInfo("Environment","QA");
        extent.setSystemInfo("User","Sid");
    }

    public static ExtentReports getExtent(){
        return extent;
    }

    public static ExtentTest getTest(){
        return test;
    }
}

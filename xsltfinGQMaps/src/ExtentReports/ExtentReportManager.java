package ExtentReports;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReportManager {
public static ExtentReports extent;
    
    public static ExtentReports getInstance() {

            extent = new ExtentReports("C:\\GQMaps_workspace\\xsltfinGQMaps\\ExtentReports\\output\\GQM_2.html", true);
            
            // optional
            extent.config()
                .documentTitle("Automation Report")
                .reportName("Regression")
                .reportHeadline("");
               
            // optional
            extent
                .addSystemInfo("Selenium Version", "2.46")
                .addSystemInfo("Environment", "QA");

        return extent;
    }

}

package ExtentReports;
import java.io.File;
import java.io.IOException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.ErrorCode;



public class NewLogForEachRunFileAppender extends FileAppender {


	 @Override
	    public void setFile(String fileName)
	    {
	      
	        super.setFile(fileName);
	   }

}
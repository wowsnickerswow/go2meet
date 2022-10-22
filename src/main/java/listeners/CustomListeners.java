package listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;

import base.Page;
import utilities.Utilities;

public  class CustomListeners extends Page implements ITestListener{
	
	   public void onFinish(ITestContext arg0) {					
	        // TODO Auto-generated method stub				
	        		
	    }		

	    public void onStart(ITestContext arg0) {		
	    	
	        // TODO Auto-generated method stub				
	        		
	    }		

	    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {					
	        // TODO Auto-generated method stub				
	        		
	    }		
	    
	    public void onTestSkipped(ITestResult arg0) {					
	        // TODO Auto-generated method stub			
	    	test.log(LogStatus.SKIP, arg0.getName().toUpperCase() +" Skipped as run mode is no");
	        		
			 rep.endTest(test);
			 rep.flush();
	    }	
		

	    public void onTestFailure(ITestResult arg0) {	
	    	
			 System.setProperty("org.uncommons.reportng.escape-output", "false");
			 try {
				Utilities.captureScreenShot();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    	test.log(LogStatus.FAIL, arg0.getName().toUpperCase()+" FAILED with exp:" + arg0.getThrowable());
		    	test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.ScreenshotName));


		    	
			 Reporter.log("Capturing screenshot");
			 Reporter.log("<br>");
			 Reporter.log("<a target = \"blank\" href="+Utilities.ScreenshotName+"> ScreenShot<a>");
			 rep.endTest(test);
			 rep.flush();
			 
	    	
	    }		

	    		
	

	    		
	    public void onTestStart(ITestResult arg0) {					
	        // TODO Auto-generated method stub		
	    	test = rep.startTest(arg0.getName().toUpperCase());
	    	//runmodels Y
	    	System.out.println(Utilities.isTestRunnable(arg0.getName().toUpperCase() , excel));
	    	
	    	if(!Utilities.isTestRunnable(arg0.getName().toUpperCase() , excel)) {
	    		
	    		throw new SkipException("Skipped via runmode excel file" + arg0.getName().toUpperCase() + "As run mode of NO");
	    	}
	        		
	    }		

	    		
	    public void onTestSuccess(ITestResult arg0) {		
	    	test.log(LogStatus.PASS, arg0.getName().toUpperCase()+" PASS");
	    	rep.endTest(test);
	    	rep.flush();
	        // TODO Auto-generated method stub			
	    	
	        		
	    }		
	

}

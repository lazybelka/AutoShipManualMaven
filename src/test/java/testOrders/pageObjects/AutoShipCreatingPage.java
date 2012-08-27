package testOrders.pageObjects;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;

import testOrders.helper.TestHelper;

public class AutoShipCreatingPage extends TestHelper{
	
	static Date date = new Date();
	static SimpleDateFormat exactDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	static String dateStr = exactDateFormat.format(date);
	
	public static String autoShipFolderName = new String();
	
	public AutoShipCreatingPage(){
		
	}

	public AutoShipCreatingPage openAutoShipCreatingPage(){
		open(autoShipCreatingPage + TestOrdersPage.invoiceNumber);
		
		return this;
	}
	
	public static boolean isAutoshipCreated(){
		wait(5);
		waitFor(By.tagName("pre"));
		if (getText(By.tagName("pre")).contains("New autoship order created")){
			return true;
		}
		else{
			Date date = new Date();
			SimpleDateFormat exactDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			takeScreenShotInFolder("Error_autoship_creating" + exactDateFormat.format(date), OfferOutlookPage.folderName);
			System.out.println("Getting autoship creating error! Look at screenshot in SKU folder");
			return false;
		}
		
	}
	
	public CustomerCardPage returnToCustomerCardPage() {
		open(customerCardPage + TestOrdersPage.customerId);
		waitFor(By.className("generalinfo_generalpanel"));
		wait(4);
		
		return new CustomerCardPage();
	} 
	
	public AutoShipCreatingPage createAutoshipFolder() {
		wait(2);
		autoShipFolderName = "TestResults/" + exactDateFormat.format(date) + "_" + "Autoship" + "_" + ItemDetailsPage.SKU;
		File createdFolder = new File(autoShipFolderName);
		createdFolder.mkdirs();
		return this;
	}
	
}

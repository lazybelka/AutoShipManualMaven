package testOrders.pageObjects;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;

import testOrders.helper.TestHelper;

public class ItemDetailsPage extends TestHelper{
	
	public static  String SKU = new String();
	
	public static boolean isAuto;
	
	static Date date = new Date();
	static SimpleDateFormat exactDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	static String dateStr = exactDateFormat.format(date);
	
	public ItemDetailsPage(){
		
	}
	
	public ItemDetailsPage takeSKU() {
		waitFor(By.cssSelector("div[class='title rendered']"));
		wait(2);
		SKU = getText(By.id("edititem"));
		System.out.println("SKU " + SKU + " was entered");
		
		return this;
	}
	
	public OfferOutlookPage openOfferOutlookPage() {
		open(offerOutlookPage + SKU);
		
		return new OfferOutlookPage();
	}
	
	public ItemDetailsPage isAutoShip(){
		isAuto = false;
		waitFor(By.id("isAutoship"));
		if (getText(By.id("isAutoship")).equals("Y")){
			isAuto = true;
		}
		
		return this;
	}

}

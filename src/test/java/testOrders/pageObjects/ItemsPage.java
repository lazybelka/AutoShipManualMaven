package testOrders.pageObjects;

import org.openqa.selenium.By;

import testOrders.helper.TestHelper;

public class ItemsPage extends TestHelper{
	
	public ItemsPage(){
		
	}
	
	public ItemDetailsPage enterSKU(){
		waitFor(By.id("newtitle"));
		System.out.println("Enter SKU and press OpenItem");
		
		return new ItemDetailsPage();
	}

}

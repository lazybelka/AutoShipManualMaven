package testOrders.pageObjects;

import java.io.FileNotFoundException;

import org.openqa.selenium.By;

import testOrders.helper.TestHelper;

public class OfferOutlookPage extends TestHelper{
	
	public OfferOutlookPage(){
		
	}
	
	public OfferOutlookPage saveOfferOutlookPage(){
		waitFor(By.linkText("save page as PDF"));
		click(By.linkText("save page as PDF"));
		wait(5);
		try {
			fileMigration(ItemDetailsPage.SKU + ".pdf", ItemDetailsPage.folderName + "/" + ItemDetailsPage.SKU + ".pdf");
		}
		catch(FileNotFoundException e){
			System.out.println("File not found!");
		}
		
		return this;
	}
	
	public TestOrdersPage openTestOrdersPage() {
		open(testOrdersPage + ItemDetailsPage.SKU);
		
		return new TestOrdersPage();
	}

}

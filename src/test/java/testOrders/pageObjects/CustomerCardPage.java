package testOrders.pageObjects;

import java.io.FileNotFoundException;

import org.openqa.selenium.By;

import testOrders.helper.TestHelper;

public class CustomerCardPage extends TestHelper{
	
	public static String orderNumber = new String();
	public static String autoShipInvoiceNumber = new String();
	
	public CustomerCardPage(){
		
	}

	public CustomerCardPage takeOrderNumber() {
		waitFor(By.id("headerToChange"));
		orderNumber = getText(By.id("headerToChange")).substring(20);
		System.out.println("Order number is " + orderNumber);
		
		return this;
	}

	public shipScriptPage shipOrder() {
		open(shipScriptPage + orderNumber);
		
		return new shipScriptPage();
	}

	public CustomerCardPage isOrderShipped() {
		wait(10);
		waitFor(By.id("commentID"));
		String orderStatus = new String();
		orderStatus = getText(By.cssSelector("label[id*='infoSaleStatus']"));
		
		if (orderStatus.contains("SHIPPED")){
			System.out.println("Order is shipped");
		}
		else {
			System.out.println("Error! Order status is " + orderStatus);
		}
		
		return this;
	}
	
	public CustomerCardPage takeCustomerCardScreenShoots(){
		wait(3);
		waitFor(By.id("commentID"));
		takeScreenShotInFolder("Order_Info-" + ItemDetailsPage.dateStr, OfferOutlookPage.folderName);
		
		waitFor(By.className("orderinfo_marketing"));
		click(By.className("orderinfo_marketing"));
		wait(4);
		takeScreenShotInFolder("Marketing_Info-" + ItemDetailsPage.dateStr, OfferOutlookPage.folderName);
		
		waitFor(By.className("orderinfo_history"));
		click(By.className("orderinfo_history"));
		wait(4);
		takeScreenShotInFolder("Order_History-" + ItemDetailsPage.dateStr, OfferOutlookPage.folderName);
		
		waitFor(By.className("orderinfo_general"));
		click(By.className("orderinfo_general"));
		wait(4);
		waitFor(By.className("paymentButton"));
		click(By.className("paymentButton"));
		wait(4);
		takeScreenShotInFolder("Advanced_Payments-" + ItemDetailsPage.dateStr, OfferOutlookPage.folderName);
		
		waitFor(By.className("generalinfo_generalpanel"));
		click(By.className("generalinfo_generalpanel"));
		wait(4);
		takeScreenShotInFolder("General_Panel-" + ItemDetailsPage.dateStr, OfferOutlookPage.folderName);
		
		waitFor(By.className("orderinfo_autoship"));
		click(By.className("orderinfo_autoship"));
		wait(4);
		takeScreenShotInFolder("Auto_Ship-" + ItemDetailsPage.dateStr, OfferOutlookPage.folderName);
		
		return this;
	}

	public ViewPackingPage saveViewPackingPage(String path){
		wait(2);
		if(isElementPresent(By.className("shipped"))){
			click(By.className("shipped"));
		}
		else {
			takeScreenShotInFolder("Order_Status-" + ItemDetailsPage.dateStr, path);
		}
		wait(5);
		try {
			fileMigration("PackingSlip.pdf",path + "/PackingSlip.pdf");
		}
		catch(FileNotFoundException e){
			System.out.println("File not found!");
		}
		return new ViewPackingPage();
	}

	public CustomerCardPage takeAutoShipInvoiceNumber() {
		waitFor(By.cssSelector("a[rel='0']"));
		autoShipInvoiceNumber = getText(By.cssSelector("a[rel='0']"));
		click(By.cssSelector("a[rel='0']"));
		System.out.println("Auto ship invoice number is " + autoShipInvoiceNumber);
		return this;
	}

	public CustomerCardPage takeAutoshipScreenShoots() {
		wait(3);
		waitFor(By.id("commentID"));
		takeScreenShotInFolder("Autoship-" + "Order_Info-" + ItemDetailsPage.dateStr, AutoShipCreatingPage.autoShipFolderName);
		
		waitFor(By.className("orderinfo_marketing"));
		click(By.className("orderinfo_marketing"));
		wait(4);
		takeScreenShotInFolder("Autoship-" + "Marketing_Info-" + ItemDetailsPage.dateStr, AutoShipCreatingPage.autoShipFolderName);
		
		waitFor(By.className("orderinfo_history"));
		click(By.className("orderinfo_history"));
		wait(4);
		takeScreenShotInFolder("Autoship-" + "Order_History-" + ItemDetailsPage.dateStr, AutoShipCreatingPage.autoShipFolderName);
		
		waitFor(By.className("orderinfo_general"));
		click(By.className("orderinfo_general"));
		wait(4);
		waitFor(By.className("paymentButton"));
		click(By.className("paymentButton"));
		wait(4);
		takeScreenShotInFolder("Autoship-" + "Advanced_Payments-" + ItemDetailsPage.dateStr, AutoShipCreatingPage.autoShipFolderName);
		
		waitFor(By.className("generalinfo_generalpanel"));
		click(By.className("generalinfo_generalpanel"));
		wait(4);
		takeScreenShotInFolder("Autoship-" + "General_Panel-" + ItemDetailsPage.dateStr, AutoShipCreatingPage.autoShipFolderName);
		
		waitFor(By.className("orderinfo_autoship"));
		click(By.className("orderinfo_autoship"));
		wait(4);
		takeScreenShotInFolder("Autoship-" + "Auto_Ship-" + ItemDetailsPage.dateStr, AutoShipCreatingPage.autoShipFolderName);
		return this;
	}
	
	public CustomerCardPage returnToCustomerCardPage() {
		wait(10);
		open(customerCardPage + "order/" + TestOrdersPage.invoiceNumber);
		
		return this;
	}
	
	public CustomerCardPage returnToNewCustomerCardPage() {
		wait(10);
		open(customerCardPage + "order/" + CustomerCardPage.autoShipInvoiceNumber);
		
		return this;
	}

}

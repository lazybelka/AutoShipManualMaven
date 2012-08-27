package testOrders.tests;

import org.testng.annotations.Test;

import testOrders.helper.TestHelper;
import testOrders.pageObjects.AutoShipCreatingPage;
import testOrders.pageObjects.ItemDetailsPage;
import testOrders.pageObjects.MattminLogIn;
import testOrders.pageObjects.OfferOutlookPage;
import testOrders.pageObjects.TestOrdersPage;

public class TestManualSku extends TestHelper{
	
	@Test
	public void ManualEnterSkuTest(){
		new MattminLogIn()
		.logIn()
		.clickHereToContinue()
		.openItemsPage() 
		.enterSKU() //MST-BALM-25ML-30-C
		.takeSKU()
		.isAutoShip()
		.openOfferOutlookPage()
		.createSKUFolder(ItemDetailsPage.SKU)
		.saveOfferOutlookPage()
		.openTestOrdersPage()
		.fillTestValues()
		.waitForInvoiceNumber();
		if (noSuchElementException) {
			new TestOrdersPage()
			.invoiceNumberErrorProcessing();
		}
		else {
			new TestOrdersPage()
			.takeInvoiceNumberAndCustomerId()
			.openCustomerCardPage()
			.takeOrderNumber()
			.shipOrder()
			.returnToCustomerCardPage()
			.isOrderShipped()
			.takeCustomerCardScreenShoots()
			.returnToCustomerCardPage()
			.saveViewPackingPage(OfferOutlookPage.folderName);
			
			if (ItemDetailsPage.isAuto){
				new AutoShipCreatingPage()
				.createAutoshipFolder()
				.openAutoShipCreatingPage();
				if (AutoShipCreatingPage.isAutoshipCreated() ){
					new AutoShipCreatingPage()
					.returnToCustomerCardPage()
					.takeAutoShipInvoiceNumber()
					.takeOrderNumber()
					.shipOrder()
					.returnToNewCustomerCardPage()
					.isOrderShipped()
					.takeAutoshipScreenShoots()
					.returnToNewCustomerCardPage()
					.saveViewPackingPage(AutoShipCreatingPage.autoShipFolderName)
					
					;
				}
			}
			
			;
		}
	}

}

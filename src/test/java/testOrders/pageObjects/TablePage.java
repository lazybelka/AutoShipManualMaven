package testOrders.pageObjects;

import testOrders.helper.TestHelper;

public class TablePage extends TestHelper{
	
	public TablePage(){
		
	}

	public ItemsPage openItemsPage(){
		open(itemsPage);
		
		return new ItemsPage();
	}
	
	public TestOrdersPage openTestOrdersPage() {
		open(testOrdersPage);
		
		return new TestOrdersPage();
	}

}

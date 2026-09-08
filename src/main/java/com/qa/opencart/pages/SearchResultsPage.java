package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By searchResult = By.cssSelector("div.product-thumb");

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public int getSearchResultsCount() {
		List<WebElement> resultsList =
				eleUtil.waitForVisibilityOfElemenetsLocated(searchResult, TimeUtil.DEFAULT_MEDIUM_TIME);
		
		int resultCount = resultsList.size();//0
		System.out.println("product search results count ====" + resultCount);
		return resultCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		eleUtil.doClick(By.linkText(productName), TimeUtil.DEFAULT_TIME);
		return new ProductInfoPage(driver);
	}
	

}

package recruitment.cmc.com.pages;
<<<<<<< HEAD

=======
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
>>>>>>> ccb0c075d59ddda2efdf19b86d661da2046a133e

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import recruitment.cmc.com.settings.ExcelUtils;
import recruitment.cmc.com.settings.NewsInfo;
import recruitment.cmc.com.settings.URL;


public class NewsPage extends BasePage {
	WebDriver driver;
	@FindBy(xpath = "(//li[@class='nav-item']//a[@href='/tin-tuc?lang=vi'])[2]")
	WebElement buttonNews;

	// @FindBy(xpath = "//a[normalize-space()='4 Ưu tiên của CFO trong năm 2020 cho
	// nền tảng tài chính doanh nghiệp vững chắc']")
	@FindBy(xpath = "(//i[@class='fa fa-angle-double-right'])[1]")
	WebElement news;

	@FindBy(xpath = "//button[@id='btn-like-fb']")
	WebElement buttonLike;
	
	// Begin of dunghtt1
	@FindBy(xpath = "//ul[@class='nav navbar-nav pull-right']//a[@href='/tin-tuc?lang=vi']")
	WebElement linkNews;
	
	@FindBy(xpath = "//ul[@class='list-new']")
	WebElement ulNews;
	
	@FindAll(@FindBy(xpath = "//ul[@class='list-new']/li"))
	List<WebElement> allNews;
	
	@FindBy(xpath = "//div[@class='title-sp']")
	WebElement eTitle;
	
	@FindBy(xpath = "//div[@class='content-detail']//div[@class='text']")
	WebElement eSubContent;
	
	@FindBy(xpath = "//div[@class='img-sp']/img")
	WebElement eImg;
	
	@FindBy(xpath = "//div[@class='note-sp']/span[@class='time']")
	WebElement eTime;
	
	@FindAll(@FindBy(xpath = "//div[@class='bot']/div[@class='text']"))
	List<WebElement> eDetail;
	
	public NewsPage(WebDriver driver) {
		super(driver);
	}

	public String pressLikeButton() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		buttonNews.click();
		news.click();
		buttonLike.click();
		wait.until(ExpectedConditions.alertIsPresent());
		String text = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		return text;
		
	}
	
	public String pressLikeButtonLogged() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		BasePage basepage = new BasePage(driver);
		basepage.login();
		buttonNews.click();
		news.click();
		buttonLike.isDisplayed();
		if(buttonLike.getText().equalsIgnoreCase("Yêu thích")) {
			buttonLike.click();
			wait.until(ExpectedConditions.elementToBeClickable(buttonLike));
			return buttonLike.getText();
		}else {
			return buttonLike.getText();
		}
	}
	
	public String pressUnLikeButtonLogged() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		BasePage basepage = new BasePage(driver);
		basepage.login();
		buttonNews.click();
		news.click();
		buttonLike.isDisplayed();
		if(buttonLike.getText().equalsIgnoreCase("Đã thích")) {
			buttonLike.click();
			wait.until(ExpectedConditions.elementToBeClickable(buttonLike));
			return buttonLike.getText();
		}else {
			buttonLike.click();
			buttonLike.click();
			wait.until(ExpectedConditions.elementToBeClickable(buttonLike));
			return buttonLike.getText();
		}
	}
	
<<<<<<< HEAD
	/*
	 * public String getAPI() { // Specify the base URL to the RESTful web service
	 * RestAssured.baseURI = "https://tuyendung.cmc.com.vn";
	 * 
	 * RequestSpecification httpRequest = RestAssured.given().params("idUser",
	 * "3237", "idNews", "68"); Response response = httpRequest.request(Method.POST,
	 * "/portal/interactnews/is_like"); return response.getBody().print(); }
	 */
=======
	// Begin of dunghtt1
	//Get the number of menus on the job page - From file
	public int getNumberMenuFromFile() throws Exception {
		ExcelUtils.setExcelFile(URL.File_NewsData, "Sheet1");
		return ExcelUtils.getTotalRow();
	}

	// Get list of menu of the job page - From file
	public ArrayList<NewsInfo> getListMenuFromFile() throws Exception {
		ArrayList<NewsInfo> arrNews = new ArrayList<NewsInfo>();
		ExcelUtils.setExcelFile(URL.File_NewsData, "Sheet1");
		int rowCount = ExcelUtils.getTotalRow();
		for (int i = 1; i < rowCount + 1; i++) {
			String subTitle = ExcelUtils.getCellData(i, 1);
			String urlBanner = ExcelUtils.getCellData(i, 2);
			String subContent = ExcelUtils.getCellData(i, 3);
			String detailContent = ExcelUtils.getCellData(i, 4);
			String postDate = ExcelUtils.getCellData(i, 5);
			arrNews.add(new NewsInfo(subTitle, urlBanner, subContent, detailContent, postDate));
		}
		return arrNews;
	}
	
	//Check display list of the News
	public boolean getStatusOfNewsList() throws Exception {
		
		linkNews.click();		
		ArrayList<NewsInfo> arrNewsFile = new ArrayList<NewsInfo>();
		arrNewsFile = getListMenuFromFile();
		boolean resultFind = false;
		for (int i = 0; i < allNews.size(); i++) {
			WebElement title = allNews.get(i).findElement(By.className("title-sp"));
			WebElement content = allNews.get(i).findElement(By.className("text"));
			WebElement img = allNews.get(i).findElement(By.tagName("img"));
			String subTitle = title.getText();
			String urlBanner = img.getAttribute("src").toString();
			String subContent = content.getText();			
			
			for (int j = 0; j < arrNewsFile.size(); j++) {				
				resultFind = arrNewsFile.get(j).subTitle.equalsIgnoreCase(subTitle);
				resultFind = resultFind && urlBanner.contains(arrNewsFile.get(j).urlBanner);
				resultFind = resultFind && subContent.contains(arrNewsFile.get(j).subContent);
				if (resultFind) {
					continue;
				}
			}
		}
		return resultFind;
	}
	
	//Check display detail of the News
	public boolean getStatusDetailOfNews() throws Exception {
		
		ArrayList<NewsInfo> arrNewsFile = new ArrayList<NewsInfo>();
		arrNewsFile = getListMenuFromFile();
		boolean resultFind = false;
		
		linkNews.click();
		
		WebElement btnDetail = allNews.get(0).findElement(By.className("read-more"));
		btnDetail.click();
		
		waitForElementVisible(5,eTitle);
		String subTitle = eTitle.getText();		
		String urlBanner = eImg.getAttribute("src").toString();		
		String subContent = eSubContent.getText();
		String postDate = eTime.getText();
		String detailContent = eDetail.get(1).getText();
	
		resultFind = arrNewsFile.get(0).subTitle.equalsIgnoreCase(subTitle);		
		resultFind = resultFind && urlBanner.contains(arrNewsFile.get(0).urlBanner);		
		resultFind = resultFind && subContent.contains(arrNewsFile.get(0).subContent);		
		resultFind = resultFind && postDate.contains(arrNewsFile.get(0).postDate);		
		resultFind = resultFind && detailContent.contains(arrNewsFile.get(0).detailContent);		
		return resultFind;
	}	
	// End of dunghtt1
>>>>>>> ccb0c075d59ddda2efdf19b86d661da2046a133e
}

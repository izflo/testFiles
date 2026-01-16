/*
  [주말과제 1 - ExSeleniumLand.java]
    Selenium을 활용해서 직방(zigbang.com) 웹사이트내의
    역삼동 아파트 매매/전월세 정보를 크롤링하여 매물명과 가격정보 출력
*/

package network.ex;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExSeleniumLand {

	private static final String URL = "https://www.zigbang.com/";
	WebElement ele;

	public static void main(String[] args) {

		// 크롬드라이버 경로 설정
		System.setProperty("webdriver.chrome.driver", "D:/pub2511/driver/chromedriver.exe");
		
		// 안정성 향상을 위한 옵션 추가
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-gpu");
		
		// 크롬 드라이버
		WebDriver driver = new ChromeDriver(options);
		
		// waiting 시간 설정
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		try {
			
			// 직방 HTML 페이지에 접근
			driver.get(URL);
			
			// 화면에 아파트가 보이면 클릭
			wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//div[contains(@id, \"animatedComponent\") and contains(text(), \"아파트\")]"))).click();
			
			// 검색창에 "역삼동" 입력하기 (sendKeys("역삼동"))
			wait.until(ExpectedConditions.elementToBeClickable(
					//// *[@id="__next"]/div[2]/div/div/div/div/div/div[2]/div[1]/div[1]/div/div[1]/input
					/// div.input-wrap > input[type=text]
					By.cssSelector("input[placeholder = \"아파트, 지역, 지하철역, 학교 검색\"]")
					// input[placeholder = "아파트, 지역, 지하철역, 학교 검색"]
					)).sendKeys("역삼동");
			
			Thread.sleep(3000);
			
			// 찾기 버튼 클릭하기
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title=\"검색\"]"))).click();
			
			// 역삼동 주변 매물보기
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), \"역삼동 주변 매물보기\")]"))).click();
			
			Thread.sleep(1000);
			// 매매, 전세, 월세
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), \"매매, 전세, 월세\")]"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), \"적용하기\")]"))).click();
			
			Thread.sleep(1000);
			// 정보 수집
//			List<WebElement> aptList = driver.findElements(By.cssSelector("#__next div.css-1dbjc4n:nth-child(3)>div>div:nth-child(n+3)>div>div"));
			// 계속 스크롤하는 작업도 있어야함*************
			List<WebElement> aptList = driver.findElements(By.cssSelector("div.css-1dbjc4n.r-1loqt21.r-1otgn73.r-lrvibr>div"));
			aptList.stream().forEach(apt -> {
				if(apt.getText().startsWith("역삼동"))
					System.out.println(apt.getText());
			});
			
			
			// 매매/전월세 정보를 크롤링하여 매물명과 가격정보 출력
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			driver.quit();
		}

	}// main


}// class

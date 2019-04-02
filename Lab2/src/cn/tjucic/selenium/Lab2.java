package cn.tjucic.selenium;

import java.util.regex.Pattern;
import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Lab2 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	  String driverPath = System.getProperty("user.dir") + "/src/resources/driver/geckodriver.exe";
	  System.setProperty("webdriver.gecko.driver", driverPath);
	  driver = new FirefoxDriver();
	  baseUrl = "http://121.193.130.195:8800";
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testBaidu() throws Exception {
    driver.get(baseUrl + "/");
    File file = new File("C:/Users/LIKAI/Desktop/在读课程/软件测试基础/Lab/Lab2/软件测试名单.xlsx");
	FileInputStream fis = null;
	Workbook workBook = null;
	if (file.exists()) {
	    try {
		fis = new FileInputStream(file);
		workBook = WorkbookFactory.create(fis);
		int numberOfSheets = workBook.getNumberOfSheets();
		// sheet工作表
		for (int s = 0; s < numberOfSheets; s++) {
		    Sheet sheetAt = workBook.getSheetAt(s);
		    //获取工作表名称
		    String sheetName = sheetAt.getSheetName();
		    // 获取当前Sheet的总行数
		    int rowsOfSheet = sheetAt.getPhysicalNumberOfRows();
            String number="",id="",name="",giturl="";
            Row row = sheetAt.getRow(0);
            Cell cell0 = row.getCell(0);			    
            Cell cell1 = row.getCell(1);			    
            Cell cell2 = row.getCell(2);			    
            Cell cell3 = row.getCell(3);			    
		   for (int i = 2; i < rowsOfSheet; i++) {
		    	row = sheetAt.getRow(i);
		    	//获取单元格
		        cell0 = row.getCell(0);			    
		        cell1 = row.getCell(1);			    
		        cell2 = row.getCell(2);			    
		        cell3 = row.getCell(3);
		    	//设置单元格类型
		    	cell0.setCellType(CellType.STRING);
		    	cell1.setCellType(CellType.STRING);
		    	cell2.setCellType(CellType.STRING);
		    	cell3.setCellType(CellType.STRING);
                 number = cell0.getStringCellValue();
                 id = cell1.getStringCellValue();
                 name = cell2.getStringCellValue();
                 giturl = cell3.getStringCellValue();
		         //System.out.println("当前行:" + i +" " +number +" "+ id + " " + name +" "+giturl);
                 driver.findElement(By.name("id")).sendKeys(id);
                 driver.findElement(By.name("password")).sendKeys(id.substring(4,10));
                 //driver.findElement(By.id("btn_login")).click();
                 WebElement login = driver.findElement(By.id("btn_login"));
                 login.sendKeys(Keys.ENTER);
                 assertEquals(id, driver.findElement(By.id("student-id")).getText());
                 assertEquals(name, driver.findElement(By.id("student-name")).getText());
                 assertEquals(giturl, driver.findElement(By.id("student-git")).getText());
                 WebElement logout = driver.findElement(By.id("btn_logout"));
                 logout.sendKeys(Keys.ENTER);
                 WebElement btn_return = driver.findElement(By.id("btn_return"));
                 btn_return.sendKeys(Keys.ENTER);
                 }
	        }
	        if (fis != null) {
		    fis.close();
	        }
	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
        } else {
	    System.out.println("文件不存在!");
        }
 
    //driver.findElement(By.name("id")).sendKeys("3016218058");
    //driver.findElement(By.name("password")).sendKeys("3016218058");
    //driver.findElement(By.id("su")).click();
    //assertEquals("天津大学_百度搜索", driver.getTitle());
  }

  @After
  public void tearDown() throws Exception {
//    driver.quit();
//    String verificationErrorString = verificationErrors.toString();
//    if (!"".equals(verificationErrorString)) {
//      fail(verificationErrorString);
//    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}


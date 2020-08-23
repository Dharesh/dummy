package genric;
//interface
//Abstraction : Constants declared in the Interface
//why Interface ??
//we have declared the interface Auto_const because the some of the file path never 
//change throught the project 
public interface Auto_const {
	String chrome_key= "webdriver.chrome.driver";
	String chrome_value="./drivers/chromedriver.exe";
	String gecko_key= "webdriver.gecko.driver";
	String gecko_Value="./drivers/geckodriver.exe";
	String photo_path="./defects/";
	String excel_path="./excel/data.xlsx";
	
}

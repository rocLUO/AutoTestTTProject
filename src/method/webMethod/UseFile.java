package method.webMethod;

import baseFile.WebBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UseFile {

    public UseFile(String s) {

    }

    public static void upLoadFileByName(WebDriver Driver, String ElementName, String ElementLocat, String FilePath) throws InterruptedException {

        try {
            WebElement adFileUpload = null;
            if (ElementName == "id") {
                adFileUpload = Driver.findElement(By.id(ElementLocat));
            } else if (ElementName == "xpath") {

                WebElement adFileUpLoad = Driver.findElement(By.xpath(ElementLocat));
            }
            adFileUpload.sendKeys(FilePath);

//            Driver.findElement(By.name(Name)).sendKeys(Path);
        } catch (Error e) {
            e.printStackTrace();
            WebBase.screenSnap(Driver);
        }
    }
}

package test;

import Util.TestListener;
import driver.DriverSingletone;
import org.testng.Assert;
import org.testng.annotations.*;
import page.CalculatorPage;
import page.MailPage;
import page.MainPage;
import page.ReadMailPage;
import service.Switcher;

@Listeners({TestListener.class})
public class CompareSumFromMailAndCalculatorPageTableTest {
    MainPage mainPage=new MainPage(DriverSingletone.getDriver());
    CalculatorPage calculatorPage =
            new CalculatorPage(DriverSingletone.getDriver());
    MailPage mailPage=new MailPage(DriverSingletone.getDriver());
    ReadMailPage readMailPage=new ReadMailPage(DriverSingletone.getDriver());
    String TotalCostFromCakculatorPage;
    String TotalCostFromReadMailPage;

    @Test
    public void compareSumFromTableAndCalculatorPage() {
        mainPage.openPage().inputAndFindDataInSearch().clickCloudCalculator().switchFrame();
        calculatorPage.findAndClickCalculatorElements();
        Switcher.openPageInNewWindow();
        mailPage.openPage();
        mailPage.copyEmail();
        Switcher.switchToFirstPage();
        calculatorPage.switchFrame();
        TotalCostFromCakculatorPage = calculatorPage.getTotalCost().getText();
        calculatorPage.pasteEmail();
        calculatorPage.sendEmail();
        Switcher.switchToSecondPage();
        mailPage.openLetter();
        TotalCostFromReadMailPage = readMailPage.getTotalCost().getText();
        Assert.assertTrue((TotalCostFromCakculatorPage).
                contains(TotalCostFromReadMailPage)
        );
    }
        @AfterTest
        public void webQuit(){
           DriverSingletone.getDriver().quit();

        }
    }





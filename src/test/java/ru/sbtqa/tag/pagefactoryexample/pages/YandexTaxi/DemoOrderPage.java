package ru.sbtqa.tag.pagefactoryexample.pages.YandexTaxi;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.sbtqa.tag.pagefactory.Page;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;
import ru.sbtqa.tag.pagefactory.exceptions.PageException;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;
import static ru.sbtqa.tag.pagefactory.PageFactory.getDriver;

@PageEntry(title = "Демо-заказ такси")
public class DemoOrderPage extends Page {

    @ElementTitle("Заголовок")
    @FindBy(xpath = "//input[contains(@id,'addressFrom')]")
    private TextInput addressFrom;

    @ElementTitle("Куда, Когда")
    @FindBy(xpath = "//span[contains(@data-html,'order.route')]")
    private WebElement orderRoute;


    public DemoOrderPage() {
        PageFactory.initElements(
                new HtmlElementDecorator(new HtmlElementLocatorFactory(getDriver())), this);
    }

    @ActionTitle("проверяет, что поле содержит значение")
    public void checkFieldValue(String webElemName, String value) throws PageException {
        WebElement webElement = PageFactory.getInstance().getCurrentPage().getElementByTitle(webElemName);
        Assert.assertEquals("Oups", value, webElement.getText());
    }

    @ActionTitle("ожидает появление поля")
    public void waitTillElementPresent(String webElemName) {
        new WebDriverWait(PageFactory.getWebDriver(), PageFactory.getTimeOutInSeconds())
                .until(ExpectedConditions.visibilityOf(orderRoute));
    }

}

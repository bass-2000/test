package ru.sbtqa.tag.pagefactoryexample.pages.YandexTaxi;

import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.sbtqa.tag.pagefactory.Page;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;
import ru.sbtqa.tag.pagefactory.exceptions.PageException;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.*;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.awt.*;

@PageEntry(title = "Яндекс Такси")
public class YtMainPage extends Page {

    @ElementTitle("Откуда")
    @FindBy(xpath = "//input[contains(@id,'addressFrom')]")
    private TextInput addressFrom;

    @ElementTitle("Очистить поле Откуда")
    @FindBy(xpath = "//input[contains(@id,'addressFrom')]/../span[contains(@class,'input__clear')]")
    private Button addressFromClear;

    @ElementTitle("Куда")
    @FindBy(xpath = "//input[contains(@id,'addressTo')]")
    private TextInput addressTo;

    @ElementTitle("Очистить поле Куда")
    @FindBy(xpath = "//input[contains(@id,'addressTo')]/../span[contains(@class,'input__clear')]")
    private TextInput addressToClear;

    @ElementTitle("Поле выбора времени подачи")
    @FindBy(xpath = "//button[contains(@id,'datetimeSelect')]")
    private Button datetimeSelect;

    @ElementTitle("вариант Через 10 минут")
    @FindBy(xpath = "//div/span[contains(text(),'Через 10 минут')]/..")
    private Button afterTenMinutes;

    @ElementTitle("вариант На ближайшее время")
    @FindBy(xpath = "//div/span[contains(text(),'На ближайшее время')]/..")
    private Button verySoon;

    @ElementTitle("Поле выбора Требований")
    @FindBy(xpath = "//button[contains(@class,'button_preset_requirements')]")
    private Button buttonPresetRequirements;

    @ElementTitle("Чек-бокс детское кресло")
    @FindBy(xpath = "//div[contains(@class,'requirements-select')]/span/span[contains(@class, 'checkbox__box')]")
    private CheckBox childChairCheckbox;

    @ElementTitle("Тип сиденья 9-18кг")
    @FindBy(xpath = "//div[contains(@class,'requirements__item-select')]/span/label[contains(@class, 'radio-button__radio_side_left')]")
    private Radio radioSideLeft;

    @ElementTitle("Тип сиденья 15-25кг")
    @FindBy(xpath = "//div[contains(@class,'requirements__item-select')]/span/label/span[contains(text(), '15-25')]/..")
    private Radio radioSideMiddle;

    @ElementTitle("Тип сиденья 22-36кг")
    @FindBy(xpath = "//div[contains(@class,'requirements__item-select')]/span/label[contains(@class, 'radio-button__radio_side_right')]")
    private Radio radioSideRight;

    @ElementTitle("Телефон")
    @FindBy(xpath = "//input[contains(@id, 'phoneNumber')]")
    private TextInput phoneNumber;

    @ElementTitle("Очистить поле Телефон")
    @FindBy(xpath = "//input[contains(@id, 'phoneNumber')]/../span")
    private Button phoneNumberClear;

    @ElementTitle("Тарифы")
    @FindBy(xpath = "//span[contains(text(),'Тарифы')]/..")
    private Link tariffs;

    @ElementTitle("кнопка Комментарий к заказу")
    @FindBy(xpath = "//span[contains(text(),'Комментарий к заказу')]/..")
    private Button commentButton;

    @ElementTitle("Комментарий к заказу")
    @FindBy(xpath = "//textarea[contains(@name,'comment')]")
    private TextArea commentTextInput;

    @ElementTitle("кнопка выбора Тарифа")
    @FindBy(xpath = "//span[contains(@class, 'select_size_service-level')]/button")
    private Button tarriffChooseButton;

    @ElementTitle("Демо-заказ")
    @FindBy(xpath = "//button[contains(@class, 'button_action_demo')]")
    private Button demoButton;

    public WebDriver driver;
    public WebDriverWait Wait;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public YtMainPage() {
        PageFactory.initElements(
                new HtmlElementDecorator(new HtmlElementLocatorFactory(PageFactory.getDriver())), this);
        Wait = new WebDriverWait(PageFactory.getWebDriver(), PageFactory.getTimeOutInSeconds());
    }

    @Override
    @ActionTitle("заполняет")
    public void fillField(String webElemName, String value) throws PageException {
        WebElement webElement = PageFactory.getInstance().getCurrentPage().getElementByTitle(webElemName);
        fillField(webElement, value);
        webElement.sendKeys(Keys.ENTER);
    }

    @Override
    public void fillField(WebElement webElement, String text) {
        webElement.click();
        if (null != text) {
            try {
                webElement.clear();
            } catch (InvalidElementStateException e) {
                logger.debug("Failed to clear element", e);
            }
            try {
                webElement.sendKeys(text);
            } catch (org.openqa.selenium.WebDriverException ex) {
                logger.error("Expected Webdriver error", ex);
                //обход бага chromeDriver
                Actions a = new Actions(PageFactory.getWebDriver());
                a.moveToElement(webElement);
                if (text.length() < 1000) {
                    webElement.click();
                    a.sendKeys(text);
                    a.build().perform();
                } else {
                    a.sendKeys(text);
                }
            }
        }
    }

}
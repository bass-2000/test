package ru.sbtqa.tag.pagefactoryexample.stepdefs;

import cucumber.api.DataTable;
import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sbtqa.tag.datajack.Stash;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.exceptions.PageInitializationException;
import java.util.Iterator;


class CommonStepDefinitions {

    protected Logger logger = LoggerFactory.getLogger(CommonStepDefinitions.class);

    @Когда("^(пользователь|он) \\((.*?)\\).* \"([^\"]*)\" .+ \"([^\"]*)\"[^\"]*$")
    public void пользователь_действие_2(String who, String action, String param1, String param2) throws Throwable {
        PageFactory.getInstance().getCurrentPage().executeMethodByTitle(action, param1, param2);
    }

    @Когда("^(пользователь|он) \\((.*?)\\) данными:$")
    public void пользователь_действие_3(String who, String action, DataTable dataTable) throws Throwable {
        PageFactory.getInstance().getCurrentPage().executeMethodByTitle(action, dataTable);
    }

    @Когда("^пользователь \\((.*?)\\) в поле \"(.*?)\"$")
    public void пользователь_действие_4(String action, Object param) throws Throwable {
        PageFactory.getInstance().getCurrentPage().executeMethodByTitle(action, param);
    }

    @Когда("^пользователь из файла \\((.*?)\\)$")
    @И("^он из файла \\((.*?)\\)$")
    public void пользователь_действие_5(String action) throws Throwable {
        PageFactory.getInstance().getCurrentPage().executeMethodByTitle(action);
    }

    @Дано("^\\(заполняет поле\\) \"(.*?)\" \"(.*?)\"$")
    public void fillField(String arg1, String arg2) throws Throwable {
        PageFactory.getInstance().getCurrentPage().fillField(arg1, arg2);
    }

    @Дано("^выбирает первый по порядку \"(.*?)\"$")
    public void выбирает_первый_по_порядку(String arg1) throws Throwable {
        PageFactory.getInstance().getCurrentPage().clickElementByTitle(arg1);
    }

    @Дано("^\\(выбирает\\) \"(.*?)\" \"(.*?)\"$")
    public void clickElementByTitle(String element, String option) throws Throwable {
        PageFactory.getInstance().getCurrentPage().select(element, option);
    }

    @Когда("^\\((.*?)\\) \"([^\"]*)\"[^\"]*$")
    @Тогда("^\\((.*?)\\)[^\"]*$")
    public void действие_системы_1(String action, Object param) throws Throwable {
        PageFactory.getInstance().getCurrentPage().executeMethodByTitle(action, param);
    }

    @Когда("^(?:появляется|открывается окно|открывается вкладка мастера) \"(.*?)\"$")
    public void пользователь_находится_на_странице(String title) throws PageInitializationException {
        if(!PageFactory.getWebDriver().getWindowHandles().isEmpty()) {
            Iterator var2 = PageFactory.getWebDriver().getWindowHandles().iterator();

            while(var2.hasNext()) {
                String windowHandle = (String)var2.next();
                PageFactory.getWebDriver().switchTo().window(windowHandle);
            }
        }
        long startingTime = System.currentTimeMillis();
        PageFactory.getInstance().getPage(title);
        long endingTime = System.currentTimeMillis();

        logger.debug(String.format("Execution of getPage() for page titled %s takes %s seconds.", title, (endingTime - startingTime) / 1000.0d));
    }

    @Когда("^(?:запоминает страницу) \"(.*?)\"$")
    public void запоминает_страницу(String title)
            throws Throwable {
        Stash.put("CurrentPage", title);
    }

    @Когда("^проверяет разрешение браузера$")
    public void возвращает_разрешение_браузера(){
        String resolution = PageFactory.getWebDriver().manage().window().getSize().toString();
        logger.info("Resolution is " + resolution);
    }

}

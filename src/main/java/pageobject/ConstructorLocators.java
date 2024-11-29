package pageobject;
import java.time.Duration;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class ConstructorLocators {

    //Локаторы конструктора
    //Кнопка Булки
    private By bunButton = By.xpath("//span[contains(text(),'Булки')]");
    //Кнопка Соусы
    private By sauceButton = By.xpath("//span[contains(text(),'Соусы')]");
    //Кнопка Начинки
    private By fillingButton = By.xpath("//span[contains(text(),'Начинки')]");
    //Появляющийся класс булочек
    private By emergingClassBun = By.xpath("//span[contains(text(),'Булки')]/parent::*");
    //Появляющийся класс соусов
    private By emergingClassSauce = By.xpath("//span[contains(text(),'Соусы')]/parent::*");
    //Появляющийся класс начинок
    private By emergingClassFilling = By.xpath("//span[contains(text(),'Начинки')]/parent::*");

    public static final String EMERGINGCLASS  = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";
    private final WebDriver driver;
    private final WebDriverWait wait;

    public ConstructorLocators(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Методы для работы с элементами
    @Step("Клик по кнопке Булки")
    public void clickBunButton() {
        wait.until(ExpectedConditions.elementToBeClickable(bunButton)).click();
    }

    @Step("Клик по кнопке Соусы")
    public void clickSauceButton() {
        wait.until(ExpectedConditions.elementToBeClickable(sauceButton)).click();
    }

    @Step("Клик по кнопке Начинки")
    public void clickFillingButton() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingButton)).click();
    }

    @Step("Получение появляющегося класса булочек")
    public String getClassOfEmergingClassBun(WebDriver driver) {
        // Находим элемент по локатору
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(emergingClassBun));
        // Получаем значение атрибута class и сохраняем в переменной
        String classValueBun = element.getAttribute("class");
        // Возвращаем значение переменной
        return classValueBun;
    }

    @Step("Получение появляющегося класса соусов")
    public String getClassOfEmergingClassSauce(WebDriver driver) {
        // Находим элемент по локатору
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(emergingClassSauce));
        // Получаем значение атрибута class и сохраняем в переменной
        String classValueSauce = element.getAttribute("class");
        // Возвращаем значение переменной
        return classValueSauce;
    }

    @Step("Получение появляющегося класса начинки")
    public String getClassOfEmergingClassFilling(WebDriver driver) {
        // Находим элемент по локатору
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(emergingClassFilling));
        // Получаем значение атрибута class и сохраняем в переменной
        String classValueFilling = element.getAttribute("class");
        // Возвращаем значение переменной
        return classValueFilling;
    }
}
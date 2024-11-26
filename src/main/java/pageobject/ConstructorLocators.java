package pageobject;
import java.time.Duration;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
    //Заголовок поля выбора булочек
    private By bunsFieldHeader = By.xpath("//h2[contains(text(),'Булки')]");
    //Заголовок поля выбора соусов
    private By sausesFieldHeader = By.xpath("//h2[contains(text(),'Соусы')]");
    //Заголовок поля выбора начинок
    private By fillingsFieldHeader = By.xpath("//h2[contains(text(),'Начинки')]");


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

    @Step("Метод возвращает текст из поля заголовка выбора булочек")
    public String textBunsFieldHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(bunsFieldHeader)).getText();
    }

    @Step("Метод возвращает текст из поля заголовка выбора соусов")
    public String textSausesFieldHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(sausesFieldHeader)).getText();
    }

    @Step("Метод возвращает текст из поля заголовка выбора начинок")
    public String textFillingsFieldHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsFieldHeader)).getText();
    }
}
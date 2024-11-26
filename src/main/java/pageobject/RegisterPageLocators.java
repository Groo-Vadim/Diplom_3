package pageobject;
import java.time.Duration;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class RegisterPageLocators {

    //Локаторы для регистрации
    //Поле ввода имени
    private By nameInput = By.xpath(".//label[text()='Имя']/..//input");
    //Поле ввода email
    private By emailInput = By.xpath(".//label[text()='Email']/..//input");
    //Поле ввода пароля
    private By passwordInput = By.xpath(".//label[text()='Пароль']/..//input");
    //Кнопка Зарегистрироваться
    private By registerButton = By.xpath("//button[contains(text(),'Зарегистрироваться')]");
    //Шапка после регистрации содержит текст Вход
    private By loginHeader = By.xpath(".//h2[text() = 'Вход']");
    //Локатор неверный пароль
    private By errorPassword = By.xpath(".//p[text()='Некорректный пароль']");


    private final WebDriver driver;
    private final WebDriverWait wait;

    public RegisterPageLocators(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Ввод имени в поле Name")
    public void enterName(String name) {
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
        // Очистка поля перед вводом
        nameField.clear();
        nameField.sendKeys(name);
    }

    @Step("Ввод емаил в поле Email")
    public void enterEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        // Очистка поля перед вводом
        emailField.clear();
        emailField.sendKeys(email);
    }

    @Step("Ввод пароля в поле Password")
    public void enterPassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        // Очистка поля перед вводом
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    @Step("Ожидание страницы входа")
    public void waitLoginHeader() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader));
    }

    @Step("Сообщение при невалидном пароле")
    public String textForErrorPassword() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorPassword)).getText();
    }
}
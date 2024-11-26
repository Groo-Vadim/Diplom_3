package pageobject;
import java.time.Duration;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import endpoints.UrlEndpoints;
import static org.junit.Assert.*;

public class LoginPageLocators {

    //Локаторы для регистрации
    //Кнопка «Войти в аккаунт» на главной
    private By loginToAccount = By.xpath("//button[contains(text(),'Войти в аккаунт')]");
    //Кнопка Личный Кабинет
    private By personalAccount = By.xpath("//p[contains(text(),'Личный Кабинет')]");
    //Кнопка вход в форме Входа
    private By loginButton = By.xpath("//button[contains(text(),'Войти')]");
    //Вы — новый пользователь? Зарегистрироваться
    private By registerNewUser = By.xpath("//a[contains(text(),'Зарегистрироваться')]");
    //Кнопка вход в форме регистрации и восстановления пароля
    private By buttonLoginFromRegistration = By.xpath("//a[contains(text(),'Войти')]");
    //Поле ввода email
    private By emailInput = By.xpath(".//label[text()='Email']/..//input");
    //Поле ввода пароля
    private By passwordInput = By.xpath(".//label[text()='Пароль']/..//input");
    //Кнопка восстановить пароль
    private By forgotPasswordButton = By.xpath("//a[contains(text(),'Восстановить пароль')]");
    //Кнопка Выход в личном кабинете
    private By exitButton = By.xpath("//button[contains(text(),'Выход')]");
    //Кнопка Конструктор
    private By constructorButton = By.xpath("//p[contains(text(),'Конструктор')]");
    //Лого Бургерс
    private By logoBurgers = By.xpath("//div[@class = 'AppHeader_header__logo__2D0X2']");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public LoginPageLocators(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Методы для работы с элементами
    @Step("Клик по кнопке «Войти в аккаунт»")
    public void clickLoginToAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(loginToAccount)).click();
    }

    @Step("Клик по кнопке Личный Кабинет")
    public void clickPersonalAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccount)).click();
    }

    @Step("Клик по кнопке Вход в форме Входа")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Клик по кнопке регистрация")
    public void clickRegisterNewUser() {
        wait.until(ExpectedConditions.elementToBeClickable(registerNewUser)).click();
    }

    @Step("Клик по кнопке восстановить пароль")
    public void clickForgotPasswordButton() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordButton)).click();
    }

    @Step("Клик по кнопке Входа на форме регистрации")
    public void clickButtonLoginFromRegistration() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonLoginFromRegistration)).click();
    }

    @Step("Клик по кнопке выход в личном кабинете")
    public void clickExitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(exitButton)).click();
    }

    @Step("Клик по кнопке лого Бургерс")
    public void clickLogoBurgers() {
        wait.until(ExpectedConditions.elementToBeClickable(logoBurgers)).click();
    }

    @Step("Ожидание перехода на главную страницу")
    public void waitForHomePage() {
        wait.until(driver -> driver.getCurrentUrl().equals(UrlEndpoints.BASE_URL));
    }

    @Step("Ожидание перехода на страницу входа")
    public void waitForLoginPage() {
        wait.until(driver -> driver.getCurrentUrl().equals(UrlEndpoints.LOGIN_URL));
    }

    @Step("Ожидание появления кнопки Выход после перехода в личный кабинет")
    public void waitExitButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(exitButton));
    }

    @Step("Метод возвращает True, если кнопка 'Выход' видна на странице")
    public boolean isExitButtonVisible() {
        return driver.findElement(exitButton).isDisplayed();
    }

    @Step("Клик по кнопке Конструктор")
    public void clickConstructorButton() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
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
}
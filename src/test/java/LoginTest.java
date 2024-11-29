import endpoints.UrlEndpoints;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.LoginPageLocators;
import user.*;

import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;


public class LoginTest {

    private WebDriver driver;
    private LoginPageLocators loginPage;
    private String accessToken;
    private UserActionsSetup userActionsSetup;

    @Before
    public void setUp() {
        // Инициализация UserActionsSetup
        userActionsSetup = new UserActionsSetup();

        // Использование WebDriverCreator для инициализации WebDriver
        driver = WebDriverCreator.createWebDriver();

        // Переход на страницу регистрации
        driver.get(UrlEndpoints.BASE_URL);

        // Инициализация локаторов
        loginPage = new LoginPageLocators(driver);

        // Создание пользователя
        createUser();
    }

    private void createUser() {
        Response createResponse = userActionsSetup.createUser(new User("test@name8.com", "123456", "TestName8"));
        // Проверяем статус-код
        createResponse.then().assertThat().statusCode(SC_OK);
        createResponse.then().assertThat().body("success", equalTo(true));
        // Сохраняем accessToken из тела ответа
        String idString = createResponse.jsonPath().getString("accessToken");
        accessToken = idString;
        System.out.println("accessToken: " + accessToken);
    }



    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginButtonLoginToAccount() {

        //Клик по кнопке «Войти в аккаунт» на главной
        loginPage.clickLoginToAccount();

        // Ввод данных
        loginPage.enterEmail("test@name8.com");
        loginPage.enterPassword("123456");

        // Нажатие на кнопку вход
        loginPage.clickLoginButton();

        loginPage.waitForHomePage();

        // Проверка успешного входа
        assertEquals("После успешного входа должен быть переход на главную страницу",
                UrlEndpoints.BASE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет")
    public void loginButtonPersonalAccount() {

        //Клик по кнопке «Личный кабинет»
        loginPage.clickPersonalAccount();

        // Ввод данных
        loginPage.enterEmail("test@name8.com");
        loginPage.enterPassword("123456");

        // Нажатие на кнопку вход
        loginPage.clickLoginButton();

        loginPage.waitForHomePage();

        // Проверка успешного входа
        assertEquals("После успешного входа должен быть переход на главную страницу",
                UrlEndpoints.BASE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginButtonFromRegistrationForm() {

        //Клик по кнопке «Личный кабинет»
        loginPage.clickPersonalAccount();

        // Нажимаем на кнопку Регистрация
        loginPage.clickRegisterNewUser();

        //Клик по кнопке войти
        loginPage.clickButtonLoginFromRegistration();

        // Ввод данных
        loginPage.enterEmail("test@name8.com");
        loginPage.enterPassword("123456");

        // Нажатие на кнопку вход
        loginPage.clickLoginButton();

        loginPage.waitForHomePage();

        // Проверка успешного входа
        assertEquals("После успешного входа должен быть переход на главную страницу",
                UrlEndpoints.BASE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля.")
    public void loginButtonFromForgotPassForm() {

        //Клик по кнопке «Личный кабинет»
        loginPage.clickPersonalAccount();

        // Нажимаем на кнопку Восстановить пароль
        loginPage.clickForgotPasswordButton();

        //Клик по кнопке войти
        loginPage.clickButtonLoginFromRegistration();

        // Ввод данных
        loginPage.enterEmail("test@name8.com");
        loginPage.enterPassword("123456");

        // Нажатие на кнопку вход
        loginPage.clickLoginButton();

        loginPage.waitForHomePage();

        // Проверка успешного входа
        assertEquals("После успешного входа должен быть переход на главную страницу",
                UrlEndpoints.BASE_URL, driver.getCurrentUrl());
    }

@After
public void tearDown() {
    // Удаление пользователя, если accessToken доступен
    if (accessToken != null) {
        deleteUser();
    }

    // Закрытие драйвера
    if (driver != null) {
        driver.quit();
    }
}

    private void deleteUser() {
        // Удаление пользователя
        Response deleteResponse = userActionsSetup.deleteUser(accessToken);
        // Проверяем статус-код удаления
        deleteResponse.then().assertThat().statusCode(SC_ACCEPTED);
        // Проверяем, что ответ содержит поле success : true
        deleteResponse.then().assertThat().body("success", equalTo(true));
    }
}
import endpoints.UrlEndpoints;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.LoginPageLocators;
import user.User;
import user.UserActionsSetup;

import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class GoToWebsitePageTest {

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
        driver.get(UrlEndpoints.LOGIN_URL);

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
    @DisplayName("Переход в личный кабинет по клику на «Личный кабинет")
    public void goToPersonalAccount() {

        // Ввод данных
        loginPage.enterEmail("test@name8.com");
        loginPage.enterPassword("123456");

        // Нажатие на кнопку вход
        loginPage.clickLoginButton();

        // Ожидание перехода на главную страницу
        loginPage.waitForHomePage();

        // Проверка успешного входа
        assertEquals("После успешного входа должен быть переход на главную страницу",
                UrlEndpoints.BASE_URL, driver.getCurrentUrl());

        // Клик по кнопке «Личный кабинет»
        loginPage.clickPersonalAccount();

        // Ожидание появления кнопки Выход после перехода в личный кабинет
        loginPage.waitExitButton();

        // Проверка видимости кнопки "Выход"
        assertTrue("Кнопка 'Выход' должна быть видимой после успешного входа", loginPage.isExitButtonVisible());
    }

    @Test
    @DisplayName("Переход в Конструктор по клику на кнопку конструктор")
    public void goToСonstructorPressToConstructor() {

        // Ввод данных
        loginPage.enterEmail("test@name8.com");
        loginPage.enterPassword("123456");

        // Нажатие на кнопку вход
        loginPage.clickLoginButton();

        // Ожидание перехода на главную страницу
        loginPage.waitForHomePage();

        // Проверка успешного входа
        assertEquals("После успешного входа должен быть переход на главную страницу",
                UrlEndpoints.BASE_URL, driver.getCurrentUrl());

        // Клик по кнопке «Личный кабинет»
        loginPage.clickPersonalAccount();

        // Ожидание появления кнопки Выход после перехода в личный кабинет
        loginPage.waitExitButton();

        // Проверка видимости кнопки "Выход"
        assertTrue("Кнопка 'Выход' должна быть видимой после успешного входа", loginPage.isExitButtonVisible());

        // Клик по кнопке конструктор
        loginPage.clickConstructorButton();

        // Ожидание перехода на главную страницу
        loginPage.waitForHomePage();

        // Проверка успешного перехода
        assertEquals("После нажатия на кнопку Конструктор должен быть переход на главную страницу",
                UrlEndpoints.BASE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход в Конструктор по клику на логотип Stellar Burgers")
    public void goToСonstructorPressToLogoBurgers() {

        // Ввод данных
        loginPage.enterEmail("test@name8.com");
        loginPage.enterPassword("123456");

        // Нажатие на кнопку вход
        loginPage.clickLoginButton();

        // Ожидание перехода на главную страницу
        loginPage.waitForHomePage();

        // Проверка успешного входа
        assertEquals("После успешного входа должен быть переход на главную страницу",
                UrlEndpoints.BASE_URL, driver.getCurrentUrl());

        // Клик по кнопке «Личный кабинет»
        loginPage.clickPersonalAccount();

        // Ожидание появления кнопки Выход после перехода в личный кабинет
        loginPage.waitExitButton();

        // Проверка видимости кнопки "Выход"
        assertTrue("Кнопка 'Выход' должна быть видимой после успешного входа", loginPage.isExitButtonVisible());

        // Клик по лого Бургерс
        loginPage.clickLogoBurgers();

        // Ожидание перехода на главную страницу
        loginPage.waitForHomePage();

        // Проверка успешного перехода
        assertEquals("После нажатия на кнопку Конструктор должен быть переход на главную страницу",
                UrlEndpoints.BASE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Выход из аккаунта по кнопке «Выйти» в личном кабинете")
    public void exitAccount() {

        // Ввод данных
        loginPage.enterEmail("test@name8.com");
        loginPage.enterPassword("123456");

        // Нажатие на кнопку вход
        loginPage.clickLoginButton();

        // Ожидание перехода на главную страницу
        loginPage.waitForHomePage();

        // Проверка успешного входа
        assertEquals("После успешного входа должен быть переход на главную страницу",
                UrlEndpoints.BASE_URL, driver.getCurrentUrl());

        // Клик по кнопке «Личный кабинет»
        loginPage.clickPersonalAccount();

        // Ожидание появления кнопки Выход после перехода в личный кабинет
        loginPage.waitExitButton();

        // Проверка видимости кнопки "Выход"
        assertTrue("Кнопка 'Выход' должна быть видимой после успешного входа", loginPage.isExitButtonVisible());

        // Нажатие на кнопку Выход в личном кабинете
        loginPage.clickExitButton();

        // Ожидание перехода на страницу входа
        loginPage.waitForLoginPage();

        // Проверка успешного перехода
        assertEquals("После нажатия на кнопку Выход должен быть переход на страницу входа",
                UrlEndpoints.LOGIN_URL, driver.getCurrentUrl());
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
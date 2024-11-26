import endpoints.UrlEndpoints;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.RegisterPageLocators;
import user.*;

import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;


public class RegistrationTest {
    private WebDriver driver;
    private RegisterPageLocators registerPage;
    private String accessToken;
    private UserActionsSetup userActionsSetup = new UserActionsSetup();
    private boolean isUserDeleted = false;

    @Before
    public void setUp() {

        // Инициализация UserActionsSetup
        userActionsSetup = new UserActionsSetup();

        // Использование WebDriverCreator для инициализации WebDriver
        driver = WebDriverCreator.createWebDriver();

        // Переход на страницу регистрации
        driver.get(UrlEndpoints.REGISTER_URL);

        // Инициализация локаторов
        registerPage = new RegisterPageLocators(driver);
    }

    @Test
    @DisplayName("Проверка валидной регистрации")
    public void testValidateRegistration() {

        // Ввод данных
        registerPage.enterName("TestName8");
        registerPage.enterEmail("test@name8.com");
        registerPage.enterPassword("123456");

        // Подтверждение регистрации
        registerPage.clickRegisterButton();

        registerPage.waitLoginHeader();

        // Проверка успешной регистрации
        assertEquals("После успешной регистрации должен быть переход на страницу входа",
                UrlEndpoints.LOGIN_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Проверка регистрации с невалидным паролем")
    public void testNotValidPasswordRegistration() {
        isUserDeleted = true;
        // Ввод данных
        registerPage.enterName("TestName8");
        registerPage.enterEmail("test@name8.com");
        registerPage.enterPassword("12345");

        // Подтверждение регистрации
        registerPage.clickRegisterButton();

        // Проверка появления сообщения об ошибке
        assertEquals("Текст ошибки должен совпадать", "Некорректный пароль", registerPage.textForErrorPassword());
    }

    @After
    public void tearDown() {
        if (accessToken == null && !isUserDeleted) {
            deleteUser(); // Удаление пользователя
        }

        if (driver != null) {
            driver.quit();
        }
    }
    private void deleteUser() {
        // Логин пользователя
        Response loginResponse = userActionsSetup.loginUser(new User("test@name8.com", "123456"));

        // Проверяем статус-код логина
        loginResponse.then().assertThat().statusCode(SC_OK);

        // Сохраняем accessToken из тела ответа
        String idString = loginResponse.jsonPath().getString("accessToken");
        accessToken = idString;
        System.out.println("accessToken: " + accessToken);

        // Удаление пользователя
        Response deleteResponse = userActionsSetup.deleteUser(accessToken);

        // Проверяем статус-код удаления
        deleteResponse.then().assertThat().statusCode(SC_ACCEPTED);
        // Проверяем, что ответ содержит поле success : true
        deleteResponse.then().assertThat().body("success", equalTo(true));
    }
}
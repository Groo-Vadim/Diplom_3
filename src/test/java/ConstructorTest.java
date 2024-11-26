import endpoints.UrlEndpoints;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.*;
import static org.junit.Assert.assertEquals;


public class ConstructorTest {

    private WebDriver driver;
    private ConstructorLocators constructorPage;

    @Before
    public void setUp() {
        // Использование WebDriverCreator для инициализации WebDriver
        driver = WebDriverCreator.createWebDriver();

        // Переход на страницу регистрации
        driver.get(UrlEndpoints.BASE_URL);

        // Инициализация локаторов
        constructorPage = new ConstructorLocators(driver);
    }

    @Test
    @DisplayName("Переход в раздел начинки")
    public void fillingConstructor() {

        //Клик по кнопке Начинки
        constructorPage.clickFillingButton();

        // Проверка появления заголовка поля Начинки
        assertEquals("Должен произойти переход на поле с заголовком Начинки",
                "Начинки", constructorPage.textFillingsFieldHeader());
    }

    @Test
    @DisplayName("Переход в раздел булки")
    public void bunConstructor() {

        //Клик по кнопке Начинки
        constructorPage.clickFillingButton();

        //Клик по кнопке Начинки
        constructorPage.clickBunButton();

        // Проверка появления заголовка поля Начинки
        assertEquals("Должен произойти переход на поле с заголовком Булки",
                "Булки", constructorPage.textBunsFieldHeader());
    }

    @Test
    @DisplayName("Переход в раздел соусы")
    public void sauceConstructor() {

        //Клик по кнопке Начинки
        constructorPage.clickSauceButton();

        // Проверка появления заголовка поля Начинки
        assertEquals("Должен произойти переход на поле с заголовком Соусы",
                "Соусы", constructorPage.textSausesFieldHeader());
    }

    @After
    public void tearDown() {
        // Закрытие драйвера
        if (driver != null) {
            driver.quit();
        }
    }
}
import endpoints.UrlEndpoints;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.*;
import static org.junit.Assert.assertEquals;
import static pageobject.ConstructorLocators.EMERGINGCLASS;


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

        // Проверка появления всплывающего класса
        assertEquals("При клике на кнопку Начинки class должен быть "+ EMERGINGCLASS,EMERGINGCLASS, constructorPage.getClassOfEmergingClassFilling(driver));
    }

    @Test
    @DisplayName("Переход в раздел булки")
    public void bunConstructor() {

        // Проверка появления всплывающего класса
        assertEquals("При клике на кнопку Булки class должен быть "+ EMERGINGCLASS,EMERGINGCLASS, constructorPage.getClassOfEmergingClassBun(driver));
    }

    @Test
    @DisplayName("Переход в раздел соусы")
    public void sauceConstructor() {

        //Клик по кнопке Соусы
        constructorPage.clickSauceButton();

        // Проверка появления всплывающего класса
        assertEquals("При клике на кнопку Соусы class должен быть "+ EMERGINGCLASS,EMERGINGCLASS, constructorPage.getClassOfEmergingClassSauce(driver));
    }

    @After
    public void tearDown() {
        // Закрытие драйвера
        if (driver != null) {
            driver.quit();
        }
    }
}
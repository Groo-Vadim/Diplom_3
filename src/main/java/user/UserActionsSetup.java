package user;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;



// Класс для работы с API пользователя
public class UserActionsSetup {
    // Базовый URL
    private final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    // Ручки API
    // Регистрация пользователя
    private static final String CREATE_USER_ENDPOINT = "/api/auth/register";
    // Авторизация пользователя
    private static final String LOGIN_USER_ENDPOINT = "/api/auth/login";
    // Удаление пользователя
    private static final String DELETE_USER_ENDPOINT = "/api/auth/user";

    @Step("Метод для создания пользователя")
    public Response createUser(User user) {
        return given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(BASE_URL + CREATE_USER_ENDPOINT);
    }

    @Step("Метод для логина пользователя")
    public Response loginUser(User user) {
        return given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(BASE_URL + LOGIN_USER_ENDPOINT);
    }

    @Step("Метод для удаления пользователя")
    public Response deleteUser(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .when()
                .delete(BASE_URL + DELETE_USER_ENDPOINT);
    }
}
package dmit2015.restclient;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * <a href="https://github.com/rest-assured/rest-assured">REST Assured GitHub repo</a>
 * <a href="https://github.com/rest-assured/rest-assured/wiki/Usage">REST Assured Usage</a>
 * <a href="http://www.mastertheboss.com/jboss-frameworks/resteasy/restassured-tutorial">REST Assured Tutorial</a>
 * <a href="https://github.com/FasterXML/jackson-databind">Jackson Data-Binding</a>
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TodoItemDtoResourceRestAssuredIT {

    String todoitemResourceUrl = "http://localhost:8080/restapi/TodoItems";
    static String testDataResourceLocation;

    @Order(1)
    @ParameterizedTest
    @CsvSource(value = {
            "Create RESTAssured IT for TodoItem REST API endpoint, false"
    })
    void shouldCreate(String name, boolean completed) {
        TodoItemDto newTodoItemDto = new TodoItemDto();
        newTodoItemDto.setName(name);
        newTodoItemDto.setCompleted(completed);

        Jsonb jsonb = JsonbBuilder.create();
        String jsonBody = jsonb.toJson(newTodoItemDto);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post(todoitemResourceUrl)
                .then()
                .statusCode(201)
                .extract()
                .response();
        testDataResourceLocation = response.getHeader("location");
    }

    @Order(2)
    @ParameterizedTest
    @CsvSource(value = {
            "Create RESTAssured IT for TodoItem REST API endpoint, false"
    })
    void shouldFineOne(String name, boolean completed) {
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get(testDataResourceLocation)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .response();
        String jsonBody = response.getBody().asString();

        Jsonb jsonb = JsonbBuilder.create();
        TodoItemDto existingTodoItemDto = jsonb.fromJson(jsonBody, TodoItemDto.class);

        assertThat(existingTodoItemDto.getName())
                .isEqualTo(name);
        assertThat(existingTodoItemDto.isCompleted())
                .isEqualTo(completed);

    }

    @Order(3)
    @ParameterizedTest
    @CsvSource(value = {
            "Create RESTAssured IT for TodoItem REST API endpoint, false"

    })
    void shouldFindAll(String name, boolean completed) {
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get(todoitemResourceUrl)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .response();
        String jsonBody = response.getBody().asString();

        Jsonb jsonb = JsonbBuilder.create();
        List<TodoItemDto> queryResultList = jsonb.fromJson(jsonBody, new ArrayList<TodoItemDto>() {
        }.getClass().getGenericSuperclass());

        TodoItemDto lastTodoItemDto = queryResultList.get(queryResultList.size() - 1);
        assertThat(lastTodoItemDto.getName())
                .isEqualTo(name);
        assertThat(lastTodoItemDto.isCompleted())
                .isEqualTo(completed);

    }

    @Order(4)
    @ParameterizedTest
    // For boolean, it wont update unless you use 0 or 1 (dont use true/false)
    @CsvSource(value = {
            "run RESTAssured IT for TodoItem REST API endpoint, 1"
    })
    void shouldUpdate(String name, boolean completed) {
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get(testDataResourceLocation)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .response();
        String jsonBody = response.getBody().asString();

        Jsonb jsonb = JsonbBuilder.create();
        TodoItemDto existingTodoItemDto = jsonb.fromJson(jsonBody, TodoItemDto.class);
        existingTodoItemDto.setName(name);
        existingTodoItemDto.setCompleted(completed);

        String jsonTodoItem = jsonb.toJson(existingTodoItemDto);

        Response putResponse = given()
                .contentType(ContentType.JSON)
                .body(jsonTodoItem)
                .when()
                .put(testDataResourceLocation)
                .then()
                .statusCode(200)
                .extract()
                .response();

        String putResponseJsonBody = putResponse.getBody().asString();
        TodoItemDto updatedTodoItemDto = jsonb.fromJson(putResponseJsonBody, TodoItemDto.class);

        assertThat(existingTodoItemDto)
                .usingRecursiveComparison()
                .ignoringFields("updateTime")
                .isEqualTo(updatedTodoItemDto);
    }

    @Order(5)
    @Test
    void shouldDelete() {
        given()
                .when()
                .delete(testDataResourceLocation)
                .then()
                .statusCode(204);
    }

}
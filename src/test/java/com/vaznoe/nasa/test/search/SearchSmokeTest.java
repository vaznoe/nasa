package com.vaznoe.nasa.test.search;

import com.vaznoe.nasa.helper.NasaTestHelper;
import com.vaznoe.nasa.test.BaseApiTest;
import com.vaznoe.nasa.utils.Request;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static com.vaznoe.nasa.utils.RandomUtils.generateSearch;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author vaznoe
 * Date: 2/15/19
 */
@Feature("Search")
@Story("As a user I can search for ....")
@RunWith(SpringRunner.class)
public class SearchSmokeTest extends BaseApiTest {

    @Autowired
    private NasaTestHelper nasaTestHelper;

    @Test
    @Description("Search with invalid param: key: \"q\" and random value")
    public void searchQTest() {
        String searchValue = generateSearch();
        Request request = new Request()
                .add("q", searchValue);
        Response response = nasaTestHelper.search(request.get());
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("collection.metadata.total_hits")).isGreaterThan("0");
        assertThat(response.jsonPath().getList("collection.items")).size().isGreaterThan(0);
        String[] result = response.jsonPath().getString("collection.href").split("=");
        assertThat(result[1]).isEqualTo(searchValue);
    }

    @Test
    @Description("Search with invalid param: key: \"q\" and empty value")
    public void searchQTest_withEmptyValue() {
        Request request = new Request()
                .add("q", "");
        Response response = nasaTestHelper.search(request.get());
        assertThat(response.statusCode()).isEqualTo(400);
        assertThat(response.jsonPath().getString("reason")).isEqualTo("Expected 'q' text search parameter or other keywords.");
    }

    @Test
    @Description("Search with invalid param: invalid key: \" q\" and valid value")
    public void searchTest_withInvalidSearchKey() {
        String searchValue = generateSearch();
        Request request = new Request()
                .add(" q", searchValue);
        Response response = nasaTestHelper.search(request.get());
        assertThat(response.statusCode()).isEqualTo(400);
        assertThat(response.jsonPath().getString("reason")).isEqualTo("Invalid search parameter:  q");
    }

    @Test
    @Description("Search with invalid param: empty key and empty value")
    public void searchQTest_withEmptyKeyAndValue() {
        Request request = new Request()
                .add(" ", " ");
        Response response = nasaTestHelper.search(request.get());
        assertThat(response.statusCode()).isEqualTo(400);
        assertThat(response.jsonPath().getString("reason")).isEqualTo("Invalid search parameter:  ");
    }

    @Test
    @Description("Search without any params")
    public void searchWithoutParamsTest() {
        Response response = nasaTestHelper.search();
        assertThat(response.statusCode()).isEqualTo(400);
        assertThat(response.jsonPath().getString("reason")).isEqualTo("Expected 'q' text search parameter or other keywords.");
    }
}

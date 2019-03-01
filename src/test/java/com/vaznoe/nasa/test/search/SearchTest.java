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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author vaznoe
 * Date: 2/16/19
 */
@Feature("Search")
@Story("As a user I can search for ....")
@RunWith(SpringRunner.class)
public class SearchTest extends BaseApiTest {

    @Autowired
    private NasaTestHelper nasaTestHelper;

    @Test
    @Description("Search with 2 valid params")
    public void searchWith2ParamsTest() {
        Request request = new Request()
                .add("center", "HQ")
                .add("keywords", "mars");
        Response response = nasaTestHelper.search(request.get());
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("collection.metadata.total_hits")).isEqualTo("724");
        assertThat(response.jsonPath().getList("collection.items")).size().isGreaterThan(99);
    }

    @Test
    @Description("Search with 8 valid params")
    public void searchWith8ParamsTest() {
        Request request = new Request()
                .add("q", "nasa")
                .add("center", "HQ")
                .add("description", "NASA")
                .add("keywords", "mars")
                .add("location", "NASA HQ")
                .add("media_type", "image")
                .add("year_start", "1961")
                .add("year_end", "2018");
        Response response = nasaTestHelper.search(request.get());
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("collection.metadata.total_hits")).isEqualTo("10");
        assertThat(response.jsonPath().getList("collection.items")).size().isEqualTo(10);
    }

    @Test
    @Description("Search with 6 valid params and 1 invalid params")
    public void searchWithOneInvalidParamsTest() {
        Request request = new Request()
                .add("q", "nasa")
                .add("center", "HQ")
                .add("description", "NASA")
                .add("location", "NASA HQ")
                .add("media_type", "image")
                .add("year_start", "2010")
                .add("year_end", "&2011");
        Response response = nasaTestHelper.search(request.get());
        assertThat(response.statusCode()).isEqualTo(400);
        assertThat(response.jsonPath().getString("reason")).isEqualTo("Invalid value year_end=&2011.");
    }
}

package com.vaznoe.nasa.test.search;

import com.vaznoe.nasa.helper.NasaTestHelper;
import com.vaznoe.nasa.test.BaseApiTest;
import com.vaznoe.nasa.utils.Request;
import com.vaznoe.nasa.utils.ValidationRules;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author vaznoe
 * Date: 2/16/19
 */
@Feature("Search")
@Story("As a user I can search for ....")
@RunWith(JUnitParamsRunner.class)
public class SearchQueryRequestValidationTest extends BaseApiTest {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private NasaTestHelper nasaTestHelper;

    @Test
    @Description("Validation test for search by  key")
    @Parameters(source = ValidationRules.class, method = "mandatorySearchKey")
    public void validationSearchForKeyQueryTest(String testValue, int expectedStatusCode) {
        Request request = new Request()
                .add(testValue, "2011");
        Response response = nasaTestHelper.search(request.get());
        assertThat(response).extracting(ResponseOptions::statusCode).isEqualTo(expectedStatusCode);
    }

    @Test
    @Description("Validation test for search by value: \"year_start\"")
    @Parameters(source = ValidationRules.class, method = "optionalStringYearStart")
    public void validationSearchForYearStartTest(String testValue, int expectedStatusCode) {
        Request request = new Request()
                .add("year_start", testValue);
        Response response = nasaTestHelper.search(request.get());
        assertThat(response).extracting(ResponseOptions::statusCode).isEqualTo(expectedStatusCode);
    }
}
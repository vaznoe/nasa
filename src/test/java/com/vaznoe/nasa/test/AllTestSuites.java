package com.vaznoe.nasa.test;

import com.vaznoe.nasa.test.search.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author vaznoe
 * Date: 2/15/19
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        SearchTest.class,
        SearchSmokeTest.class,
        SearchQueryRequestValidationTest.class
})
public class AllTestSuites {
}

package com.vaznoe.nasa.utils;

import org.apache.commons.httpclient.HttpStatus;

import java.util.Arrays;

/**
 * @author vaznoe
 * Date: 2/16/19
 * <p>
 * Test data sests for specific testing validation rules
 * Available data sets:<br>
 * - mandatorySearchKey<br>
 * - optionalStringYearStart<br>
 */
public class ValidationRules {

    public static Iterable<Object[]> mandatorySearchKey() {
        return Arrays.asList(new Object[][]{
                {"q", HttpStatus.SC_OK},
                {"center", HttpStatus.SC_OK},
                {"description", HttpStatus.SC_OK},
                {"description_508", HttpStatus.SC_OK},
                {"keywords", HttpStatus.SC_OK},
                {"location", HttpStatus.SC_OK},
                {"media_type", HttpStatus.SC_OK},
                {"nasa_id", HttpStatus.SC_OK},
                {"photographer", HttpStatus.SC_OK},
                {"secondary_creator", HttpStatus.SC_OK},
                {"title", HttpStatus.SC_OK},
                {"year_start", HttpStatus.SC_OK},
                {"year_end", HttpStatus.SC_OK},
                {" ", HttpStatus.SC_BAD_REQUEST},
                {" q", HttpStatus.SC_BAD_REQUEST},
                {"?center", HttpStatus.SC_BAD_REQUEST},
                {" description=", HttpStatus.SC_BAD_REQUEST},
                {"2011", HttpStatus.SC_BAD_REQUEST},
                {"//", HttpStatus.SC_BAD_REQUEST},
                {"invalid", HttpStatus.SC_BAD_REQUEST},
                {"\" &appolon 11;\"", HttpStatus.SC_BAD_REQUEST},
                {"\"", HttpStatus.SC_BAD_REQUEST},
                {true + "\"", HttpStatus.SC_BAD_REQUEST},
                {false, HttpStatus.SC_BAD_REQUEST},
                {"true", HttpStatus.SC_BAD_REQUEST},
                {"\"true\"", HttpStatus.SC_BAD_REQUEST},
                {"false", HttpStatus.SC_BAD_REQUEST},
                {"\"false\"", HttpStatus.SC_BAD_REQUEST},
                {'A', HttpStatus.SC_BAD_REQUEST},
                {"invalid", HttpStatus.SC_BAD_REQUEST},
                {"", HttpStatus.SC_BAD_REQUEST},
                {"\"title\"", HttpStatus.SC_BAD_REQUEST},
                {"x7fff_ffff_ffff_ffffL", HttpStatus.SC_BAD_REQUEST},
                {"#", HttpStatus.SC_BAD_REQUEST},
                {0223D + 00331, HttpStatus.SC_BAD_REQUEST},
                {"\\u20AC", HttpStatus.SC_BAD_REQUEST},
                {"\"1234567890()_+=\\/`~|?><.,;:!@#$%^&*\"", HttpStatus.SC_BAD_REQUEST},
        });
    }

    public static Iterable<Object[]> optionalStringYearStart() {
        return Arrays.asList(new Object[][]{
                {"1985", HttpStatus.SC_OK},
                {"1961", HttpStatus.SC_OK},
                {"2000", HttpStatus.SC_OK},
                {"2010", HttpStatus.SC_OK},
                {"2018", HttpStatus.SC_OK},
                {"1800", HttpStatus.SC_OK},
                {"2001-01-22", HttpStatus.SC_BAD_REQUEST},
                {"2018-05-14T00:00:00Z", HttpStatus.SC_BAD_REQUEST},
                {"20090720T00:00:00Z", HttpStatus.SC_BAD_REQUEST},
                {"0009/07/20T00:00:00Z", HttpStatus.SC_BAD_REQUEST},
                {"id", HttpStatus.SC_BAD_REQUEST},
                {"udid", HttpStatus.SC_BAD_REQUEST},
                {"\" &appolon 11\"", HttpStatus.SC_BAD_REQUEST},
                {true, HttpStatus.SC_BAD_REQUEST},
                {false, HttpStatus.SC_BAD_REQUEST},
                {'A', HttpStatus.SC_BAD_REQUEST},
                {"", HttpStatus.SC_BAD_REQUEST},
                {" ", HttpStatus.SC_BAD_REQUEST},
                {"\"title\"", HttpStatus.SC_BAD_REQUEST},
                {"x7fff_ffff_ffff_ffffL", HttpStatus.SC_BAD_REQUEST},
                {"#", HttpStatus.SC_BAD_REQUEST},
                {"&quot;", HttpStatus.SC_BAD_REQUEST},
                {"\\u20AC", HttpStatus.SC_BAD_REQUEST},
                {"\"1234567890()_+=\\/`~|?><.,;:!@#$%^&*\"", HttpStatus.SC_BAD_REQUEST},
                {"SELECT id, username, first_name, last_name, email FROM employees WHERE username='\".$_GET['username'].\"';", HttpStatus.SC_BAD_REQUEST},
                {"SELECT id, username, first_name, last_name, email FROM employees WHERE username='admin' OR 'a'='a", HttpStatus.SC_BAD_REQUEST},
                {"SELECT employer FROM employees WHERE name=A' AND 1=2 UNION SELECT password FROM members WHERE username='admin", HttpStatus.SC_BAD_REQUEST},
        });
    }
}
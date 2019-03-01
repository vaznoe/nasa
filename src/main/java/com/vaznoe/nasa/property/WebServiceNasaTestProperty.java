package com.vaznoe.nasa.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author vaznoe
 * Date: 2/15/19
 */
@Component
public class WebServiceNasaTestProperty {

    @Value("${nasa.api.baseUrl}")
    private String baseUrl;

    @Value("${nasa.api.search}")
    private String search;

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getSearch() {
        return getBaseUrl() + search;
    }
}

package com.tan.annotation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tan.annotation.bean.AutoDetectDemoBean;
import com.tan.annotation.bean.IgnoreDemoBean;
import com.tan.annotation.bean.IgnorePropertiesDemoBean;
import com.tan.annotation.bean.ValueDemoBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @Description jackson testing
 * https://springframework.guru/jackson-annotations-json/
 * @Author zhengqiang.tan
 * @Date 2024/6/21 23:23
 */
public class BeanTest {
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
    }

    @After
    public void tearDown() throws Exception {
        objectMapper = null;
    }

    @Test
    public void testSerializingWithJsonIgnore()
            throws JsonProcessingException {
        String jsonString = objectMapper.writeValueAsString(new IgnoreDemoBean());
        System.out.println(jsonString);
        assertThat(jsonString, containsString("James Clark"));
        assertThat(jsonString, not(containsString("productId")));
    }

    @Test
    public void testDeSerializingWithJsonIgnore() throws IOException {
        String jsonString = "{\"personId\": 231, \"name\": \"Mary Parker\"}";
        ObjectMapper mapper = new ObjectMapper();
        IgnoreDemoBean bean = objectMapper.readValue(jsonString, IgnoreDemoBean.class);
        System.out.println(bean);
        assertThat(bean.name, is(equalTo("Mary Parker")));
        assertThat(bean.personId, is(not(equalTo(231L))));
    }

    @Test
    public void testSerializingWithJsonIgnoreProperties()
            throws JsonProcessingException {
        String jsonString = objectMapper.writeValueAsString(new IgnorePropertiesDemoBean());
        System.out.println(jsonString);
        assertThat(jsonString, containsString("James Clark"));
        assertThat(jsonString, not(containsString("userId")));
    }

    @Test
    public void testDeSerializingWithJsonIgnoreProperties() throws IOException {
        String jsonString = "{\"userId\": 231, \"name\": \"Mary Parker\", \"gender\": \"male\"}";
        ObjectMapper mapper = new ObjectMapper();
        IgnorePropertiesDemoBean bean = objectMapper.readValue(jsonString, IgnorePropertiesDemoBean.class);
        System.out.println(bean);
        assertThat(bean.name, is(equalTo("Mary Parker")));
        assertThat(bean.userId, is(not(equalTo(231L))));
    }

    @Test
    public void testSerializingWithJsonAutoDetect()
            throws JsonProcessingException {
        String jsonString = objectMapper.writeValueAsString(new AutoDetectDemoBean());
        System.out.println(jsonString);
        assertThat(jsonString, containsString("123"));
        assertThat(jsonString, containsString("James Clark"));
    }

    @Test
    public void testSerializingWithJsonValue() throws JsonProcessingException {
        String jsonString = objectMapper.writeValueAsString(new ValueDemoBean());
        System.out.println(jsonString);
        assertThat(jsonString, containsString("James Clark"));
        assertThat(jsonString, containsString("James Clark,123"));
    }
}


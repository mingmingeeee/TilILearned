package com.ssafy.ws;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssafy.ws.config.ApplicationConfig;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
// xml 파일일 경우 "(locations = "/application.xml")"
// Java class인 경우 "(classes = ApplicationConfig.class)"
public class AbstractTest {}

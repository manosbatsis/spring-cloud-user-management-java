package com.github.manosbatsis.services.event.config;

import java.util.Map;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.error.ErrorAttributeOptions.Include;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.WebRequest;

@Configuration
public class ErrorAttributesConfig {

  @Bean
  public ErrorAttributes errorAttributes() {
    return new DefaultErrorAttributes() {
      @Override
      public Map<String, Object> getErrorAttributes(
          WebRequest webRequest, ErrorAttributeOptions options) {
        return super.getErrorAttributes(
            webRequest,
            options.including(Include.EXCEPTION, Include.MESSAGE, Include.BINDING_ERRORS));
      }
    };
  }
}
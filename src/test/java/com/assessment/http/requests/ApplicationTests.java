package com.assessment.http.requests;

import com.assessment.http.requests.controller.HostController;
import com.assessment.http.requests.controller.RequestController;
import com.assessment.http.requests.controller.SiteController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTests {

    @Autowired
    SiteController siteController;

    @Autowired
    RequestController requestController;

    @Autowired
    HostController hostController;

    @Test
    void contextLoads() {
        assertThat(siteController).isNotNull();
        assertThat(requestController).isNotNull();
        assertThat(hostController).isNotNull();
    }

}

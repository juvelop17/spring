package com.example.zerobase.domain;

import com.example.zerobase.web.mock.FastcampusFakeController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FastcampusAdaptor {

    @Value("${server.port}")
    private int port;

    private RestTemplate restTemplate = new RestTemplate();
    ParameterizedTypeReference<List<FastcampusFakeController.FastCampusCourse>> responseType = new ParameterizedTypeReference<>() {
    };


    public List<FastcampusFakeController.FastCampusCourse> getOpenedZerobaseCourseList(LocalDate now) {
        return restTemplate.exchange("http://localhost:" + port + "/v1/api/fastcampus/open?dt=" + now.toString(), HttpMethod.GET, null, responseType)
                .getBody();
    }
}

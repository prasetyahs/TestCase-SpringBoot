package com.example.getapi.controller;


import com.example.getapi.entity.Person;
import com.example.getapi.entity.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class PersonController {

    @Value(value = "${base.url}")
    private String basUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public PersonController(RestTemplateBuilder restTemplate) {
        this.restTemplate = restTemplate.build();
    }


    @GetMapping("/")
    public String Person(Model model){
        RestResponse response = restTemplate.getForObject(basUrl+"/api/persons", RestResponse.class);
        assert response != null;
        model.addAttribute("title","Rest Api");
        model.addAttribute("persons", response.getData());
        return "fragments/data-person-rest";
    }

    @GetMapping("/xml")
    public String PersonXML(Model model){
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
        RestResponse response = restTemplate.exchange(basUrl+"/api/persons", HttpMethod.GET, new HttpEntity<>(headers), RestResponse.class).getBody();
        model.addAttribute("title","Rest XML");
        assert response != null;
        model.addAttribute("persons",response.getData());
        return "fragments/data-person-rest";
    }
}

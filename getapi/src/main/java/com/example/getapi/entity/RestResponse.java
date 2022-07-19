package com.example.getapi.entity;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;
import java.util.List;

@Data
@JacksonXmlRootElement(localName = "RestResponse",namespace = "com.example")
public class RestResponse {
    @JacksonXmlProperty(localName = "message")
    private String message;
    @JacksonXmlProperty(localName = "status")
    private int status;
    @JacksonXmlProperty(localName = "data")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Person> data;
}

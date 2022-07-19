package com.testcase.restapi.controller;

import com.testcase.restapi.entity.Person;
import com.testcase.restapi.entity.RestResponse;
import com.testcase.restapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(value = "/api",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
public class PersonController {

    private final PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping(value="/persons")
    public ResponseEntity<RestResponse> all(){
        List<Person> listPerson = personRepository.findAll();
        return new ResponseEntity<>(new RestResponse("Read Data All Success",200,listPerson), HttpStatus.OK);
    }

    @GetMapping(value = "/persons/{id}")
    public  ResponseEntity<RestResponse> getPersonById(@PathVariable("id") @Min(1) int id){
        return new ResponseEntity<>(new RestResponse("Available Data",200, personRepository.findById(id).orElseThrow()), HttpStatus.OK);
    }

    @PostMapping(value = "/persons")
    public ResponseEntity<RestResponse> addPerson(@Valid @RequestBody Person person){
        return new ResponseEntity<>(new RestResponse("Add Data Successfully",200, personRepository.save(person)), HttpStatus.OK);
    }

    @PutMapping(value = "/persons/{id}")
    public ResponseEntity<RestResponse> updatePerson(@PathVariable("id") @Min(1) int id, @Valid @RequestBody Person newPerson){
        Person person =  personRepository.findById(id).orElseThrow();
        person.setFirstname(newPerson.getFirstname());
        person.setLastname(newPerson.getLastname());
        person.setAddress(newPerson.getAddress());
        Person personSaved =  personRepository.save(person);
        return new ResponseEntity<>(new RestResponse("Updated Data Successfully",200, personSaved), HttpStatus.OK);

    }

    @DeleteMapping(value = "/persons/{id}")
    public ResponseEntity<RestResponse> deletePerson(@PathVariable("id") @Min(1) int id){
        Person person =  personRepository.findById(id).orElseThrow();
        personRepository.deleteById(person.getId());
        return new ResponseEntity<>(new RestResponse("Person with ID :"+id+" is deleted",200, null), HttpStatus.OK);
    }
}

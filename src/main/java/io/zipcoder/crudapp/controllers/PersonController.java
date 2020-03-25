package io.zipcoder.crudapp.controllers;

import io.zipcoder.crudapp.models.Person;
import io.zipcoder.crudapp.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping("/people/")
    public ResponseEntity<Iterable<Person>> show(){
        return new ResponseEntity<>(personService.getPersonList(), HttpStatus.OK);
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> showAll(@PathVariable Long id){
        return new ResponseEntity<>((personService.getPerson(id)), HttpStatus.OK);
    }

    @PostMapping("/people/")
    public ResponseEntity<Person> create(@RequestBody Person p) {
        return new ResponseEntity<>(personService.createPerson(p), HttpStatus.CREATED);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody Person person) {
        if (personService.getPerson(id) == null) {
            return create(person);
        }
        return new ResponseEntity<>(personService.updatePerson(id, person), HttpStatus.OK);
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(personService.deletePerson(id), HttpStatus.NOT_FOUND);
    }

}

package io.zipcoder.crudapp.services;

import io.zipcoder.crudapp.Repository.PersonRepository;
import io.zipcoder.crudapp.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public Person createPerson(Person person){
        return personRepository.save(person);
    }

    public Person getPerson(Long id){
        return personRepository.findOne(id);
    }

    public Iterable<Person> getPersonList(){
        return personRepository.findAll();
    }

    public Person updatePerson(Long id, Person person){
        Person originalPerson = personRepository.findOne(id);
        originalPerson.setFirstName(person.getFirstName());
        originalPerson.setLastName(person.getLastName());
        return personRepository.save(originalPerson);
    }

    public Boolean deletePerson(Long id){
        personRepository.delete(id);
        return true;
    }

}

package co.simplon.springboot.simplecrud.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.springboot.simplecrud.model.People;
import co.simplon.springboot.simplecrud.repository.PeopleRepository;

@RestController
@RequestMapping("/api")
public class PeopleController {
    @Autowired
    PeopleRepository repository;
    
    @CrossOrigin
    @GetMapping("/people")
    List<People> getAllPeople(){
        return repository.findAll();
    }
    
    @CrossOrigin
    @GetMapping("/people/{id}")
    ResponseEntity<People> getPeopleById(@PathVariable(value="id") long id) {
        People people = repository.findOne(id);
        if(people == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(people);
    }
    
    @CrossOrigin
    @PostMapping("/people")
    People addPeople(@Valid @RequestBody People people){
        return repository.save(people);
    }
    @CrossOrigin
    @PostMapping("/peoples")
    List<People>addPeople(@Valid @RequestBody List<People> people){
    	return repository.save(people);
    }
    
    @CrossOrigin
    @PutMapping("/people/{id}")
    ResponseEntity<People> updatePeople(@PathVariable(value="id") long id, @Valid @RequestBody People people){
        People peopleToUpdate = repository.findOne(id);
        if(peopleToUpdate == null)
            return ResponseEntity.notFound().build();
        
        // Update the mandatory attributes
        peopleToUpdate.setFirstname(people.getFirstname());
        peopleToUpdate.setName(people.getName());
        
        // Update all other not null attributes
        if(people.getAddress() != null)
            peopleToUpdate.setAddress(people.getAddress());
        
        if(people.getPhone() != null)
            peopleToUpdate.setPhone(people.getPhone());
        
        if(people.getEmail() != null)
            peopleToUpdate.setEmail(people.getEmail());
        
        People updatedPeople = repository.save(peopleToUpdate);
        return ResponseEntity.ok(updatedPeople);
    }
    
    @CrossOrigin
    @DeleteMapping("/people/{id}")
    ResponseEntity<People> deletePeople(@PathVariable(value="id") long id){
        People people = repository.findOne(id);
        if(people == null)
            return ResponseEntity.notFound().build();
        
        repository.delete(people);
        return ResponseEntity.ok().build();
    }}
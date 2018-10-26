package co.simplon.springboot.simplecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.springboot.simplecrud.model.People;

public interface PeopleRepository extends JpaRepository<People, Long> {

}
package com.batch.batch_demo.repository;

import com.batch.batch_demo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface PersonRepository extends JpaRepository<Person,Long> {


}

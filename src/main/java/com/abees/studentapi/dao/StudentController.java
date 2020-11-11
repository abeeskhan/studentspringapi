package com.abees.studentapi.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.abees.studentapi.entity.Student;

public interface StudentController extends MongoRepository<Student, String>{

}

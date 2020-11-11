package com.abees.studentapi.resource;

import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abees.studentapi.dao.StudentController;
import com.abees.studentapi.entity.Student;
import com.abees.studentapi.entity.StudentResponse;

@RestController
public class StudentResource {
	@Autowired
	private StudentController controller;

	/**
	 * Get all Students in the database
	 * 
	 * @return
	 */
	@GetMapping("/")
	public StudentResponse getAll() {
		System.out.println(HttpStatus.OK);
		return new StudentResponse(Response.SC_OK, controller.findAll());
	}

	/**
	 * @param studentDetails
	 * @return
	 */
	@PostMapping("/insert")
	public StudentResponse insertStudent(@RequestBody Student studentDetails) {
		System.out.println("Student Roll Number --> " + studentDetails.getId());
		System.out.println("Student Name --> " + studentDetails.getName());

		try {
			if (controller.findById(studentDetails.getId()).isEmpty()) {
				if (studentDetails.getName() == null || studentDetails.getName().isEmpty())
					return new StudentResponse(Response.SC_FORBIDDEN, "Please enter name");
				controller.save(studentDetails);
			} else
				return new StudentResponse(Response.SC_CONFLICT, "Already Existing ID");
		} catch (Exception e) {
			return new StudentResponse(Response.SC_INTERNAL_SERVER_ERROR, "Insert Failed, Please contact admin.");
		}

		return new StudentResponse(Response.SC_CREATED, "Inserted Succesfully");
	}

//	Find a student by roll number
	@GetMapping("/{rollnumber}")
	public StudentResponse findStudentByRollNumber(@PathVariable("rollnumber") String rollnumber) {
		System.out.println("Roll Number --> " + rollnumber);
		try {
			Optional<Student> studentDetails = controller.findById(rollnumber);
			if (studentDetails.isEmpty()) {
				return new StudentResponse(Response.SC_NOT_FOUND, "Details not found");
			} else {
				return new StudentResponse(Response.SC_OK, studentDetails);
			}
		} catch (Exception e) {
			return new StudentResponse(Response.SC_INTERNAL_SERVER_ERROR, "Error please contact admin");
		}
	}
}

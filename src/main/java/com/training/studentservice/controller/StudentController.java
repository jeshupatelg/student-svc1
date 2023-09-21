package com.training.studentservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.studentservice.model.Student;
import com.training.studentservice.repository.StudentRepository;

@RestController
@RequestMapping("/studapi")
public class StudentController {
	
	@Autowired
	StudentRepository repository;
	
	@GetMapping("/stud")
	public String greet() {
		return "Hi There!!";
	}
	
	@GetMapping("/list")
	public List<Student> getStudentList(){
		return repository.findAll();
		//return Arrays.asList(new Student[] {new Student("Soham",12,'A'),new Student("Peeyush",15,'A')});
	}
	
	@PostMapping("/post")
	public void post(@RequestBody Student student) {
		repository.save(student);
	}
	
	@GetMapping("/stud/{id}")
	public Student getById(@PathVariable long id) {
		Optional<Student> opt = repository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		else return null;
	}
	
	@PostMapping("/stud")
	public Student getStudentById(@RequestBody long id) {
		Optional<Student> opt = repository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		else return null;
	}
	
	@PutMapping("/update/{id}")
	public void update(@RequestBody Student student, @PathVariable long id) {
		Optional<Student> opt = repository.findById(id);
		if(opt.isPresent()) {
			Student stud = opt.get();
			stud.setGrade(student.getGrade());
			stud.setName(student.getName());
			stud.setRoll(student.getRoll());
			repository.save(stud);
		}
		else {System.out.println("Not found");}
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	
	
	
}

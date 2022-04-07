package com.example.crudtest.crudtest.RestApis;

import com.example.crudtest.crudtest.Model.StudentModel;
import com.example.crudtest.crudtest.Srevices.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/backend")
public class Apis {
    @Autowired
    StudentService studentService;

    @PostMapping(path = "/addstudent")
    public ResponseEntity<?> addStudent(@RequestBody StudentModel studentModel) {
        return this.studentService.addStudent(studentModel);
    }

    @PutMapping(path = "/updatestudent/{sId}")
    public ResponseEntity<?> updateStudent(@PathVariable String sId, @RequestBody StudentModel studentModel) {
        return this.studentService.updateStudent(Long.parseLong(sId), studentModel);
    }

    @DeleteMapping(path = "/deletestudent/{sId}")
    public ResponseEntity<?> deleteStudent(@PathVariable String sId) {
        return this.studentService.deleteStudent(Long.parseLong(sId));
    }

    @GetMapping(path = "/getstudents")
    public ResponseEntity<?> getAllStudents() {
        return this.studentService.getAllStudents();
    }

    @GetMapping(path = "/getstudentdetails/{sId}")
    public ResponseEntity<?> getStudentDetails(@PathVariable String sId) {
        return this.studentService.getStudentDetails(Long.parseLong(sId));
    }
}

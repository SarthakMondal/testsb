package com.example.crudtest.crudtest.Srevices;

import java.util.List;

import com.example.crudtest.crudtest.Model.StudentModel;
import com.example.crudtest.crudtest.Repo.StudentRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepo studentRepo;

    public ResponseEntity<?> addStudent(StudentModel studentModel) {
        ResponseEntity<?> response = null;

        try {
            studentRepo.save(studentModel);
            response = new ResponseEntity<>(studentModel, HttpStatus.OK);
            return response;
        }

        catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    public ResponseEntity<?> updateStudent(long sId, StudentModel studentOld) {
        ResponseEntity<?> response = null;

        try {
            StudentModel studentNew = studentRepo.findById(sId).get();
            studentNew.setName(studentOld.getName());
            studentNew.setYear(studentOld.getYear());
            studentNew.setMarks(studentOld.getMarks());
            studentRepo.save(studentNew);

            response = new ResponseEntity<>(studentNew, HttpStatus.OK);
            return response;
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    public ResponseEntity<?> deleteStudent(long sId) {
        ResponseEntity<?> response = null;

        try {
            StudentModel student = studentRepo.findById(sId).get();
            studentRepo.deleteById(sId);
            response = new ResponseEntity<>(student, HttpStatus.OK);
            return response;
        }

        catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    public ResponseEntity<?> getAllStudents() {
        ResponseEntity<?> response = null;

        try {
            List<StudentModel> students = studentRepo.findAll();
            response = new ResponseEntity<>(students, HttpStatus.OK);
            return response;
        }

        catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    public ResponseEntity<?> getStudentDetails(long sId) {
        ResponseEntity<?> response = null;

        try {
            StudentModel student = studentRepo.findById(sId).get();
            response = new ResponseEntity<>(student, HttpStatus.OK);
            return response;
        }

        catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }
}

package com.example.securityproject.service;

import com.example.securityproject.model.Student;
import com.example.securityproject.repository.GroupRepository;
import com.example.securityproject.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final GroupRepository groupRepository;

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public void saveStudent(Student student) {
        Student student1 = mapToEntity(student);
        repository.save(student1);
    }

    public Student getByIdStudent(Long id) {
        return repository.findById(id).get();
    }

    public void deleteByIdStudent(Long id) {
        repository.deleteById(id);
    }

    public void updateStudent(Student student, Long id) {
        repository.getById(id);
        repository.save(student);
    }

    public Student mapToEntity(Student student) {
        Student student1 = new Student();
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        student1.setStudyFormat(student.getStudyFormat());
        student1.setGroup(groupRepository.getById(student.getGroup().getId()));
        return student;
    }

    public List<Student> search(String name) {
        String text = name == null ? "" : name;
        return repository.searchStudentByFirstName(text.toUpperCase());
    }

    public List<Student> getSearch(String name) {
        return search(name);
    }
}

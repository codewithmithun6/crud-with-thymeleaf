package com.codewithmithun.crudwiththymeleaf.service;

import com.codewithmithun.crudwiththymeleaf.entities.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeacherService {

    List<Teacher> getAllTeachers();

    Teacher saveTeacher(Teacher teacher);

    Teacher getTeacherById(Long id);

    Teacher updateTeacher(Teacher teacher);

    void deleteTeacherById(Long id);

    public Page<Teacher> getAllTeachers(Pageable pageable);
}

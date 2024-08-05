package com.kimikevin.nomad_backend.course;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course getCourseById(Long courseNo) {
        return courseRepository.findById(courseNo).orElseThrow(() -> new IllegalArgumentException("Courses cannot be found"));
    }

    public Course updateCourse(Long courseNo, Course courseDetails) {
        Course course = getCourseById(courseNo);

        course.setCourseName(courseDetails.getCourseName());
        course.setCourseDescription(courseDetails.getCourseDescription());
        return courseRepository.save(course);
    }

    public void deleteCourse(Long courseNo) {
        courseRepository.deleteById(courseNo);
    }
}

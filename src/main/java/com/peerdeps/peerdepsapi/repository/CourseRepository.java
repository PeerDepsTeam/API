package com.peerdeps.peerdepsapi.repository;

import com.peerdeps.peerdepsapi.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
}

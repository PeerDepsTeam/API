package com.peerdeps.peerdepsapi.endpoint.controller;

import com.peerdeps.peerdepsapi.model.Course;
import com.peerdeps.peerdepsapi.service.CourseService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CourseController {
  private final CourseService service;

  @GetMapping(value = "/courses")
  public List<Course> getCourses(
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "pageSize", required = false) Integer pageSize
  ){
    return service.findAll(page, pageSize);
  }
}

package com.peerdeps.peerdepsapi.service;

import com.peerdeps.peerdepsapi.model.Course;
import com.peerdeps.peerdepsapi.model.User;
import com.peerdeps.peerdepsapi.model.exception.NotFoundException;
import com.peerdeps.peerdepsapi.repository.CourseRepository;
import java.util.Comparator;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseService {
  private final CourseRepository repository;
  public List<Course> findAll(Integer page, Integer pageSize){
    int pageValue = page == null ? 0 : page - 1;
    int sizeValue = pageSize == null ? 10 : pageSize;
    Pageable pageable = PageRequest.of(pageValue, sizeValue);
    return repository.findAll(pageable).stream().toList();
  }

  public Course findById(String id){
    return repository.findById(id)
        .orElseThrow(()->new NotFoundException("Course."+id+" is not found"));
  }

}

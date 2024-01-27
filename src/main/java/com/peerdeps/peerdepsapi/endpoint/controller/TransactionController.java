package com.peerdeps.peerdepsapi.endpoint.controller;

import com.peerdeps.peerdepsapi.endpoint.mapper.TransactionMapper;
import com.peerdeps.peerdepsapi.endpoint.restmodel.Transaction;
import com.peerdeps.peerdepsapi.service.TransactionService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class TransactionController {
  private final TransactionService service;
  private final TransactionMapper mapper;

  @GetMapping(value = "/users/{userId}/transactions")
  public List<Transaction> getTransactions(@PathVariable("userId") String userId){
    return service.findByUserId(userId).stream()
        .map(mapper::toRest)
        .collect(Collectors.toList());
  }
}

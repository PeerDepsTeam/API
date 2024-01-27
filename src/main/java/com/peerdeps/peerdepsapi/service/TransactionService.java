package com.peerdeps.peerdepsapi.service;

import com.peerdeps.peerdepsapi.model.Transaction;
import com.peerdeps.peerdepsapi.repository.TransactionRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {
  private final TransactionRepository repository;

  public List<Transaction> findByUserId(String userId){
    return repository.findByUserId(userId);
  }

}

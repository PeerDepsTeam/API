package com.peerdeps.peerdepsapi.endpoint.mapper;

import com.peerdeps.peerdepsapi.endpoint.restmodel.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

  public Transaction toRest(com.peerdeps.peerdepsapi.model.Transaction model){
    return Transaction.builder()
        .id(model.getId())
        .userId(model.getUser().getId())
        .amount(model.getAmount())
        .creationDatetime(model.getCreationDatetime())
        .type(model.getType())
        .build();
  }
}

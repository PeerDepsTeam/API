package com.peerdeps.peerdepsapi.service.calculator;

import com.peerdeps.peerdepsapi.model.Transaction;
import com.peerdeps.peerdepsapi.model.User;
import com.peerdeps.peerdepsapi.service.TransactionService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BalanceCalculator {
  private final TransactionService transactionService;

  public User computeBalance(User user){
    List<Transaction> transactions = transactionService.findByUserId(user.getId());
    var incomeAmount = transactions.stream()
        .filter(trans -> trans.getType().equals(Transaction.TransactionType.INCOME))
        .map(Transaction::getAmount)
        .reduce(Double::sum).get();
    var outcomeAmount = transactions.stream()
        .filter(trans -> trans.getType().equals(Transaction.TransactionType.OUTCOME))
        .map(Transaction::getAmount)
        .reduce(Double::sum).get();
    var currentBalance = user.getBudget().getInitialCapital() - outcomeAmount + incomeAmount;
    user.getBudget().setCurrentCapital(currentBalance);
    return user;
  }
}

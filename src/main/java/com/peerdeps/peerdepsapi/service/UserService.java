package com.peerdeps.peerdepsapi.service;

import com.peerdeps.peerdepsapi.model.exception.NotFoundException;
import com.peerdeps.peerdepsapi.repository.UserRepository;
import com.peerdeps.peerdepsapi.model.User;
import com.peerdeps.peerdepsapi.service.calculator.BalanceCalculator;
import java.util.Comparator;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
  private final UserRepository repository;
  private final BalanceCalculator balanceCalculator;

  public User findById(String userId){
    var user =  repository.findById(userId)
        .orElseThrow(()->new NotFoundException("User."+userId+" is not found"));
    return balanceCalculator.computeBalance(user);
  }

  public User getUserByFirebaseIdAndEmail(String uid, String email) {
    return null;
  }

  public List<User> findAll(Integer page, Integer pageSize){
    int pageValue = page == null ? 0 : page - 1;
    int sizeValue = pageSize == null ? 10 : pageSize;
    Pageable pageable = PageRequest.of(pageValue, sizeValue);
    return repository.findAll(pageable).stream().toList().stream()
        .map(balanceCalculator::computeBalance)
        .sorted(Comparator.comparingDouble(user -> getCurrentCapital((User) user))
            .reversed())
        .toList();
  }

  private double getCurrentCapital(User user){
    return user.getBudget().getCurrentCapital();
  }

}

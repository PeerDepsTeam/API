package com.peerdeps.peerdepsapi;

import com.peerdeps.peerdepsapi.model.Budget;
import com.peerdeps.peerdepsapi.model.Savings;
import com.peerdeps.peerdepsapi.model.User;
import com.peerdeps.peerdepsapi.repository.UserRepository;
import java.time.Instant;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DatabaseInsert implements CommandLineRunner {
  private final UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {
    for (int i = 1; i <= 10; i++) {
      User user = new User();
      user.setId(String.valueOf(i));
      user.setFirstName("FirstName" + i);
      user.setLastName("LastName" + i);
      user.setEmail("user" + i + "@example.com");
      user.setSex(i % 2 == 0 ? User.Sex.M : User.Sex.F);
      user.setUsername("user" + i);
      user.setBirthdate(Instant.now());

      Budget budget = new Budget();
      budget.setId(String.valueOf(i));
      budget.setUserId(user.getId());

      Savings savings = new Savings();
      savings.setId(String.valueOf(i));
      savings.setAmount(50.0 * i);

      budget.setSavings(savings);
      user.setBudget(budget);

      userRepository.save(user);
    }
  }
}

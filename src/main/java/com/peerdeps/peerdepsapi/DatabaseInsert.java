package com.peerdeps.peerdepsapi;

import com.peerdeps.peerdepsapi.model.Budget;
import com.peerdeps.peerdepsapi.model.Course;
import com.peerdeps.peerdepsapi.model.Savings;
import com.peerdeps.peerdepsapi.model.Transaction;
import com.peerdeps.peerdepsapi.model.User;
import com.peerdeps.peerdepsapi.repository.CourseRepository;
import com.peerdeps.peerdepsapi.repository.TransactionRepository;
import com.peerdeps.peerdepsapi.repository.UserRepository;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DatabaseInsert implements CommandLineRunner {
  private final UserRepository userRepository;
  private final TransactionRepository transactionRepository;
  private final CourseRepository courseRepository;

  @Override
  public void run(String... args) throws Exception {
    for (int i = 1; i <= 10; i++) {
      Random random = new Random();
      var income =  10 + (500 - 10) * random.nextDouble();
      var outcome =  10 + (500 - 10) * random.nextDouble();

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
      budget.setInitialCapital(10000.0);

      Savings savings = new Savings();
      savings.setId(String.valueOf(i));
      savings.setAmount(50.0 * i);

      budget.setSavings(savings);
      user.setBudget(budget);

      Transaction incomeTransaction = new Transaction();
      incomeTransaction.setId("income" + i);
      incomeTransaction.setUser(user);
      incomeTransaction.setAmount(income * i);
      incomeTransaction.setCreationDatetime(Instant.now());
      incomeTransaction.setType(Transaction.TransactionType.INCOME);

      Transaction outcomeTransaction = new Transaction();
      outcomeTransaction.setId("outcome" + i);
      outcomeTransaction.setUser(user);
      outcomeTransaction.setAmount(outcome * i);
      outcomeTransaction.setCreationDatetime(Instant.now());
      outcomeTransaction.setType(Transaction.TransactionType.OUTCOME);

      transactionRepository.save(outcomeTransaction);

      transactionRepository.save(incomeTransaction);

      userRepository.save(user);
    }

    User user = new User();
    user.setId("user");
    user.setFirstName("FirstName");
    user.setLastName("LastName");
    user.setEmail("test@gmail.com");
    user.setSex(User.Sex.M);
    user.setUsername("user");
    user.setBirthdate(Instant.now());

    Budget budget = new Budget();
    budget.setId("bgd");
    budget.setUserId(user.getId());
    budget.setInitialCapital(10000.0);

    Savings savings = new Savings();
    savings.setId("svg");
    savings.setAmount(50.0);

    budget.setSavings(savings);
    user.setBudget(budget);

    userRepository.save(user);

    Course course3 = new Course();
    course3.setId("course3");
    course3.setModule("Investing Principles for Beginners");
    course3.setContent("Understand the fundamental principles of investing, including stocks, bonds, and mutual funds.");
    course3.setRequirements("Basic knowledge of personal finance is recommended.");
    course3.setBanner("https://example.com/investing_principles_banner.jpg");
    course3.setDescription("Explore the key concepts and strategies for successful investing.");

    Course course4 = new Course();
    course4.setId("course4");
    course4.setModule("Real Estate Investment Strategies");
    course4.setContent("Learn about real estate investment opportunities, financing, and risk management.");
    course4.setRequirements("Basic understanding of investing principles.");
    course4.setBanner("https://example.com/real_estate_investment_banner.jpg");
    course4.setDescription("Discover effective strategies for investing in real estate markets.");

    Course course5 = new Course();
    course5.setId("course5");
    course5.setModule("Retirement Planning Essentials");
    course5.setContent("Plan for a secure retirement by exploring various retirement savings options and strategies.");
    course5.setRequirements("Basic knowledge of personal finance and investing.");
    course5.setBanner("https://example.com/retirement_planning_banner.jpg");
    course5.setDescription("Understand the essential steps to plan for a financially secure retirement.");

    Course course1 = new Course();
    course1.setId("course1");
    course1.setModule("Introduction to Personal Finance");
    course1.setContent("Explore the basics of personal finance, including budgeting, savings, and investments.");
    course1.setRequirements("No prerequisites. Suitable for beginners.");
    course1.setBanner("https://example.com/intro_to_finance_banner.jpg");
    course1.setDescription("This course provides an overview of essential personal finance concepts.");

    Course course2 = new Course();
    course2.setId("course2");
    course2.setModule("Managing Debt: Strategies for Success");
    course2.setContent("Learn effective strategies for managing and reducing debt.");
    course2.setRequirements("Basic understanding of personal finance recommended.");
    course2.setBanner("https://example.com/debt_management_banner.jpg");
    course2.setDescription("Explore practical techniques to manage and eliminate debt.");

    courseRepository.saveAll(List.of(course1, course2, course3, course4, course5));

  }
}

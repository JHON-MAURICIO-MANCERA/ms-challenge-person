package co.com.sofka.model.budget.gateways;


import co.com.sofka.model.budget.Budget;
import reactor.core.publisher.Mono;


public interface BudgetRepository {
    Mono<Budget> findByBudgetId(String id);

    Mono<Void> saveData(Budget budget);
}

package com.orhanararat.repository.bankaccount;

import com.orhanararat.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long>, BankAccountDslRepository {
    BankAccount findByAccountNumber(String accountNumber);
}

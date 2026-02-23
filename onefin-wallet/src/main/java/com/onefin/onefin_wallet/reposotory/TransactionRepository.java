package com.onefin.onefin_wallet.reposotory;

import com.onefin.onefin_wallet.entity.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
}

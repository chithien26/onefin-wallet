package com.onefin.onefin_wallet.reposotory;

import com.onefin.onefin_wallet.entity.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}

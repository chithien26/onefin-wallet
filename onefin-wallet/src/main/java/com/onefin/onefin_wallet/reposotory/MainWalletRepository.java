package com.onefin.onefin_wallet.reposotory;

import com.onefin.onefin_wallet.entity.wallet.MainWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainWalletRepository extends JpaRepository<MainWallet, String> {
    MainWallet findByWalletNumber(String walletNumber);

    boolean existsByWalletNumber(String walletNumber);
//    @Modifying
//    @Transactional
//    @Query("UPDATE Wallet w SET w.balance = w.balance - :amount " +
//            "WHERE w.walletNumber = :mainWalletNumber AND w.balance >= :amount")
//    int debit(@Param("mainWalletNumber") String mainWalletNumber, @Param("amount") BigDecimal amount);
}

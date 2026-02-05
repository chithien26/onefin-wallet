package com.onefin.onefin_wallet.reposotory;

import com.onefin.onefin_wallet.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);

    User findByUsername(String username);

    boolean existsByUsername(String username);
}

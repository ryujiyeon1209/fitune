package com.fun.fitune.db.repository;

import com.fun.fitune.db.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserSeq(Integer userSeq);
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);

    @Query(value = "SELECT * FROM user WHERE user_seq <> ?1 ORDER BY RAND() LIMIT 5", nativeQuery = true)
    List<User> findRandomUsers(int userSeq);
}

package com.business.auth.security.repository;

import com.business.auth.security.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

  @Query("select t from Token t inner join Account a on t.account.id = a.id " +
          "where a.id = :id and (t.expired = false or t.revoked = false)")
  List<Token> findAllValidTokenByUser(Long id);

  Optional<Token> findByToken(String token);
}
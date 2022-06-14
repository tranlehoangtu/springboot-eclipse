package com.javacode.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javacode.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>{

}

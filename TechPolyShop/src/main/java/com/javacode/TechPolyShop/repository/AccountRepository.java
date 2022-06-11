package com.javacode.TechPolyShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javacode.TechPolyShop.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>{

}

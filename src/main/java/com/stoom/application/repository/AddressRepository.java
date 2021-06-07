package com.stoom.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.stoom.application.domain.Address;

/**
 * Interface that represents a repository for {@code Address}.
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
}
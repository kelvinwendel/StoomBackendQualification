package com.stoom.application.services;

import com.stoom.application.domain.Address;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface that represents operations of an Address.
 */
public interface IAddressService {
    Address create(Address address);
    Address findByID(Long id);
    Page<Address> findAll(Pageable pageable);
    Address update(Long id, Address address);
    void delete(Long id);
    InputStreamResource exportDataToXLSX(Pageable pageable);
}

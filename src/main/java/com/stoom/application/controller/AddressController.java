package com.stoom.application.controller;

import com.stoom.application.domain.Address;
import com.stoom.application.services.IAddressService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Controller responsible for {@link Address} behavior.
 */
@RestController
@RequestMapping(value = "/addresses")
public class AddressController {

    @Autowired
    private IAddressService addressService;

    /**
     * Method responsible for find an {@code Address} by ID.
     *
     * @param id ID of Address to find.
     * @return An {@code ResponseEntity<Address>} with address found.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Address> findAddress(@PathVariable Long id) {
        Address address = addressService.findByID(id);
        return ResponseEntity.ok().body(address);
    }

    /**
     * Method responsible for find all addresses.
     *
     * @return A {@code ResponseEntity<Page<Address>>} with addresses found.
     */
    @GetMapping
    public ResponseEntity<Page<Address>> findAllAddresses(@PageableDefault(page = 0, size = 5, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Address> addresses = addressService.findAll(pageable);
        return ResponseEntity.ok(addresses);
    }

    /**
     * Method Responsible for edit an {@code Address} by specified ID.
     *
     * @param id      ID of address to edit.
     * @param address {@code Address} updated.
     * @return An {@code ResponseEntity<Address>} with address edited.
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<Address> editAddress(@PathVariable Long id, @RequestBody Address address) {
        Address updatedAddress = addressService.update(id, address);
        return ResponseEntity.ok().body(updatedAddress);
    }

    /**
     * Method responsible for create an {@code Address}.
     *
     * @param address {@code Address} to be created.
     * @return A {@code ResponseEntity<Address>}.
     */
    @SneakyThrows
    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        Address createdAddress = addressService.create(address);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdAddress.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Method responsible for remove an {@code Address}.
     *
     * @param id ID of {@code Address} to be removed.
     * @return A {@code ResponseEntity<Void>} with no content.
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> removeAddress(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Method responsible for generate a '.XLSX' file from all Addresses.
     *
     * @return A '.XLSX' file from addresses.
     */
    @SneakyThrows
    @GetMapping(value = "/export")
    public ResponseEntity<InputStreamResource> exportToXLSX(@PageableDefault(page = 0, size = 5, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Addresses.xlsx");
        InputStreamResource stream = addressService.exportDataToXLSX(pageable);

        return ResponseEntity.ok().headers(headers).body(stream);
    }
}
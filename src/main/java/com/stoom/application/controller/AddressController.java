package com.stoom.application.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.stoom.application.domain.Address;
import com.stoom.application.service.AddressService;

/**
 * Controller responsible for {@link Address} behavior.
 */
@RestController
@RequestMapping(value = "/addresses")
public class AddressController {

	@Autowired
	private AddressService addressService;

	/**
	 * Method responsible for find an {@code Address} by ID.
	 *
	 * @param id
	 *   ID of Address to find.
	 *
	 * @return
	 *   An {@code ResponseEntity<Address>} with address found.
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Address> findAddress(@PathVariable Long id) {
		Address address = addressService.findByID(id);
		return ResponseEntity.ok().body(address);
	}

	/**
	 * Method responsible for find all addresses.
	 *
	 * @return
	 *   A {@code ResponseEntity<List<Address>>} with addresses found.
	 */
	@GetMapping
	public ResponseEntity<List<Address>> findAllAddresses() {
		List<Address> addresses = addressService.findAll();
		return ResponseEntity.ok(addresses);
	}

	/**
	 * Method Responsible for edit an {@code Address} by specified ID.
	 *
	 * @param id
	 *   ID of address to edit.
	 * @param address
	 *   {@code Address} updated.
	 *
	 * @return
	 *   An {@code ResponseEntity<Address>} with address edited.
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Address> editAddress(@PathVariable Long id, @RequestBody Address address) {
		Address updatedAddress = addressService.update(id, address);
		return ResponseEntity.ok().body(updatedAddress);
	}

	/**
	 * Method responsible for create an {@code Address}.
	 *
	 * @param address
	 *   {@code Address} to be created.
	 *
	 * @return
	 *   A {@code ResponseEntity<Address>}.
	 *
	 * @throws IOException Exception occurred by problems of IO.
	 */
	@PostMapping
	public ResponseEntity<Address> createAddress(@RequestBody Address address) throws IOException {
		Address createdAddress = addressService.create(address);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdAddress.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	/**
	 * Method responsible for remove an {@code Address}. 
	 *
	 * @param id
	 *   ID of {@code Address} to be removed.
	 *
	 * @return
	 *   A {@code ResponseEntity<Void>} with no content.
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> removeAddress(@PathVariable Long id){
		addressService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
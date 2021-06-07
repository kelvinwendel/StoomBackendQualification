package com.stoom.qualification.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stoom.application.QualificationApplication;
import com.stoom.application.domain.Address;
import com.stoom.application.service.AddressService;

/**
 * Class responsible for test a CRUD (Create, Read, Update, Delete) of {@code AddressController}.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = QualificationApplication.class)
public class AddressControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	AddressService addressService;

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * Responsible for test an create operation and expect a status (201 - Created).
	 *
	 * @throws JsonProcessingException Exception occurred during a mapping of address.
	 * @throws Exception Any exception.
	 */
	@Test
	public void shouldReturnCreated_WhenCreateAddress() throws JsonProcessingException, Exception {
		Address address = new Address(null, "Rua Tiradentes", 508, "Loja Magazine Luiza", "Centro", "Araras",
				"São Paulo", "Brasil", "13600-070", null, null);

		mockMvc.perform(
				post("/addresses").contentType("application/json").content(objectMapper.writeValueAsString(address)))
				.andExpect(status().isCreated());
	}

	/**
	 * Responsible for test an find by ID operation and expect a status (200 - OK).
	 *
	 * @throws Exception Any exception.
	 */
	@Test
	public void shouldReturnSuccess_WhenFindAddress() throws Exception {
		mockMvc.perform(get("/addresses/{id}", 1L)).andExpect(status().isOk());
	}

	/**
	 * Responsible for test an find all operation and expect a status (200 - OK). 
	 *
	 * @throws Exception Any exception.
	 */
	@Test
	public void shouldReturnSuccess_WhenFindAllAddresses() throws Exception {
		mockMvc.perform(get("/addresses")).andExpect(status().isOk());
	}

	/**
	 * Responsible for test an edit operation and expect a status (200 - OK). 
	 *
	 * @throws JsonProcessingException Exception occurred during a mapping of address.
	 * @throws Exception Exception Any exception.
	 */
	@Test
	public void shouldReturnSuccess_WhenEditAddress() throws JsonProcessingException, Exception {
		Address address = new Address(1L, "Av. Dona Renata", 270, "Casa", "Centro", "Araras", "São Paulo", "Brasil",
				"13600-000", null, null);

		mockMvc.perform(put("/addresses/{id}", 1L).contentType("application/json")
				.content(objectMapper.writeValueAsString(address))).andExpect(status().isOk());
	}

	/**
	 * Responsible for test an remove operation and expect a status (204 - No content).
	 *
	 * @throws Exception Exception Any exception.
	 */
	@Test
	public void shouldReturnNoContent_WhenRemoveAddress() throws Exception {
		mockMvc.perform(delete("/addresses/{id}", 1L)).andExpect(status().isNoContent());
	}
}
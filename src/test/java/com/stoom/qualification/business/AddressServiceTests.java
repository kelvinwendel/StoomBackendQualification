package com.stoom.qualification.business;

import com.stoom.application.domain.Address;
import com.stoom.application.repository.AddressRepository;
import com.stoom.application.service.AddressService;
import com.stoom.application.util.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Class responsible for test a CRUD (Create, Read, Update, Delete) of {@code Address}.
 */
public class AddressServiceTests {

    private Address payloadAddress;

    private List<Address> payloadAddresses;

    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        payloadAddress = (Address) TestUtilities.mapJsonToClass("payloadAddress", Address.class, false);
        payloadAddresses = (List<Address>) TestUtilities.mapJsonToClass("payloadAddresses",
                Address.class, true);
    }

    /**
     * Responsible for test a find by ID operation.
     */
    @Test
    public void shouldReturnAddressSearched() {
        Mockito.when(addressRepository.findById(1L)).thenReturn(Optional.ofNullable(payloadAddress));
        Address addressSearched = addressService.findByID(1L);

        Assertions.assertEquals("Rua Tiradentes", addressSearched.getStreetName());
        Assertions.assertEquals(508, addressSearched.getNumber());
        Assertions.assertEquals("Araras", addressSearched.getCity());
        Assertions.assertEquals("13606-070", addressSearched.getZipcode());
    }

    /**
     * Responsible for test a find all operation.
     */
    @Test
    public void shouldReturnAllAddresses() {
        Mockito.when(addressRepository.findAll()).thenReturn(payloadAddresses);
        List<Address> addresses = addressService.findAll();

        Address firstAddress = addresses.stream().findFirst().get();
        Address lastAddress = addresses.stream().max(Comparator.comparing(Address::getId)).get();

        Assertions.assertEquals("Rua Tiradentes", firstAddress.getStreetName());
        Assertions.assertEquals(508, firstAddress.getNumber());
        Assertions.assertEquals("Araras", firstAddress.getCity());
        Assertions.assertEquals("13606-070", firstAddress.getZipcode());

        Assertions.assertEquals("Av. Dona Renata", lastAddress.getStreetName());
        Assertions.assertEquals(2914, lastAddress.getNumber());
        Assertions.assertEquals("Araras", lastAddress.getCity());
        Assertions.assertEquals("13600-001", lastAddress.getZipcode());
    }

    /**
     * Responsible for test an edit operation.
     */
    @Test
    public void shouldReturnUpdatedAddress() {
        Mockito.when(addressRepository.findById(1L)).thenReturn(Optional.ofNullable(payloadAddress));
        Address lastAddress = payloadAddresses.stream().max(Comparator.comparing(Address::getId)).get();
        Mockito.when(addressRepository.save(payloadAddress)).thenReturn(lastAddress);

        Address updatedAddress = addressService.update(1L, lastAddress);
        Assertions.assertEquals("Av. Dona Renata", updatedAddress.getStreetName());
        Assertions.assertEquals(2914, updatedAddress.getNumber());
        Assertions.assertEquals("Vila Michelin", updatedAddress.getNeighbourhood());
        Assertions.assertEquals("13600-001", updatedAddress.getZipcode());
    }

    /**
     * Responsible for test an create operation.
     */
    @Test
    public void shouldReturnCreatedAddress() {
        Mockito.when(addressRepository.save(payloadAddress)).thenReturn(payloadAddress);
        Address addressCreated = addressService.create(payloadAddress);

        Assertions.assertEquals("Rua Tiradentes", addressCreated.getStreetName());
        Assertions.assertEquals(508, addressCreated.getNumber());
        Assertions.assertEquals("Araras", addressCreated.getCity());
        Assertions.assertEquals("13606-070", addressCreated.getZipcode());
    }

    /**
     * Responsible for test a delete operation.
     */
    @Test
    public void shouldDeleteAnAddress() {
        Mockito.when(addressRepository.findById(1L)).thenReturn(Optional.ofNullable(payloadAddress));
        addressService.delete(1L);
        Mockito.verify(addressRepository).delete(payloadAddress);
    }
}
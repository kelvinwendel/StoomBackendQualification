package com.stoom.application.service;

import com.stoom.application.domain.Address;
import com.stoom.application.exception.ObjectNotFoundException;
import com.stoom.application.repository.AddressRepository;
import com.stoom.application.util.ExportUtilities;
import lombok.SneakyThrows;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class that represents the services supported by {@code Address}.
 */
@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    private static final String URL_API_GEOLOCATION = "https://maps.googleapis.com/maps/api/geocode/json?address=";

    private static final String KEY_API_GEOLOCATION = "AIzaSyCj0cY2yEvVfYhAaTz3-P2MW-YRKmhz5Uw";

    /**
     * Responsible for find an address by specified ID.
     *
     * @param id ID of address to find.
     * @return A {@code Address} found.
     */
    public Address findByID(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        return address.orElseThrow(
                () -> new ObjectNotFoundException("Object not found! ID:" + id + ", Type: " + Address.class.getName()));
    }

    /**
     * Responsible for find all addresses.
     *
     * @return A {@code List<Address>}.
     */
    public List<Address> findAll() {
        List<Address> addressList = addressRepository.findAll();
        addressList.sort(Comparator.comparing(Address::getId, Comparator.naturalOrder()));
        return addressList;
    }

    /**
     * Update an address by specified ID.
     *
     * @param id  ID of address to be update.
     * @param obj An address provided by request with fields updated.
     * @return An {@code Address} updated.
     */
    public Address update(Long id, Address obj) {
        Address address = findByID(id);
        BeanUtils.copyProperties(obj, address);
        address.setId(id);
        return addressRepository.save(address);
    }

    /**
     * Create an address with request payload, if field "latitude" or "longitude" be empty, <br>
     * the values will be fetched from Google GeoLocation API.
     *
     * @param address A payload that represents an address to be save.
     * @return An {@code Address} created.
     */
    @SneakyThrows
    public Address create(Address address) {
        address.setId(null);

        if (Objects.isNull(address.getLatitude()) || Objects.isNull(address.getLongitude())) {
            String parameters = "" + address.getStreetName() + "," + address.getNumber() + ","
                    + address.getNeighbourhood() + "," + address.getCity() + "," + address.getState();

            parameters = URLEncoder.encode(parameters, StandardCharsets.UTF_8.toString());

            String url = URL_API_GEOLOCATION + parameters + "&key=" + KEY_API_GEOLOCATION;

            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            InputStream resultRequest = httpClient.execute(request).getEntity().getContent();

            BufferedReader reader = new BufferedReader(new InputStreamReader(resultRequest));
            String content = reader.lines().collect(Collectors.joining(""));

            JSONObject jsonContent = new JSONObject(content);
            JSONArray results = jsonContent.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                JSONObject location = results.getJSONObject(i).getJSONObject("geometry").getJSONObject("location");
                Double longitude = location.getDouble("lng");
                Double latitude = location.getDouble("lat");

                address.setLongitude(longitude);
                address.setLatitude(latitude);
            }
        }

        return addressRepository.save(address);
    }

    /**
     * Delete an address by specified ID.
     *
     * @param id ID of address to be removed.
     */
    public void delete(Long id) {
        Address address = findByID(id);
        addressRepository.delete(address);
    }

    /**
     * Export a list of Address to '.XLSX' file.
     *
     * @return A '.XLSX' file with list of Address.
     */
    @SneakyThrows
    public InputStreamResource exportDataToXLSX() {
        String name = "Addresses";
        String[] columns = new String[]{"Street", "Number", "Complement", "Neighbourhood", "City", "State", "ZipCode"};
        String[] fields = new String[]{"streetName", "number", "complement", "neighbourhood", "city", "state", "zipcode"};

        List<Address> addresses = findAll();
        return ExportUtilities.generateXLSX(name, columns, fields, addresses);
    }
}
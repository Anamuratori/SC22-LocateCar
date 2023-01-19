package org.locadora.model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.locadora.model.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Agency<T extends Vehicle> {
    private Integer id;

    public Integer getId() {
        return id;
    }

    private String name;
    private Address address;
    private List<T> vehicles;

    public Agency() {
        this.vehicles = new ArrayList<>();
    }

    public Agency(String name) {
        this.name = name;
    }

    public Agency(String name, Address address) {
        this.id = (int) (Math.random() * 200) + 1;
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void addVehicle(T vehicle) {
        this.vehicles.add(vehicle);
    }

    public List<T> getVehicles() {
        return this.vehicles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agency agency = (Agency) o;
        return Objects.equals(name, agency.name) && Objects.equals(address, agency.address);
    }

    public JSONObject toJSONObject() {
        JSONObject costumerObject = new JSONObject();
        JSONObject addressObject = new JSONObject();

        costumerObject.put("id", this.getId());
        costumerObject.put("name", this.getName());

        costumerObject.put("address", addressObject);
        addressObject.put("zipcode", address.getZipcode());
        addressObject.put("street", address.getStreet());
        addressObject.put("number", address.getNumber());
        addressObject.put("city", address.getCity());
        addressObject.put("state", address.getState());

        costumerObject.put("vehicles", vehicles);



        return costumerObject;

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }

    @Override
    public String toString() {
        return "Agency{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }


}


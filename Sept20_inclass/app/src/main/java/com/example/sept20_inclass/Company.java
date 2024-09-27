package com.example.sept20_inclass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Company {
    private List<Store> stores = new ArrayList<>();
    private List<Vehicle> vehicles = new ArrayList<>();

    public void addStore(Store store) {
        stores.add(store);
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public List<Store> getStores() {
        return stores;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}




interface RentStatus {
    boolean isRented();
    boolean canRent();

}


class RentedRentStatus implements RentStatus{
    private Rental rental;

    public RentedRentStatus(Rental rental) {
        this.rental = rental;
    }

    @Override
    public boolean isRented() {
        return rental != null;
    }

    @Override
    public boolean canRent(){
        return rental == null;
    }

    public Rental getRental() {
        return rental;
    }


}

class AtStoreRentStatus implements RentStatus{
    private Store store;

    public AtStoreRentStatus(Store store) {
        this.store = store;

    }

    @Override
    public boolean isRented() {
        return false;
    }

    @Override
    public boolean canRent() {
        return true;
    }

    public Store getStore() {
        return store;
    }
}

class Vehicle extends Rentable {
    private Company company;

    public Vehicle(Company company) {
        this.company = company;
        setAtStoreRentStatus(null);
    }
    public Company getCompany(){
        return company;
    }
    public double cargoSpace(){
        return 100.0;
    }

}

class Rentable {
    private RentStatus rentStatus;

    public RentStatus getRentStatus() {
        return rentStatus;
    }

    public void setAtStoreRentStatus(Store store) {
        this.rentStatus = new AtStoreRentStatus(store);
    }

    public void setRentedRentStatus(Rental rental) {
        this.rentStatus = new RentedRentStatus(rental);
    }
}

class Rental {
    private Vehicle vehicle;
    private Store from;
    private Customer customer;

    public Rental (Vehicle vehicle, Store from, Customer customer){
        this.vehicle = vehicle;
        this.from = from;
        this.customer = customer;
    }

    public Store getFrom(){
        return from;

    }
    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

}


class Customer {
    private Optional<Rental> rental = Optional.empty();

    public Optional<Rental> getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental =Optional.ofNullable(rental);
    }
}




class Store {
    private List<Vehicle> vehicles = new ArrayList<>();

    public Rental rentVehicle(Vehicle vehicle, Customer customer) {
        if (vehicles.contains(vehicle)) {
            vehicles.remove(vehicle);
            Rental rental = new Rental(vehicle, this, customer);
            vehicle.setRentedRentStatus(rental);
            customer.setRental(rental);
            return rental;
        } else {
            throw new RuntimeException("Vehicle not available for rent!");
        }
    }

    public void returnVehicle(Rental rental){
        Vehicle vehicle = rental.getVehicle();
        vehicles.add(vehicle);
        vehicle.setAtStoreRentStatus(this);

    }

    public void addVehicle(Vehicle vehicle){
        vehicles.add(vehicle);

    }
    public List<Vehicle> getVehicles() {
        return vehicles;
    }


}


package org.locadora.model;

import org.json.JSONObject;
import org.locadora.model.customer.Customer;
import org.locadora.model.vehicle.Vehicle;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;

public class RentalOperation<T extends Vehicle, C extends Customer> {
    private Integer rentalID;
    private C customer;
    private T vehicle;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal cost;
    private Agency agency;

    private boolean isOver;


    public RentalOperation(C costumer, T vehicle, LocalDate startDate, LocalDate endDate, Agency agency) {
        this(0, costumer, vehicle, startDate, endDate, agency, new BigDecimal(0.9), false);
        this.rentalID = (int) (Math.random() * 200) + 1;
        calculateCost();
    }

    public RentalOperation(Integer rentalID, C costumer, T vehicle, LocalDate startDate, LocalDate endDate, Agency agency, BigDecimal cost, boolean isOver) {
        this.rentalID = rentalID;
        this.customer = costumer;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.agency = agency;
        this.cost = cost;
        this.isOver = isOver;
    }

    public void shortInfo() {
        System.out.println(" CONTRATO " + this.rentalID);
        this.agency.shortInfo();
        System.out.println("VEÍCULO: " + this.vehicle.getVehicleManufacturer() + " " + this.vehicle.getVehicleModel());
        System.out.println(" DATA DA LOCAÇÃO: " + this.startDate);
        System.out.println(" DATA DE ENTREGA: " + this.endDate);
    }

    public void completeInfo() {
        this.shortInfo();
        System.out.println("CUSTO DO ALUGUEL: " + this.cost);
        System.out.println("SITUAÇÂO DO CONTRATO: " + (this.isOver ? "CONCLUÍDO" : "EM ABERTO"));
        System.out.println("CLIENTE");
        this.customer.completeInfo();
        System.out.println("------------");
        System.out.println("VEÍCULO");
        this.vehicle.completeInfo();
    }

    public JSONObject toJSONObject() {
        JSONObject costumerObject = new JSONObject();

        costumerObject.put("rentalID", this.rentalID);
        costumerObject.put("startDate", this.startDate);
        costumerObject.put("endDate", this.endDate);
        costumerObject.put("cost", this.cost);
        costumerObject.put("isOver", this.isOver);

        if (customer != null) {
            costumerObject.put("customer", customer.toJSONObject());
        }

        if (vehicle != null) {
            costumerObject.put("vehicle", vehicle.toJSONObject());
        }

        if (agency != null) {
            costumerObject.put("agency", agency.getId());
        }

        return costumerObject;
    }

    private void calculateCost() {
        long days = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays();
//        BigDecimal rentalFee = vehicle.getRentalFee();
        BigDecimal rentalFee = new BigDecimal("45");

        if (days > 5) {
            // 10% de desconto para locações acima de 5 dias
            rentalFee = rentalFee.multiply(new BigDecimal("0.9"));
        }

        cost = rentalFee.multiply(new BigDecimal(days));
    }

    public Customer getCustomer() {
        return customer;
    }

    public T getVehicle() {
        return vehicle;
    }

    public Integer getRentalID() {
        return rentalID;
    }

    public boolean isOver() {
        return isOver;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public Agency getAgency() {
        return agency;
    }

    public void updateEndDate(LocalDate newEndDate) {
        this.endDate = newEndDate;
        calculateCost();
    }

    public void returnVehicle(Agency agency) {
        if (!isOver) {

            LocalDate returnDate = LocalDate.now();
            if (returnDate.isAfter(endDate)) {
                long lateDays = Duration.between(endDate.atStartOfDay(), returnDate.atStartOfDay()).toDays();
                BigDecimal lateFee = new BigDecimal("5").multiply(new BigDecimal(lateDays));
                cost = cost.add(lateFee);
            }

            this.isOver = true;
            vehicle.setAvaible(true);
        }

        System.out.println("Esta locação ja se encontra concluida");
    }

    @Override
    public String toString() {
        return "--------------------------\n" +
                "\n contrato: " + rentalID +
                "\n Cliente: " + customer.getName() +
//                "\n  Cpf: " + costumer.completeInfo() +
                "\n Veículo: " + vehicle +
                "\n Locação: " + startDate +
                "\n Devolução: " + endDate +
                "\n Custo: " + cost +
                "\n Agência: " + agency;

    }


}

package models;

import java.time.LocalDate;

/**
 *
 * @author Petra Schröder
 */
public class Shipment {

    private int order_id;
    private int shipment_id;
    private LocalDate shipment_date;
    private int tariff_code;
    private double weight;

    public Shipment(int order_id, int shipment_id, LocalDate shipment_date, int tariff_code, double weight) {
        this.order_id = order_id;
        this.shipment_id = shipment_id;
        this.shipment_date = shipment_date;
        this.tariff_code = tariff_code;
        this.weight = weight;
    }

    public int getOrderId() {
        return order_id;
    }

    public int getShipmentId() {
        return shipment_id;
    }

    public LocalDate getShipment_date() {
        return shipment_date;
    }

    public void setShipment_date(LocalDate shipmentDate) {
        this.shipment_date = shipmentDate;
    }

    public void setTariffCode(int tariffCode) {
        this.tariff_code = tariffCode;
    }

    public int getTariffCode() {
        return tariff_code;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

}

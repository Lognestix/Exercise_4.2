package ru.netology.domain;

import java.util.Objects;

public class TicketInformation implements Comparable<TicketInformation> {
  private int id;                   //идентификатор
  private int price;                //стоимость
  private String departureAirport;  //аэропорта вылета
  private String arrivalAirport;    //аэропорт прилёта
  private int travelTime;           //время в пути, в минутах

  public TicketInformation() {
  }

  public TicketInformation(int id, int price, String departureAirport, String arrivalAirport, int travelTime) {
    this.id = id;
    this.price = price;
    this.departureAirport = departureAirport;
    this.arrivalAirport = arrivalAirport;
    this.travelTime = travelTime;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getDepartureAirport() {
    return departureAirport;
  }

  public void setDepartureAirport(String departureAirport) {
    this.departureAirport = departureAirport;
  }

  public String getArrivalAirport() {
    return arrivalAirport;
  }

  public void setArrivalAirport(String arrivalAirport) {
    this.arrivalAirport = arrivalAirport;
  }

  public int getTravelTime() {
    return travelTime;
  }

  public void setTravelTime(int travelTime) {
    this.travelTime = travelTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TicketInformation that = (TicketInformation) o;
    return id == that.id && price == that.price &&
            travelTime == that.travelTime &&
            Objects.equals(departureAirport, that.departureAirport) &&
            Objects.equals(arrivalAirport, that.arrivalAirport);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, price, departureAirport, arrivalAirport, travelTime);
  }

  @Override
  public String toString() {
    return "TicketInformation{" +
            "id=" + id +
            ", price=" + price +
            ", departureAirport='" + departureAirport + '\'' +
            ", arrivalAirport='" + arrivalAirport + '\'' +
            ", travelTime=" + travelTime +
            '}';
  }

  @Override
  public int compareTo(TicketInformation o) {
    TicketInformation p = (TicketInformation) o;
    return this.price - p.price;
  }
}
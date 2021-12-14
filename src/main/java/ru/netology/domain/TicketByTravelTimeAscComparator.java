package ru.netology.domain;

import java.util.Comparator;

public class TicketByTravelTimeAscComparator implements Comparator<TicketInformation> {
  public int compare(TicketInformation t1, TicketInformation t2) {
    return t1.getTravelTime() - t2.getTravelTime();
  }
}
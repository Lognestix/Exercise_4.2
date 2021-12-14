package ru.netology.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TicketByTravelTimeAscComparatorTest {
    //Общие данные:
    private final TicketByTravelTimeAscComparator comparator = new TicketByTravelTimeAscComparator();

    private final TicketInformation zero = new TicketInformation(0, 25_000, "ALA", "DME", 280);
    private final TicketInformation first = new TicketInformation(1, 5_000, "ALA", "CIT", 60);
    private final TicketInformation second = new TicketInformation(2, 5_500, "ALA", "CIT", 85);
    private final TicketInformation third = new TicketInformation(3, 1_800, "DME", "LED", 110);
    private final TicketInformation fourth = new TicketInformation(4, 2_500, "DME", "LED", 90);
    private final TicketInformation fifth = new TicketInformation(5, 2_600, "DME", "LED", 100);
    private final TicketInformation sixth = new TicketInformation(6, 3_100, "CIT", "ALA", 70);
    private final TicketInformation seventh = new TicketInformation(7, 35_000, "CIT", "LRH", 2_205);

    //Unit-тест логики класса TicketByTravelTimeAscComparator
    @Test
    public void shouldSortByTime() {
        TicketInformation[] expected = new TicketInformation[] {first, sixth, second, fourth, fifth, third, zero, seventh};
        TicketInformation[] actual = new TicketInformation[] {zero, first, second, third, fourth, fifth, sixth, seventh};
        Arrays.sort(actual, comparator);
        assertArrayEquals(expected, actual);
    }
}
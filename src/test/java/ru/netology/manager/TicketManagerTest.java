package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.TicketInformation;
import ru.netology.repository.TicketRepository;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {
    //Общие данные:
    private final TicketRepository repository = new TicketRepository();
    private final TicketManager ticketManager = new TicketManager(repository);

    private final TicketInformation zero = new TicketInformation(0, 25_000, "ALA", "DME", 280);
    private final TicketInformation first = new TicketInformation(2, 5_500, "ALA", "CIT", 85);
    private final TicketInformation second = new TicketInformation(1, 5_000, "ALA", "CIT", 60);
    private final TicketInformation third = new TicketInformation(3, 1_800, "DME", "LED", 110);
    private final TicketInformation fourth = new TicketInformation(4, 2_500, "DME", "LED", 90);
    private final TicketInformation fifth = new TicketInformation(5, 2_600, "DME", "LED", 100);
    private final TicketInformation sixth = new TicketInformation(6, 3_100, "CIT", "ALA", 70);
    private final TicketInformation seventh = new TicketInformation(7, 35_000, "CIT", "LRH", 2_205);

    @BeforeEach
    public void setUp() {
        ticketManager.addTicketInformation(zero);
        ticketManager.addTicketInformation(first);
        ticketManager.addTicketInformation(second);
        ticketManager.addTicketInformation(third);
        ticketManager.addTicketInformation(fourth);
        ticketManager.addTicketInformation(fifth);
        ticketManager.addTicketInformation(sixth);
        ticketManager.addTicketInformation(seventh);
    }

    @Test   //Тест на удаление по id
    public void shouldRemoveById() {
        ticketManager.removeById(3);
        ticketManager.removeById(5);
        TicketInformation[] expected = new TicketInformation[] {fourth};
        TicketInformation[] actual = ticketManager.findAll("DME","LED");
    }

    @Test   //Тест на удаление по id - исключение
    public void shouldRemoveByIdException() {
        ticketManager.removeById(6);
        assertThrows(NotFoundException.class, () -> {
            repository.removeById(6);
        });
    }

    @Test   //Тест на правильный поиск по полям вылета и прилёта
    public void shouldCorrectSearch() {
        TicketInformation[] expected = new TicketInformation[] {second, first};
        TicketInformation[] actual = ticketManager.findAll("ALA","CIT");
        assertArrayEquals(expected, actual);
    }

    @Test   //Тест на отсутсвие значения при поиске
    public void shouldMissingValue() {
        TicketInformation[] expected = new TicketInformation[0];
        TicketInformation[] actual = ticketManager.findAll("ALA", "LRH");
        assertArrayEquals(expected, actual);
    }
}
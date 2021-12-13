package ru.netology.manager;

import ru.netology.domain.TicketInformation;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;


public class TicketManager {
  //Добавление необходимыех полей, конструкторов и методов
  private TicketRepository repository;

  public TicketManager(TicketRepository repository) { this.repository = repository; }

  //Добавление информации о билете в репозиторий
  public void addTicketInformation(TicketInformation item) { repository.save(item); }

  //Удаление информации о билете из репозитория по id
  public void removeById(int id) { repository.removeById(id); }

  //Отображение всех билетов, соотвествующих запросу по аэропорту вылета и аэропорту прилёта
  public TicketInformation[] findAll(String departureAirport, String arrivalAirport) {
    TicketInformation[] tickets = repository.findAll();
    TicketInformation[] result = new TicketInformation[0];
    for (TicketInformation ticket : tickets) {
      if (matches(ticket, departureAirport, arrivalAirport)) {
        int length = result.length + 1;
        TicketInformation[] tmp = new TicketInformation[length];
        System.arraycopy(result, 0, tmp, 0, result.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = ticket;
        result = tmp;
      }
      Arrays.sort(result);  //Сортировка билетов по цене от меньшей к большей
    }
    return result;
  }

  //Поиск совпадений по аэропорту вылета и аэропорту прилёта
  public boolean matches(TicketInformation ticketInformation, String departureAirport, String arrivalAirport) {
    //Проверка на наличие поисковых запросов в данных о аэропорте вылета и аэропорте прилёта
    if (ticketInformation.getDepartureAirport().contains(departureAirport) && ticketInformation.getArrivalAirport().contains(arrivalAirport)) {
      return true;
    }
    return false;
  }
}
# Настройки добавленные в pom.xml для данного проекта
```xml
    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.22.2</version>
                <configuration>
                    <failIfNoTests>true</failIfNoTests>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.jacoco</groupId>
                <artifactId>jacoco-maven-plugin</artifactId>
                <version>0.8.5</version>
                <executions>
                    <execution>
                        <id>agent-Smith</id>
                        <phase>initialize</phase>
                        <goals>
                            <goal>prepare-agent</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>report-agent-Smith</id>
                        <phase>verify</phase>
                        <goals>
                            <goal>report</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.8.1</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.22</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-junit-jupiter</artifactId>
            <version>3.6.28</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
```
# Код Java находящийся в этом репозитории
```Java
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
```
```Java
package ru.netology.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TicketInformationTest {
    //Общие данные:
    private final TicketInformation zero = new TicketInformation(0, 25_000, "ALA", "DME", 280);
    private final TicketInformation first = new TicketInformation(1, 5_000, "ALA", "CIT", 60);
    private final TicketInformation second = new TicketInformation(2, 5_500, "ALA", "CIT", 85);
    private final TicketInformation third = new TicketInformation(3, 1_800, "DME", "LED", 110);
    private final TicketInformation fourth = new TicketInformation(4, 2_500, "DME", "LED", 90);
    private final TicketInformation fifth = new TicketInformation(5, 2_600, "DME", "LED", 100);
    private final TicketInformation sixth = new TicketInformation(6, 3_100, "CIT", "ALA", 70);
    private final TicketInformation seventh = new TicketInformation(7, 35_000, "CIT", "LRH", 2_205);

    //Unit-тест логики класса TicketInformation
    @Test
    public void shouldSortByPrice() {
        TicketInformation[] expected = new TicketInformation[] {third, fourth, fifth, sixth, first, second, zero, seventh};
        TicketInformation[] actual = new TicketInformation[] {zero, first, second, third, fourth, fifth, sixth, seventh};
        Arrays.sort(actual);
        assertArrayEquals(expected, actual);
    }
}
```
```Java
package ru.netology.domain;

import java.util.Comparator;

public class TicketByTravelTimeAscComparator implements Comparator<TicketInformation> {
  public int compare(TicketInformation t1, TicketInformation t2) {
    return t1.getTravelTime() - t2.getTravelTime();
  }
}
```
```Java
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
```
```Java
package ru.netology.manager;

import ru.netology.domain.TicketInformation;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;
import java.util.Comparator;

public class TicketManager {
  //Добавление необходимыех полей, конструкторов и методов
  private TicketRepository repository;

  public TicketManager(TicketRepository repository) { this.repository = repository; }

  //Добавление информации о билете в репозиторий
  public void addTicketInformation(TicketInformation item) { repository.save(item); }

  //Удаление информации о билете из репозитория по id
  public void removeById(int id) { repository.removeById(id); }

  //Отображение всех билетов, соотвествующих запросу по аэропорту вылета и аэропорту прилёта
  public TicketInformation[] findAll(String departureAirport, String arrivalAirport, Comparator<TicketInformation> comparator) {
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
      Arrays.sort(result, comparator);  //Сортировка билетов
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
```
```Java
package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.TicketByTravelTimeAscComparator;
import ru.netology.domain.TicketInformation;
import ru.netology.repository.TicketRepository;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {
    //Общие данные:
    private final TicketRepository repository = new TicketRepository();
    private final TicketManager ticketManager = new TicketManager(repository);
    private final TicketByTravelTimeAscComparator comparator = new TicketByTravelTimeAscComparator();

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
        TicketInformation[] actual = ticketManager.findAll("DME","LED", TicketInformation::compareTo);
        assertArrayEquals(expected, actual);
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
        TicketInformation[] actual = ticketManager.findAll("ALA","CIT", TicketInformation::compareTo);
        assertArrayEquals(expected, actual);
    }

    @Test   //Тест на отсутсвие значения при поиске
    public void shouldMissingValue() {
        TicketInformation[] expected = new TicketInformation[0];
        TicketInformation[] actual = ticketManager.findAll("ALA", "LRH", TicketInformation::compareTo);
        assertArrayEquals(expected, actual);
    }

    @Test   //Тест сортировки результатов поиска по компаратору(время проведенное в пути)
    public void shouldSortingSearchResultsByComparator() {
        TicketInformation[] expected = new TicketInformation[] {fourth, fifth, third};
        TicketInformation[] actual = ticketManager.findAll("DME","LED", comparator);
        assertArrayEquals(expected, actual);
    }
}
```
```Java
package ru.netology.repository;

import ru.netology.domain.NotFoundException;
import ru.netology.domain.TicketInformation;

public class TicketRepository {
  private TicketInformation[] items = new TicketInformation[0];

  public void save(TicketInformation item) {
    int length = items.length + 1;
    TicketInformation[] tmp = new TicketInformation[length];
    System.arraycopy(items, 0, tmp, 0, items.length);
    int lastIndex = tmp.length - 1;
    tmp[lastIndex] = item;
    items = tmp;
  }

  public TicketInformation[] findAll() {
    return items;
  }

  public TicketInformation findById(int id) {
    for (TicketInformation item : items) {
      if (item.getId() == id) {
        return item;
      }
    }
    return null;
  }

  public void removeById(int id) {
    if (findById(id) == null) {
      throw new NotFoundException(
              "Element with id: " + id + " not found"
      );
    }
    int length = items.length - 1;
    TicketInformation[] tmp = new TicketInformation[length];
    int index = 0;
    for (TicketInformation item : items) {
      if (item.getId() != id) {
        tmp[index] = item;
        index++;
      }
    }
    items = tmp;
  }
}
```
```Java
package ru.netology.domain;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg) {
        super(msg);
    }
}
```
package models;

import jakarta.persistence.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "client")
@Getter
@Setter
@ToString
public class Client {
    @Id
    private int id;
    private String name;
    private String SSN;
    private String Department;
    private String city;
    private String website;
    private boolean clientState;

}

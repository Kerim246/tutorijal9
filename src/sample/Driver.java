package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Driver {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty surname= new SimpleStringProperty("");;
    private SimpleStringProperty jmb= new SimpleStringProperty("");;
    private SimpleObjectProperty<LocalDate> birthday= new SimpleObjectProperty<>();
    private SimpleObjectProperty<LocalDate> hire_date= new SimpleObjectProperty<>();

    public Driver() {
    }

    public Driver(String name, String surname, String jmb, LocalDate birthday, LocalDate hire_date) {
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.jmb = new SimpleStringProperty(jmb);
        this.birthday = new SimpleObjectProperty<>(birthday);
        this.hire_date = new SimpleObjectProperty<>(birthday);
    }

    public void getId(int id) {

    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getJmb() {
        return jmb.get();
    }

    public SimpleStringProperty jmbProperty() {
        return jmb;
    }

    public void setJmb(String jmb) {
        this.jmb.set(jmb);
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public SimpleObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public LocalDate getHire_date() {
        return hire_date.get();
    }

    public SimpleObjectProperty<LocalDate> hire_dateProperty() {
        return hire_date;
    }

    public void setHire_date(LocalDate hire_date) {
        this.hire_date.set(hire_date);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "name=" + name +
                ", surname=" + surname +
                '}';
    }
}



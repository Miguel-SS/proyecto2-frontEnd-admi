package cr.una.frontend.model;

import java.util.ArrayList;
import java.util.List;

public class Patient {

    // Attributes
    private String name;
    private String lastName;
    private int birthYear;
    private int id;
    private String password;
    private String phone;
    private String address;

    private String disease;
    private String observation;

    /**
     *
     */
    public Patient() { }

    /**
     * @param id
     * @param name
     * @param lastName
     * @param birthYear
     * @param password
     * @param phone
     * @param address
     * @param disease
     * @param observation
     */
    public Patient(int id, String name, String lastName, int birthYear, String phone, String address,
                        String disease, String observation, String password) {
        this.name = name;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.id = id;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.disease = disease;
        this.observation = observation;
    }

    /**
     * @param id
     * @param name
     * @param lastName
     * @param birthYear
     * @param phone
     * @param address
     * @param password
     */
    public Patient(int id, String name, String lastName, int birthYear, String phone, String address, String password) {
        this.name = name;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.id = id;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.disease = "INDEF";
        this.observation = "INDEF";
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthYear=" + birthYear +
                ", id=" + id +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", disease='" + disease + '\'' +
                ", observation='" + observation + '\'' +
                '}';
    }
}

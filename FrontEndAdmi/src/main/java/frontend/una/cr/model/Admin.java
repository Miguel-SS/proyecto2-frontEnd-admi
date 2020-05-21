package frontend.una.cr.model;

public class Admin {

    // Attributes
    private String name;
    private String lastName;
    private int id;
    private String password;
    private String phone;

    /**
     *
     */
    public Admin() {}

    /**
     * @param id
     * @param name
     * @param lastName
     * @param phone
     * @param password
     */
    public Admin(int id, String name, String lastName, String phone, String password) {
        this.name = name;
        this.lastName = lastName;
        this.id = id;
        this.password = password;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    @Override
    public String toString() {
        return "Admin{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone +
                '}';
    }
}

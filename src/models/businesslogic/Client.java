package models.businesslogic;

import static models.businesslogic.ClientPasswordService.hashPassword;

import java.io.Serializable;


public class Client implements Serializable {
    private String firstName;
    private String lastName;
    private String password;

    public Client(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = hashPassword(password);
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Client{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", password='" + password + '\'' +
            '}';
    }

}

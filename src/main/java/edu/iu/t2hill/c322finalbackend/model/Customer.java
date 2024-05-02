package edu.iu.t2hill.c322finalbackend.model;

//@Entity
//@Table(schema = "ducks")
public final class Customer {
//    @Id
    private String password;
    private String email;

    public Customer() {}

    public Customer(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

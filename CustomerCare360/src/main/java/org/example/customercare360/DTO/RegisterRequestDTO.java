package org.example.customercare360.DTO;


import org.example.customercare360.Enums.CustomerType;
import org.example.customercare360.Enums.Role;
import org.example.customercare360.Entity.User;
import org.example.customercare360.Entity.Customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {

    private String name;
    private String email;
    private String phone;
    private String username;
    private String password;
    private Role role;
    private CustomerType customerType;

    public RegisterRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    public void setUserName(String userName) {
//        this.userName = userName;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole(){return role;}

    public void setRole(Role role){this.role = role;}

    public CustomerType getCustomerType(){return customerType;}

    public void setCustomerType(CustomerType type){this.customerType = type;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

}
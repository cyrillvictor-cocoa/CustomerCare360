package org.example.customercare360.Entity;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.naming.Name;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private Integer userId;

    private String name;

    private String email;

    private String phone;

    @Column(name ="UserName")
    private String userName;

    private String password; // BCrypt encrypted password
    @Column(name="CreatedBy")
    private Integer createdBy;

    @Column(name="ModifiedBy")
    private Integer modifiedBy;

    public void setUserName(String userName){this.userName = userName;}

    public void setEmail(String email){this.email = email;}

    public void setPhone(String phone){this.phone = phone;}

    public void setName(String name){this.name = name;}

    public void setPassword(String password){this.password = password;}

    public void setCreatedBy(Integer createdBy){this.createdBy = createdBy;}

    public void setModifiedBy(Integer modifiedBy){this.modifiedBy = modifiedBy;}

    public String getName(){return name;}

    public String getEmail(){return email;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getPassword(){return password;}

    @Override
    public String getUsername() {
        //return "";
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public String getUserName(){return userName;}

    public String getPhone(){return phone;}

    public Integer getCreatedBy(){return createdBy;}

    public Integer getModifiedBy(){return modifiedBy;}

}
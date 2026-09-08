package org.example.customercare360.Entity;
import jakarta.persistence.*;
import org.example.customercare360.Enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "CreatedBy")
    private User createdBy;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ModifiedBy")
    private User modifiedBy;

    public void setUserName(String userName){this.userName = userName;}

    public void setEmail(String email){this.email = email;}

    public void setPhone(String phone){this.phone = phone;}

    public void setName(String name){this.name = name;}

    public void setPassword(String password){this.password = password;}

    public void setRole(Role role){this.role = role;}

    public void setCreatedBy(User createdBy){this.createdBy = createdBy;}

    public void setModifiedBy(User modifiedBy){this.modifiedBy = modifiedBy;}

    public Integer getUserId(){return userId;}

    public String getName(){return name;}

    public String getEmail(){return email;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+role));
    }

    public String getPassword(){return password;}

    @Override
    public String getUsername() {
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

    public Role getRole(){return role;}

    public User getCreatedBy(){return createdBy;}

    public User getModifiedBy(){return modifiedBy;}

}
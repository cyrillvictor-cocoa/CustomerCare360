package org.example.customercare360.DTO;

import org.example.customercare360.Enums.Role;

public class UpdateUserDTO {

    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
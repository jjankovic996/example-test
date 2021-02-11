package org.example.clean.architecture;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleBM {
    private RoleTypeBM name;

    public RoleBM(RoleTypeBM name) {
        this.name = name;
    }
}

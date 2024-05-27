package com.restaurant.system.backend_restaurant_system.persistence.util;

import java.util.Arrays;
import java.util.List;

public enum Role {

    ROLE_ADMINISTRATOR(Arrays.asList(
        RolePermission.READ_ALL_DISH,
        RolePermission.READ_ONE_DISH,
        RolePermission.CREATE_ONE_DISH,
        RolePermission.UPDATE_ONE_DISH,
        RolePermission.DISABLE_ONE_DISH,
        RolePermission.READ_ALL_CATEGORIES,
        RolePermission.READ_ONE_CATEGORY,
        RolePermission.CREATE_ONE_CATEGORY,
        RolePermission.UPDATE_ONE_CATEGORY,
        RolePermission.DISABLE_ONE_CATEGORY,
        RolePermission.READ_MY_PROFILE
    )),

    ROLE_WAITER(Arrays.asList(
        RolePermission.READ_ALL_DISH,
        RolePermission.READ_ONE_DISH,
        RolePermission.READ_ALL_CATEGORIES,
        RolePermission.READ_ONE_CATEGORY,
        RolePermission.UPDATE_ONE_CATEGORY,
        RolePermission.READ_MY_PROFILE
    ));

    private List<RolePermission> permission;

    private Role(List<RolePermission> permission) {
        this.permission = permission;
    }

    public List<RolePermission> getPermission() {
        return permission;
    }

    public void setPermission(List<RolePermission> permission) {
        this.permission = permission;
    }
}

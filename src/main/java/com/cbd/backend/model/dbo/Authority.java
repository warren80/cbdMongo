package com.cbd.backend.model.dbo;

import com.cbd.backend.model.AuthorityPermission;
import com.cbd.backend.model.Model;

public class Authority extends Model {
    private boolean authorized;
    private AuthorityPermission permission;
    public Authority() {}

    public Authority(AuthorityPermission permission, boolean authorized ) {
        this.authorized = authorized;
        this.permission = permission;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public AuthorityPermission getPermission() {
        return permission;
    }

    public void setPermission(AuthorityPermission permission) {
        this.permission = permission;
    }

}

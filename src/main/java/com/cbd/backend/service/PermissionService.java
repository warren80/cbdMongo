package com.cbd.backend.service;

import com.cbd.backend.model.AuthorityPermission;

public interface PermissionService {
    boolean isPermissionGranted( String token, AuthorityPermission role );
}

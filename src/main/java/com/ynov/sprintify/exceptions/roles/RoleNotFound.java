package com.ynov.sprintify.exceptions.roles;

public class RoleNotFound extends RuntimeException
{
    public RoleNotFound() {
        super("Role not found");
    }
}

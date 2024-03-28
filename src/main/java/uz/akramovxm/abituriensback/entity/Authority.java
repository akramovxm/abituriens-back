package uz.akramovxm.abituriensback.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    GET_PROBLEM, CREATE_PROBLEM, UPDATE_PROBLEM, DELETE_PROBLEM;

    @Override
    public String getAuthority() {
        return this.name();
    }
}

package uz.akramovxm.abituriensback.entity;

import lombok.Getter;
import uz.akramovxm.abituriensback.entity.Authority;

import java.util.Set;

@Getter
public enum Role {
    ADMIN(Set.of(Authority.values())),
    TEACHER(Set.of()),
    PUPIL(Set.of());

    final Set<Authority> authorities;

    Role(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}

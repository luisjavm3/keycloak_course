package co.luisjavm3.resource_server.controllers;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.luisjavm3.resource_server.response.UserRest;

@RestController
@RequestMapping("/users")
public class UsersController {
    @GetMapping("/status/check")
    public String status() {
        return "Working...";
    }

    // @Secured("ROLE_user")
    // @Secured("ROLE_developer")
    // @PreAuthorize("#id == #jwt.subject")
    // @PreAuthorize("hasAuthority('ROLE_developer')")
    @PreAuthorize("hasAuthority('ROLE_developer') or #id == #jwt.subject")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return "User deleted by the user with ID: " + jwt.getSubject();
    }

    @PostAuthorize("returnObject.userId == #id")
    @GetMapping("/{id}")
    public UserRest getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return new UserRest("luis", "montes", "3fa14916-2bc9-49b1-97a6-c215a5792c1d");
    }
}

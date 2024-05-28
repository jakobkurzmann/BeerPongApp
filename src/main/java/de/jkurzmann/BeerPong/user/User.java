package de.jkurzmann.BeerPong.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record User(

        Integer user_id,
        @NotEmpty
        String username,
        @NotEmpty
        @Email
        String email,
        @NotEmpty
        String firstname,
        @NotEmpty
        String lastname,
        @NotEmpty
        String password,
        String profilePicture) {
}

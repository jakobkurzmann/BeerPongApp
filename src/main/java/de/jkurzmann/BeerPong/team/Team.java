package de.jkurzmann.BeerPong.team;

import de.jkurzmann.BeerPong.user.User;
import jakarta.validation.constraints.NotEmpty;

public record Team
        (
                Integer team_id,
                String team_name,
                @NotEmpty
                User user1,
                @NotEmpty
                User user2
                )
{

}

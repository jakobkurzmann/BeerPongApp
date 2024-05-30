package de.jkurzmann.BeerPong.team;


public record Team
        (
                Integer team_id,
                String team_name,

                Integer user_id1,
                Integer user_id2
                )
{

}

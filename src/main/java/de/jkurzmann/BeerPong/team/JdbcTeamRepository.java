package de.jkurzmann.BeerPong.team;

import de.jkurzmann.BeerPong.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import java.util.List;

@Repository
public class JdbcTeamRepository implements TeamRepository
{
    private static final Logger log = LoggerFactory.getLogger(Team.class);
    private final JdbcClient jdbcClient;

    public JdbcTeamRepository(JdbcClient jdbcClient)
    {
        this.jdbcClient = jdbcClient;
    }


    public List<Team> findAll() {
        return jdbcClient.sql("select * FROM team")
                .query(Team.class)
                .list();
    }

    public void create(Team team)
    {
        var created = jdbcClient.sql("INSERT INTO team(team_name, user_id1, user_id2) values(?,?,?)").
                params(List.of(team.team_name(),team.user_id1(),team.user_id2())).update();
        Assert.state(created == 1,"Failed to create team");
    }
    public void update(Team team, Integer id)
    {
        var updated = jdbcClient.sql("UPDATE team set team_name = ?, user_id1" +
                "= ?, user_id2 = ? WHERE team_id = ?").params(List.of(team.team_name(),team.user_id1(),
                team.user_id2(),id)).update();
        Assert.state(updated == 1, "Failed to update Team " + team.team_id());
    }

    public void delete(Integer id)
    {
        var deleted = jdbcClient.sql("DELETE FROM team WHERE team_id = :id").param("id", id).
                update();
        Assert.state(deleted == 1, "Failed to delete Team " + id);
    }
}

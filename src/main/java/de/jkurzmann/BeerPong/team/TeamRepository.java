package de.jkurzmann.BeerPong.team;

import java.util.List;

public interface TeamRepository
{
    List<Team> findAll();
    void create(Team team);
    void update(Team team, Integer id);
    void delete(Integer id);
}

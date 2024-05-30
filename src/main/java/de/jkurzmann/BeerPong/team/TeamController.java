package de.jkurzmann.BeerPong.team;

import de.jkurzmann.BeerPong.user.JdbcUserRepository;
import de.jkurzmann.BeerPong.user.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/team")
public class TeamController
{
    private final JdbcTeamRepository teamRepository;


    public TeamController(JdbcTeamRepository teamRepository)
    {
        this.teamRepository = teamRepository;

    }
    @GetMapping()
    List<Team> findAll()
    {
        return teamRepository.findAll();
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void createTeam(@Valid @RequestBody Team team)
    {
        teamRepository.create(team);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void updateTeam(@Valid @RequestBody Team team, @PathVariable Integer id)
    {
        teamRepository.update(team,id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteTeam(@PathVariable Integer id)
    {
        teamRepository.delete(id);
    }

}

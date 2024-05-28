package de.jkurzmann.BeerPong.user;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController
{
    private final JdbcUserRepository userRepository;

    public UserController(JdbcUserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @GetMapping()
    List<User> findAll()
    {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    User findById(@PathVariable Integer id)
    {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return user.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void createUser(@Valid @RequestBody User user)
    {
        userRepository.create(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void updateUser(@Valid @RequestBody User user, @PathVariable Integer id)
    {
        userRepository.update(user, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Integer id)
    {
        userRepository.delete(id);
    }
}

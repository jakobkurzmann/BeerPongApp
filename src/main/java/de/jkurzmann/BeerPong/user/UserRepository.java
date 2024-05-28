package de.jkurzmann.BeerPong.user;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository
{
    List<User> findAll();
    Optional<User> findById(Integer id);
    void create(User user);
    void update(User user, Integer id);
    void delete(Integer id);
}

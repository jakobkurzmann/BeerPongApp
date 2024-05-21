package de.jkurzmann.BeerPong.user;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository
{
    private List<User> users = new ArrayList<>();

    List<User> findAll()
    {
        return users;
    }

    Optional<User> findById(Integer id)
    {
        return users.stream()
                .filter(users -> users.id() == id)
                .findFirst();
    }

    void createUser(User user)
    {
        users.add(user);
    }

    void updateUser(User user, Integer id)
    {
        Optional<User> existingUser = findById(id);
        if(existingUser.isPresent())
        {
            users.set(users.indexOf(existingUser.get()),user);
        }
    }

    void deleteUser(Integer id)
    {
        users.removeIf(user -> user.id().equals(id));
    }

    @PostConstruct
    private void init()
    {
        users.add(new User(1,"JJK","business.kurzmann@gmail.com", "Jakob", "Kurzmann",
                ""));
        users.add(new User(2,"Incasula", "incasula@gmx.com", "Susanne", "Lalla",""));
    }
}

package de.jkurzmann.BeerPong.user;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private static final Logger log = LoggerFactory.getLogger(UserRepository.class);
    private final JdbcClient jdbcClient;

    public UserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
    //This function retrieves all users from the database
    public List<User> findAll() {
        return jdbcClient.sql("select * from \"User\"")
                .query(User.class)
                .list();
    }
    //This function searches for a user in the database based on their ID.
    public Optional<User> findById(Integer id) {
        return jdbcClient.sql("select * from \"User\" WHERE id = :id")
                .param("id", id)
                .query(User.class)
                .optional();
    }
    //This function creates a new user and adds them to the database.
    public void createUser(User user)
    {
        var updated = jdbcClient.sql("INSERT INTO \"User\"(id, username, firstname, " +
                "lastname, email, profilePicture) values(?,?,?,?,?,?)").params(List.of(user.id(), user.username(),
                user.firstname(), user.lastname(), user.email(), user.profilePicture())).update();

        Assert.state(updated == 1, "Failed to create user" + user.username());
    }
    //This function updates an existing user in the database based on their ID.
    public void updateUser(User user, Integer id)
    {
        var updated = jdbcClient.sql("UPDATE \"User\" set id = ?, username = ?, firstname = ?," +
                                            "lastname = ?, email = ?, profilePicture = ? WHERE id = ?")
                .params(List.of(user.id(), user.username(),
                        user.firstname(), user.lastname(), user.email(), user.profilePicture(),id)).update();

        Assert.state(updated == 1, "Failed to update user " + user.id());
    }
    //This function deletes a user from the database based on their ID.
    public void deleteUser(Integer id)
    {
        var updated = jdbcClient.sql("DELETE FROM \"User\" WHERE id = :id").param("id",id).update();

        Assert.state(updated == 1, "Failed to delete User " + id) ;
    }


}

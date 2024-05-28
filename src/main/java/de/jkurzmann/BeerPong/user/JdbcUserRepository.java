package de.jkurzmann.BeerPong.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcUserRepository implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(JdbcUserRepository.class);
    private final JdbcClient jdbcClient;

    public JdbcUserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
    //This function retrieves all users from the database
    public List<User> findAll() {
        return jdbcClient.sql("select * from user_account")
                .query(User.class)
                .list();
    }
    //This function searches for a user in the database based on their ID.
    public Optional<User> findById(Integer id) {
        return jdbcClient.sql("select * from user_account WHERE user_id = :id")
                .param("id", id)
                .query(User.class)
                .optional();
    }
    //This function creates a new user and adds them to the database.
    public void create(User user)
    {
        var updated = jdbcClient.sql("INSERT INTO user_account(username, firstname, " +
                "lastname, email, password, profilepicture) values(?,?,?,?,?,?)").params(List.of( user.username(),
                user.firstname(), user.lastname(), user.email(), user.password(), user.profilePicture())).update();

        Assert.state(updated == 1, "Failed to create user" + user.username());
    }
    //This function updates an existing user in the database based on their ID.
    public void update(User user, Integer id)
    {
        var updated = jdbcClient.sql("UPDATE user_account set username = ?, firstname = ?," +
                                            "lastname = ?, email = ?, password = ?,profilePicture = ? WHERE user_id = ?")
                .params(List.of(user.username(),
                        user.firstname(), user.lastname(), user.email(), user.password(), user.profilePicture(),id)).update();

        Assert.state(updated == 1, "Failed to update user " + user.user_id());
    }
    //This function deletes a user from the database based on their ID.
    public void delete(Integer id)
    {
        var updated = jdbcClient.sql("DELETE FROM user_account WHERE user_id = :id").param("id",id).update();

        Assert.state(updated == 1, "Failed to delete User " + id) ;
    }


}

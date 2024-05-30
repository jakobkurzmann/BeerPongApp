CREATE TABLE IF NOT EXISTS user_account(
    user_id SERIAL PRIMARY KEY,
    username varchar(50) NOT NULL UNIQUE,
    firstname varchar(50) NOT NULL,
    lastname varchar(50) NOT NULL,
    email varchar(100) NOT NULL UNIQUE,
    password varchar(200) NOT NULL,
    profilePicture varchar(250)
);

CREATE TABLE IF NOT EXISTS team
(
    team_id SERIAL PRIMARY KEY,
    team_name varchar(50),
    user1_id INT NOT NULL ,
    user2_id INT NOT NULL,
    FOREIGN KEY (user1_id) REFERENCES user_account(user_id),
    FOREIGN KEY (user2_id) REFERENCES user_account(user_id)
);

CREATE TABLE IF NOT EXISTS match
(
    match_id SERIAL PRIMARY KEY,
    date DATE,
    winning_team_id INT NOT NULL,
    loosing_team_id INT NOT NULL,
    remaining_cups_team_1 INT,
    remaining_cups_team_2 INT,
    team1_id INT NOT NULL,
    team2_id INT NOT NULL,
    confirmed BOOLEAN,
    FOREIGN KEY (winning_team_id) REFERENCES team(team_id),
    FOREIGN KEY (loosing_team_id) REFERENCES team(team_id),
    FOREIGN KEY (team1_id) REFERENCES team(team_id),
    FOREIGN KEY (team2_id) REFERENCES team(team_id)
);
CREATE TABLE IF NOT EXISTS tournament
(
    tournament_id SERIAL PRIMARY KEY,
    tournament_name varchar(50) NOT NULL,
    date DATE,
    modus varchar(50),
    winner_team_id INT,
    host_id INT NOT NULL,
    FOREIGN KEY (winner_team_id) REFERENCES team(team_id),
    FOREIGN KEY (host_id) REFERENCES user_account(user_id)
);
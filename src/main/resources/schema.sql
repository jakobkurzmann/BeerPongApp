CREATE TABLE IF NOT EXISTS "User" (
    id INT NOT NULL,
    username varchar(250) NOT NULL,
    firstname varchar(250) NOT NULL,
    lastname varchar(250) NOT NULL,
    email varchar(250) NOT NULL,
    profilePicture varchar(250),
    PRIMARY KEY (id)
);
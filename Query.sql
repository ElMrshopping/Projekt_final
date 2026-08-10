CREATE TABLE IF NOT EXISTS Client(
                    pin VARCHAR(255) PRIMARY KEY NOT NULL,
                    nom varchar(255) NOT NULL,
                    prenom varchar(255) NOT NULL,
                    email varchar(255) NOT NULL ,
                    telephone char(16) NOT NULL ,
                    date_naissance TEXT NOT NULL );
CREATE DATABASE "versioning";

CREATE USER "foobar"@"localhost" IDENTIFIED BY "geheim2345!";

USE versioning;

CREATE TABLE VERSION (id long sequence, description varchar(200), cdate DATETIME, mdate DATETIME);


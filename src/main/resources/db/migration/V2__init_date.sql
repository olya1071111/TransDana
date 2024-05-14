insert into user(name, job) value ("Ivan Sidoroy", "admin");
insert into user(name, job) value ("Sergey Kot","driver");
insert into user(name, job) value ("Anton Upit","driver");
insert into user(name, job) value ("Poman Sotov","driver");
insert into user(name, job) value ("Petr Vifov","driver");
insert into user(name, job) value ("Roman Kim","driver");
insert into user(name, job) value ("Andrey Som","driver");
insert into user(name, job) value ("David Trofimov","operator");

insert into car(number, tonnage, status) value ("MAZ 5674-3TY", 10, "repair");
insert into car(number, tonnage, status) value ("MAZ 8754-3NT", 10, "working");
insert into car(number, tonnage, status) value ("MAZ 1254-3OU", 5, "working");
insert into car(number, tonnage, status) value ("MAZ 6354-3HB", 20, "working");
insert into car(number, tonnage, status) value ("MAZ 2554-3HB", 20, "working");
insert into car(number, tonnage, status) value ("MAZ 9574-3HB", 10, "working");
insert into car(number, tonnage, status) value ("MAZ 3111-3HB", 20, "working");

insert into city(name) value ("Minsk");
insert into city(name) value ("Gomel");
insert into city(name) value ("Mogiley");
insert into city(name) value ("Vitebsk");
insert into city(name) value ("Brest");
insert into city(name) value ("Grodno");

insert into route value (1, 342, "2024-05-21 10:00:00", "2024-05-21 12:00:00", 1,"ul.Agareva 17", 2, "ul.Bartashova 18", 4, 3, "OPEN");
insert into route value (2, 546, "2024-05-22 17:00:00", "2024-05-22 20:00:00", 3, "ul.Tanka 75",4, "ul.Titova 12", 2, 2, "OPEN");
insert into route value (3, 658, "2024-05-20 13:20:00", "2024-05-20 15:00:00", 5, "ul.Rutova 89",  6, "ul.Lazurnaya 19", 3, 3, "OPEN");
insert into route value (4, 869, "2024-05-21 08:36:00", "2024-05-21 10:11:00", 3, "ul.Chapaeva 34",5,"ul.Lermontova 15", 2, 2, "OPEN");
insert into route value (5, 187, "2024-05-20 11:50:00", "2024-05-20 14:50:00", 1, "ul.Pervomaiskaya 98",6,"ul.Batalova 92", 7, 4, "OPEN");
insert into route value (6, 746, "2024-05-22 14:10:00", "2024-05-22 15:55:00", 2, "ul.Nutova 41",4,"ul.Polevaya 85", 6, 5, "OPEN");
insert into route value (7, 546, "2024-05-21 17:00:00", "2024-05-21 20:00:00", 3, "ul.Semina",4,"ul.Kovaleva 70", 2, 2, "OPEN");
insert into route value (8, 746, "2024-05-23 14:10:00", "2024-05-23 15:55:00", 2, "ul.Firsova 13",4, "ul.Marshalova 64", 5,6, "OPEN");
insert into route value (9, 746, "2024-05-25 14:10:00", "2024-05-25 15:55:00", 2, "ul.Nutova 41",4,"ul.Polevaya 85", 6, 5, "OPEN");
insert into route value (10, 546, "2024-05-26 17:00:00", "2024-05-26 20:00:00", 3, "ul.Semina",4,"ul.Kovaleva 70", 2, 2, "OPEN");
insert into route value (11, 746, "2024-05-28 14:10:00", "2024-05-28 15:55:00", 2, "ul.Firsova 13",4, "ul.Marshalova 64", 5,6, "OPEN");

insert into login value (1,"admin","$2a$12$mBQNyrepunufj98gopBA1.pWCt9AqnJylXFy4M7fnLK0ZIy9LM/Aq",1,1,1,1,1,"ROLE_ADMIN");
insert into login value (2,"user", "$2a$12$mBQNyrepunufj98gopBA1.pWCt9AqnJylXFy4M7fnLK0ZIy9LM/Aq",1,1,1,1,2,"ROLE_USER");
insert into login value (3,"user3", "$2a$12$mBQNyrepunufj98gopBA1.pWCt9AqnJylXFy4M7fnLK0ZIy9LM/Aq",1,1,1,1,3,"ROLE_USER");
insert into login value (4,"user4", "$2a$12$mBQNyrepunufj98gopBA1.pWCt9AqnJylXFy4M7fnLK0ZIy9LM/Aq",1,1,1,1,4,"ROLE_USER");
insert into login value (5,"user5", "$2a$12$mBQNyrepunufj98gopBA1.pWCt9AqnJylXFy4M7fnLK0ZIy9LM/Aq", 1,1,1,1,5,"ROLE_USER");
insert into login value (6,"user6", "$2a$12$mBQNyrepunufj98gopBA1.pWCt9AqnJylXFy4M7fnLK0ZIy9LM/Aq",1,1,1,1,6,"ROLE_USER");
insert into login value (7,"user7", "$2a$12$mBQNyrepunufj98gopBA1.pWCt9AqnJylXFy4M7fnLK0ZIy9LM/Aq", 1,1,1,1,7,"ROLE_USER");
insert into login value (8,"operator", "$2a$12$mBQNyrepunufj98gopBA1.pWCt9AqnJylXFy4M7fnLK0ZIy9LM/Aq", 1,1,1,1,8,"ROLE_OPERATOR");

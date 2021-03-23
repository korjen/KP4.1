use workersdb;

create table `workContract` (
`contractNumber` int NOT NULL primary key AUTO_INCREMENT,
  `position` varchar(60) NOT NULL,
  `signDate` date NOT NULL,
  `endDate` date NOT NULL,
  `rate` float NOT NULL
);

create table `worker` (
`idWorker` int NOT NULL primary key AUTO_INCREMENT,
  `contractNumber` int NOT NULL,
  `experience` float NOT NULL,
   `numberOfdependants` int NOT NULL,
   `soloParent` bool NOT NULL,
   `active` bool NOT NULL,
   `accountNumber` varchar(35) NOT NULL,
  CONSTRAINT  FK_worker_workContract  FOREIGN KEY (contractNumber)
     REFERENCES  workContract(contractNumber) ON DELETE CASCADE  ON UPDATE CASCADE
);

create table `salary` (
`idSalary` int NOT NULL primary key AUTO_INCREMENT,
`idWorker` int NOT NULL,
  `sum` float NOT NULL,
  `tax` float NOT NULL,
  `workingDays` int NOT NULL,
  `vacationDays` int NOT NULL,
  `privilege` boolean NOT NULL,
  `calculationMonth` int NOT NULL,
  `monthDays` int NOT NULL,
    `payDate` date NOT NULL,
  CONSTRAINT  FK_salary_worker  FOREIGN KEY (idWorker)
     REFERENCES  worker(idWorker) ON DELETE CASCADE  ON UPDATE CASCADE
);

create table `passport` (
    `idWorker` int NOT NULL,
  `passportSeriesNumber` varchar(15) NOT NULL primary key,
  `surname` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `patronymic` varchar(45) NOT NULL,
  `gender` varchar(20) NOT NULL,
  `birthDate` date NOT NULL,
  `address` varchar(60) NOT NULL,
  CONSTRAINT  FK_passport_worker  FOREIGN KEY (idWorker)
     REFERENCES  worker(idWorker) ON DELETE CASCADE  ON UPDATE CASCADE
);

create table `user` (
`idWorker` int NOT NULL,
  `login` varchar(20) NOT NULL primary key,
  `role` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  CONSTRAINT  FK_user_worker  FOREIGN KEY (idWorker)
     REFERENCES  worker(idWorker) ON DELETE CASCADE  ON UPDATE CASCADE
);

create table `workingTime` (
`idTimetable` int NOT NULL primary key AUTO_INCREMENT,
`idWorker` int NOT NULL,
`startTime` datetime NULL,
`endTime` datetime NULL,
`workingHours` float NULL,
`attendance` bool NULL,
`reason` varchar(20) NULL,
CONSTRAINT  FK_workingTime_worker  FOREIGN KEY (idWorker)
     REFERENCES  worker(idWorker) ON DELETE CASCADE  ON UPDATE CASCADE
);
create table `privilege` (
`idPrivilege` int NOT NULL primary key AUTO_INCREMENT,
`name` TINYTEXT NOT NULL,
`effect` float NULL
);
create table `workerPrivilege` (
`idPrivilege` int NOT NULL,
`idWorker` int NOT NULL,
CONSTRAINT  FK_workerPrivilege_worker  FOREIGN KEY (idWorker)
     REFERENCES  worker(idWorker) ON DELETE CASCADE  ON UPDATE CASCADE,
     CONSTRAINT  FK_workerPrivilege_privilege  FOREIGN KEY (idPrivilege)
     REFERENCES  privilege(idPrivilege) ON DELETE CASCADE  ON UPDATE CASCADE
);

insert into `privilege`values(1, 'на каждого ребенка до 18 лет и/или иждивенца', 34);
insert into `privilege`values(2, 'для вдовы или вдовца, одинокого родителя, приемного родителя, опекуна или попечителя на каждого ребенка до 18 лет и/или иждивенца', 65);
insert into `privilege`values(3, 'ежемесячный доход ниже 709р', 117);

insert into `workContract` values(1, 'Начальник отдела кадров', '2017-03-19', '2022-03-19', 1270);
insert into `worker` values(1, 1, 3,0,false, true,'BY13 NBRB 3600 9000 0000 2Z00 AB00');
insert into `user` values(1, 'admin', 'ROLE_ADMIN', '$2a$11$kdsKWETpXbOA4GeZqb8OEu22ljRidLM1Xs7B4Y0ZvSsW5ZUqoTrZO');
insert into `passport` values(1, 'MP1834271', 'Иванов', 'Александр', 'Павлович', 'мужской', '1978-05-21', 'г. Минск, ул. Калиновского 17');

insert into `workContract` values(2, 'Специалист по кадрам', '2018-06-04', '2023-06-04', 1150);
insert into `worker` values(2, 2, 2.5, 2,false,true,'BY20 OLMP 3135 0000 0010 0000 0933');
insert into `user` values(2, 'user1', 'ROLE_MANAGER', '$2a$11$kdsKWETpXbOA4GeZqb8OEu22ljRidLM1Xs7B4Y0ZvSsW5ZUqoTrZO');
insert into `passport` values(2, 'MP5183647', 'Краснова', 'Светлана', 'Игоревна', 'женский', '1988-10-02', 'г. Минск ул. Белинского 21');

insert into `workContract` values(3, 'Юрист', '2015-11-30', '2020-11-30', 1120);
insert into `worker` values(3, 3, 5,0,false, true,'BY06 MMBN 3012 0000 1101 0000 0000');
insert into `user` values(3, 'user2', 'ROLE_WORKER', '$2a$11$kdsKWETpXbOA4GeZqb8OEu22ljRidLM1Xs7B4Y0ZvSsW5ZUqoTrZO');
insert into `passport` values(3, 'MP6382145', 'Леснов', 'Игорь', 'Алексеевич', 'мужской', '1984-05-24', 'г. Минск ул.Козлова 48');

insert into `workContract` values(4, 'Бухгалтер', '2018-09-14', '2021-09-14', 1050);
insert into `worker` values(4, 4, 2.5,1,true, true,'BY49 IRJS 3013 0002 0020 0000 0643');
insert into `user` values(4, 'user3', 'ROLE_WORKER', '$2a$11$kdsKWETpXbOA4GeZqb8OEu22ljRidLM1Xs7B4Y0ZvSsW5ZUqoTrZO');
insert into `passport` values(4, 'MP8359243', 'Беляев', 'Леонид', 'Николаевич', 'мужской', '1992-03-09', 'г. Минск ул.Калинина 32');

insert into `workContract` values(5, 'Бухгалтер', '2018-08-21', '2021-08-21', 1050);
insert into `worker` values(5, 5, 2,1,false, true,'BY13 NBRB 3600 9000 0000 2Z00 AB00');
insert into `user` values(5, 'user4', 'ROLE_WORKER', '$2a$11$kdsKWETpXbOA4GeZqb8OEu22ljRidLM1Xs7B4Y0ZvSsW5ZUqoTrZO');
insert into `passport` values(5, 'MP6482314', 'Маркова', 'Галина', 'Денисовна', 'женский', '1988-12-10', 'г. Минск ул.Гинтовта 29');

insert into `workContract` values(6, 'Главный бухгалтер', '2017-03-20', '2022-03-20', 1180);
insert into `worker` values(6, 6, 3,2,false, true,'BY11 ABLT 3012 0047 7933 5001 0001');
insert into `user` values(6, 'user5', 'ROLE_WORKER', '$2a$11$kdsKWETpXbOA4GeZqb8OEu22ljRidLM1Xs7B4Y0ZvSsW5ZUqoTrZO');
insert into `passport` values(6, 'MP6243175', 'Фомина', 'Карина', 'Витальевна', 'женский', '1991-07-15', 'г. Минск ул.Козлова 48');

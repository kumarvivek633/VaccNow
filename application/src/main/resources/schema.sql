



CREATE TABLE Branch (
    id int NOT null primary key,
    branch_name varchar2(100 char) NOT NULL
);

CREATE TABLE Users (
    user_name varchar2(50 char) NOT null primary key,
    first_name varchar2(100 char) NOT NULL,
	last_name varchar2(100 char) NOT NULL,
	email varchar2(100 char) NOT NULL
);

CREATE TABLE Vaccines (
    id int NOT null primary key,
    vaccine_name varchar2(100 char) NOT NULL
);


CREATE TABLE Branch_Vaccine_Inventory (
    id int NOT null primary key,
    vaccine_in_stock int NOT NULL,
    vacc_id int not null,
	branch_id int not null,
    FOREIGN KEY (vacc_id) REFERENCES Vaccines(id),
	FOREIGN KEY (branch_id) REFERENCES Branch(id)
);

CREATE TABLE Vaccination_Timeslot (
	id int NOT null primary key,
	start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
	capacity int NOT null ,
	branch_vacc_id int not null,
    FOREIGN KEY (branch_vacc_id) REFERENCES Branch_Vaccine_Inventory(id)
);

CREATE TABLE Vaccine_Registration (
    user_name varchar2(50 char) NOT null,
    time_slot_id int NOT NULL,
	status varchar2(50 char) NOT null,
	payment_mode varchar2(50 char) NOT null,
    FOREIGN KEY (time_slot_id) REFERENCES Vaccination_Timeslot(id),
	FOREIGN KEY (user_name) REFERENCES Users(user_name),
	constraint PK_Vacc_Reg primary key (user_name, time_slot_id)
);

CREATE SEQUENCE SEQ_Vaccination_Timeslot_ID
 START WITH     1
 INCREMENT BY   1;
 


 
 insert into branch (id, branch_name) values(1, 'Pune');
insert into branch (id, branch_name) values(2, 'Mumbai');

 insert into Users (user_name, first_name,last_name,email) values('vkumar', 'Vivek', 'Kumar', 'kumarvivek633@gmail');
insert into Users (user_name, first_name, last_name,email) values('akumar', 'Aman', 'Kumar', 'aman.kumar@gmail');

insert into Vaccines (id, vaccine_name ) values(1,'Covaxine');
insert into Vaccines (id, vaccine_name ) values(2,'Covishield');

insert into Branch_Vaccine_Inventory (id, vaccine_in_stock,vacc_id,branch_id) values(1,960,1, 1);
insert into Branch_Vaccine_Inventory (id, vaccine_in_stock, vacc_id,branch_id) values(2,960,2,1);

insert into Branch_Vaccine_Inventory (id, vaccine_in_stock,vacc_id,branch_id) values(3,960,1, 2);
insert into Branch_Vaccine_Inventory (id, vaccine_in_stock, vacc_id,branch_id) values(4,960,2,2);

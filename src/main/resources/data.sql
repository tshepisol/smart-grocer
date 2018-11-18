insert into role (role_name) values ('CUSTOMER');
insert into role (role_name) values ('ADMIN');
insert into role (role_name) values ('CLERK');



insert into customer_no (customer_number) values (1);
insert into hamper_no (hamper_number) values (1);

insert into partner (partner_type, referral) values ('LEVEL_1', 1);


insert into vendor (name) values ('SOMA');

insert into hamper (name, product_code, category, brand, quantity, unit_measure, price, vendor_id) values ('Pens', 'STA1', 'EDUCATION', 'Bic' ,12, 16, 200,1);
insert into hamper (name, product_code, category, brand, quantity, unit_measure, price, vendor_id) values ('Pencils', 'STA2', 'EDUCATION', 'HB' ,24, 10, 300, 1);

insert into hamper ( product_code, category, price, vendor_id) values ('UNI1', 'EDUCATION', 150, 1);
insert into hamper ( product_code, category, price, vendor_id) values ('UNI2', 'EDUCATION', 200, 1);

insert into hamper ( product_code, category, price, vendor_id) values ('COM1', 'EDUCATION', 350, 1);
insert into hamper ( product_code, category, price, vendor_id) values ('COM2', 'EDUCATION', 400, 1);
insert into hamper ( product_code, category, price, vendor_id) values ('COM3', 'EDUCATION', 450, 1);
insert into hamper ( product_code, category, price, vendor_id) values ('COM4', 'EDUCATION', 500, 1);

insert into hamper ( product_code, category, price, vendor_id) values ('GRO1', 'GROCERIES', 300, 1);
insert into hamper ( product_code, category, price, vendor_id) values ('GRO2', 'GROCERIES', 400, 1);
insert into hamper ( product_code, category, price, vendor_id) values ('GRO3', 'GROCERIES', 500, 1);
insert into hamper ( product_code, category, price, vendor_id) values ('GRO4', 'GROCERIES', 600, 1);

insert into hamper ( product_code, category, price, vendor_id) values ('CAT1', 'CATERING', 500, 1);
insert into hamper ( product_code, category, price, vendor_id) values ('CAT2', 'CATERING', 600, 1);
insert into hamper ( product_code, category, price, vendor_id) values ('CAT3', 'CATERING', 700, 1);
insert into hamper ( product_code, category, price, vendor_id) values ('CAT4', 'CATERING', 800, 1);

insert into hamper ( product_code, category, price, vendor_id) values ('HOU1', 'HOUSEHOLD', 300, 1);
insert into hamper ( product_code, category, price, vendor_id) values ('HOU2', 'HOUSEHOLD', 500, 1);
insert into hamper ( product_code, category, price, vendor_id) values ('HOU3', 'HOUSEHOLD', 650, 1);
insert into hamper ( product_code, category, price, vendor_id) values ('HOU4', 'HOUSEHOLD', 550, 1);

--insert into hamper ( product_code, category, price, vendor_id) values ('STA2', 'EDUCATION', 50, 1);
--insert into hamper ( product_code, category, price, vendor_id) values ('STA2', 'EDUCATION', 30, 1);
--insert into hamper ( product_code, category, price, vendor_id) values ('STA2', 'EDUCATION', 75, 1);
--insert into hamper ( product_code, category, price, vendor_id) values ('STA2', 'EDUCATION', 100, 1);
--insert into hamper ( product_code, category, price, vendor_id) values ('STA2', 'EDUCATION', 150, 1);
--insert into hamper ( product_code, category, price, vendor_id) values ('STA2', 'EDUCATION', 200, 1);
--insert into hamper ( product_code, category, price, vendor_id) values ('STA2', 'EDUCATION', 250, 1);
--insert into hamper ( product_code, category, price, vendor_id) values ('STA2', 'EDUCATION', 275, 1);
--insert into hamper ( product_code, category, price, vendor_id) values ('STA2', 'EDUCATION', 300, 1);

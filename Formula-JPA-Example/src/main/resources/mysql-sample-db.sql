create table product (
	product_id integer not null auto_increment, 
	manufactured_date datetime, 
	price float, 
	product_code varchar(255), 
	product_name varchar(255), 
	vat float, 
	primary key (product_id)
);

INSERT INTO product (manufactured_date,price,product_code,product_name,vat) VALUES (STR_TO_DATE('03-03-2016', '%d-%m-%Y'), 130, 'AZ00010','IPhone-New-7', 5.6);
INSERT INTO product (manufactured_date,price,product_code,product_name,vat) VALUES (STR_TO_DATE('01-01-2016', '%d-%m-%Y'), 140, 'AZ00011','Lenovo Vibe X3', 5.6);
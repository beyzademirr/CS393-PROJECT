insert into Car(ID, BARCODE, license_plate_number, passenger_capacity, brand, model, mileage, transmission_type, daily_price, type, status)
values (100, 'ABCDF', 2, 3, 'R', 'K', 10, 'H', 100, 'C', 'AVAILABLE');

insert into Car(ID, BARCODE, license_plate_number, passenger_capacity, brand, model, mileage, transmission_type, daily_price, type, status)
values (200, 'XYZK', 2, 3, 'R', 'K', 10, 'H', 100, 'C', 'AVAILABLE');

insert into Equipment(ID, NAME, price)
values (100, 'Toy', 90);

insert into Equipment(ID, NAME, price)
values (200, 'Perfume', 90);

insert into Location(LOCATION_ID, NAME, address)
values (100, 'Sabiha', 'İstanbul');

insert into Location(LOCATION_ID, NAME, address)
values (200, 'Çekmeköy', 'İstanbul');

insert into Service(ID, NAME, price)
values (100, 'Choco', 90);

insert into Service(ID, NAME, price)
values (200, 'Hey', 90);

insert into Member(MEMBER_ID, NAME, ADDRESS, email, phone, driving_license_number)
values (100, 'Beyza', 'ist', 'a@b', '123456', 123);

insert into Member(MEMBER_ID, NAME, ADDRESS, email, phone, driving_license_number)
values (200, 'Tarık', 'ist', 't@b', '123456', 125);
/* Here you can add inserts etc. which will be run at the start of the application */
/* All statements must end with `;` */

INSERT INTO patient(first_name, last_name, pesel, street_name, building_number, zip_code, city, creation_date)
VALUES ('Jan', 'Nowak', '77050972126', 'Ulicowska', '4a', '65-123', 'Poznań', date('2019-10-11')),
       ('Andrzej', 'Grabowski', '53032317633', 'Ćwiartki', '3/4', '50-529', 'Wrocław', date('2019-10-11'));
/* Here you can add inserts etc. which will be run at the start of the application */
/* All statements must end with `;` */

INSERT INTO patient(first_name, last_name, pesel, street_name, building_number, zip_code, city, creation_date)
VALUES ('Jan', 'Nowak', 'Dw8IDQgBDwoJCg4=', 'Ulicowska', '4a', '65-123', 'Poznań', current_timestamp),
       ('Andrzej', 'Grabowski', 'DQsICwoLCQ8OCws=', 'Ćwiartki', '3/4', '50-529', 'Wrocław', current_timestamp);
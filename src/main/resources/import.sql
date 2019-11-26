/* Here you can add inserts etc. which will be run at the start of the application */
/* All statements must end with `;` */

INSERT INTO patient(first_name, last_name)
VALUES ('Jan', 'Nowak'),
       ('Andrzej', 'Grabowski');

INSERT INTO examination(code, patient_id)
VALUES ('123', 1);

INSERT INTO xray_examination(id)
VALUES (1);
INSERT INTO sex VALUES (1, 'male');
INSERT INTO sex VALUES (2, 'female');

INSERT INTO stew_pan VALUES (1, 1.5, 50);
INSERT INTO stew_pan VALUES (2, 2, 53);
INSERT INTO stew_pan VALUES (3, 3, 60);
INSERT INTO stew_pan VALUES (4, 5, 65);
INSERT INTO stew_pan VALUES (5, 10, 77);

INSERT INTO manager VALUES (1, 'Anna Talstaya');
INSERT INTO manager VALUES (2, 'Igor Lopo');
INSERT INTO manager VALUES (3, 'Maxim Huil');
INSERT INTO manager VALUES (4, 'Kate Ruti');
INSERT INTO manager VALUES (5, 'Nikita Popoiu');
INSERT INTO manager VALUES (6, 'Maria Eruio');
INSERT INTO manager VALUES (7, 'Egor Farx');
INSERT INTO manager VALUES (8, 'Liza Jakio');
INSERT INTO manager VALUES (9, 'Vlad Lopout');
INSERT INTO manager VALUES (10, 'Daria Furi');

INSERT INTO advertising_presentation VALUES (1, 'Minsk, Gamarnick street 1, 55', CURRENT_TIMESTAMP);
INSERT INTO advertising_presentation VALUES (2, 'Minsk, Yakub Kolas street 1, 13', CURRENT_TIMESTAMP);
INSERT INTO advertising_presentation VALUES (3, 'Minsk, Yakub Kolas street 33, 4', CURRENT_TIMESTAMP);
INSERT INTO advertising_presentation VALUES (4, 'Minsk, Miroshnichenko street 12, 67', CURRENT_TIMESTAMP);
INSERT INTO advertising_presentation VALUES (5, 'Minsk, Green street 12, 22', CURRENT_TIMESTAMP);
INSERT INTO advertising_presentation VALUES (6, 'Minsk, Oxford street 56, 12', CURRENT_TIMESTAMP);
INSERT INTO advertising_presentation VALUES (7, 'Minsk, Kolcova street 2, 42', CURRENT_TIMESTAMP);
INSERT INTO advertising_presentation VALUES (8, 'Minsk, Tolstoi street 19, 68', CURRENT_TIMESTAMP);
INSERT INTO advertising_presentation VALUES (9, 'Minsk, Bolgogradskaya street 6, 8', CURRENT_TIMESTAMP);
INSERT INTO advertising_presentation VALUES (10, 'Minsk, prospect Nezavisimosti street 7, 12', CURRENT_TIMESTAMP);

INSERT INTO person VALUES (1, '375293334547', 1, 'Jack Sparrow');
INSERT INTO person VALUES (2, '375293234976', 1, 'Nikita Lilo');
INSERT INTO person VALUES (3, '375292634616', 1, 'Max Puiot');
INSERT INTO person VALUES (4, '375447634600', 2, 'Anna Deri');
INSERT INTO person VALUES (5, '375447634511', 1, 'Artem Noikov');
INSERT INTO person VALUES (6, '375296634610', 2, 'Maria Juio');
INSERT INTO person VALUES (7, '375446534610', 2, 'Anastasia Muiokio');
INSERT INTO person VALUES (8, '375446634622', 1, 'Artur Giroi');
INSERT INTO person VALUES (9, '375296534676', 2, 'Kate Kytoeva');
INSERT INTO person VALUES (10, '375296734645', 1, 'Egor Ivanov');

INSERT INTO regular_visitor VALUES (1, 1, 1);
INSERT INTO regular_visitor VALUES (2, 2, 1);
INSERT INTO regular_visitor VALUES (3, 7, 2);
INSERT INTO regular_visitor VALUES (4, 2, 10);
INSERT INTO regular_visitor VALUES (5, 5, 7);
INSERT INTO regular_visitor VALUES (6, 4, 9);
INSERT INTO regular_visitor VALUES (7, 2, 10);
INSERT INTO regular_visitor VALUES (8, 8, 3);
INSERT INTO regular_visitor VALUES (9, 7, 2);
INSERT INTO regular_visitor VALUES (10, 4, 10);

INSERT INTO set_of_stew_pans VALUES (1, 3, 1, to_date('01-12-2019', 'dd-mm-yyyy'));
INSERT INTO set_of_stew_pans VALUES (2, 3, 2, to_date('11-02-2019', 'dd-mm-yyyy'));
INSERT INTO set_of_stew_pans VALUES (3, 1, 7, to_date('05-11-2019', 'dd-mm-yyyy'));
INSERT INTO set_of_stew_pans VALUES (4, 4, 2, to_date('05-11-2019', 'dd-mm-yyyy'));
INSERT INTO set_of_stew_pans VALUES (5, 3, 5, to_date('13-12-2019', 'dd-mm-yyyy'));
INSERT INTO set_of_stew_pans VALUES (6, 1, 4, to_date('13-12-2019', 'dd-mm-yyyy'));
INSERT INTO set_of_stew_pans VALUES (7, 1, 2, to_date('14-06-2019', 'dd-mm-yyyy'));
INSERT INTO set_of_stew_pans VALUES (8, 6, 8, to_date('13-12-2019', 'dd-mm-yyyy'));
INSERT INTO set_of_stew_pans VALUES (9, 3, 7, to_date('01-07-2019', 'dd-mm-yyyy'));
INSERT INTO set_of_stew_pans VALUES (10, 5, 4, to_date('01-07-2019', 'dd-mm-yyyy'));

INSERT INTO stew_pan_in_set VALUES (1, 1, 1);
INSERT INTO stew_pan_in_set VALUES (2, 2, 3);
INSERT INTO stew_pan_in_set VALUES (3, 3, 1);
INSERT INTO stew_pan_in_set VALUES (4, 4, 2);
INSERT INTO stew_pan_in_set VALUES (5, 5, 2);
INSERT INTO stew_pan_in_set VALUES (6, 4, 1);
INSERT INTO stew_pan_in_set VALUES (7, 2, 1);
INSERT INTO stew_pan_in_set VALUES (8, 3, 2);
INSERT INTO stew_pan_in_set VALUES (9, 3, 1);
INSERT INTO stew_pan_in_set VALUES (10, 1, 2);

INSERT INTO visitor VALUES (1, 1, 'Happy');
INSERT INTO visitor VALUES (2, 2, 'Happy');
INSERT INTO visitor VALUES (3, 3, 'Happy');
INSERT INTO visitor VALUES (4, 4, 'Happy');
INSERT INTO visitor VALUES (5, 5, 'Happy');
INSERT INTO visitor VALUES (6, 6, 'Sad');
INSERT INTO visitor VALUES (7, 7, 'Happy');
INSERT INTO visitor VALUES (8, 8, 'Sad');
INSERT INTO visitor VALUES (9, 9, 'Happy');
INSERT INTO visitor VALUES (10, 10, 'Happy');

INSERT INTO information_call VALUES (1, 1, 1, 2);
INSERT INTO information_call VALUES (2, 2, 3, 4);
INSERT INTO information_call VALUES (3, 3, 1, 3);
INSERT INTO information_call VALUES (4, 4, 2, 5);
INSERT INTO information_call VALUES (5, 5, 2, 1);
INSERT INTO information_call VALUES (6, 4, 1, 4);
INSERT INTO information_call VALUES (7, 2, 1, 6);
INSERT INTO information_call VALUES (8, 3, 2, 7);
INSERT INTO information_call VALUES (9, 3, 1, 8);
INSERT INTO information_call VALUES (10, 1, 2, 9);

INSERT INTO registration VALUES (1, 1, 1, 2);
INSERT INTO registration VALUES (2, 2, 3, 4);
INSERT INTO registration VALUES (3, 3, 1, 3);
INSERT INTO registration VALUES (4, 4, 2, 5);
INSERT INTO registration VALUES (5, 5, 2, 1);
INSERT INTO registration VALUES (6, 4, 1, 4);
INSERT INTO registration VALUES (7, 2, 1, 6);
INSERT INTO registration VALUES (8, 3, 2, 7);
INSERT INTO registration VALUES (9, 3, 1, 8);
INSERT INTO registration VALUES (10, 1, 2, 9);

--Триггер должен отслеживать, чтобы клиент совершал покупки только у своего менеджера;
CREATE OR REPLACE TRIGGER customer_manager BEFORE
    INSERT ON set_of_stew_pans
    FOR EACH ROW
DECLARE
    exist NUMBER;
BEGIN
    SELECT
        COUNT(*)
    INTO exist
    FROM
        registration
        INNER JOIN advertising_presentation ON registration.advertising_presentation_id = advertising_presentation.id
    WHERE
        registration.visitor_id = :new.person_id
        AND registration.manager_id = :new.manager_id
        AND trunc(advertising_presentation.date_and_time) = :new.date_of_creation;

    IF exist = 0 THEN
        raise_application_error(-20000, 'The customer makes a buy not from his manager');
    END IF;
END;
 
 
 --Триггер должен отслеживать, чтобы набор комплектовался кастрюлями разных объёмов;
CREATE OR REPLACE TRIGGER check_set_of_stew_pan BEFORE
    INSERT ON stew_pan_in_set
    FOR EACH ROW
DECLARE
    number_of_stew_pans_in_set_with_such_volume INTEGER;
BEGIN
    SELECT
        COUNT(*)
    INTO number_of_stew_pans_in_set_with_such_volume
    FROM
        stew_pan_in_set
    WHERE
        stew_pan_id = :new.stew_pan_id
        AND set_of_stew_pans_id = :new.set_of_stew_pans_id;

    IF number_of_stew_pans_in_set_with_such_volume != 0 
    THEN raise_application_error(-20000, 'Such set already contains stew pan of this volume');
    END IF;
END;
 
 
--Триггер должен отслеживать, чтобы статус постоянного получал только клиент, совершивший >= двух покупок.
CREATE OR REPLACE TRIGGER check_regular_user BEFORE
    INSERT ON regular_customer
    FOR EACH ROW
DECLARE
    number_of_buys INTEGER;
BEGIN
    SELECT
        COUNT(*)
    INTO number_of_buys
    FROM
        set_of_stew_pans
    WHERE
        person_id = :new.person_id;

    IF number_of_buys < 2 THEN
        raise_application_error(-20000, 'This user has made less than two buys');
    END IF;
END;
 
 
 
 
 
 
 
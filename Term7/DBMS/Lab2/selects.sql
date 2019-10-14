--найти сведени€ о продажах конкретного менеджера (‘»ќ параметр запроса) за текущий мес€ц;
SELECT
    person.name           AS buyer_name,
    person.mobile_number,
    sex.value             AS sex,
    set_of_stew_pans.id   AS id_of_set,
    set_of_stew_pans.date_of_creation,
    stew_pan.volume       AS stew_pan_volume,
    stew_pan.price
FROM
    set_of_stew_pans
    INNER JOIN manager ON manager.id = set_of_stew_pans.manager_id
    INNER JOIN stew_pan_in_set ON set_of_stew_pans.id = stew_pan_in_set.set_of_stew_pans_id
    INNER JOIN stew_pan ON stew_pan.id = stew_pan_in_set.stew_pan_id
    INNER JOIN person ON set_of_stew_pans.person_id = person.id
    INNER JOIN sex ON person.sex_id = sex.id
WHERE
    manager.name =: param
    AND EXTRACT(MONTH FROM date_of_creation) = EXTRACT(MONTH FROM sysdate)
    AND EXTRACT(YEAR FROM date_of_creation) = EXTRACT(YEAR FROM sysdate);


--найти сведени€ о трЄх покупател€х с наибольшими расходами за все презентации;
SELECT
    *
FROM
    (
        SELECT
            person.name,
            sex.value AS sex,
            person.mobile_number,
            total_price
        FROM
            (
                SELECT
                    set_of_stew_pans.person_id,
                    SUM(stew_pan.price) AS total_price
                FROM
                    set_of_stew_pans
                    INNER JOIN stew_pan_in_set ON set_of_stew_pans.id = stew_pan_in_set.set_of_stew_pans_id
                    INNER JOIN stew_pan ON stew_pan.id = stew_pan_in_set.stew_pan_id
                GROUP BY
                    set_of_stew_pans.person_id
                ORDER BY
                    total_price DESC
            )
            INNER JOIN person ON person.id = person_id
            INNER JOIN sex ON sex.id = person.sex_id
    )
WHERE
    ROWNUM < 4;
    
        
--найти сведени€ в разрезе менеджеров о количестве не результативных звонков. 
SELECT
    manager.name,
    number_of_failed_calls
FROM
    (
        SELECT
            manager_id,
            COUNT(*) AS number_of_failed_calls
        FROM
            (
                SELECT
                    information_call.manager_id   AS manager_id,
                    registration.id               AS registration_id
                FROM
                    information_call left
                    JOIN registration ON information_call.visitor_id = registration.visitor_id
                WHERE
                    registration.id IS NULL
            )
        GROUP BY
            manager_id
    )
    INNER JOIN manager ON manager_id = manager.id;
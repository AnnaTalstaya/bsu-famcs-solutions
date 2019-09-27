CREATE TABLE stew_pan
(
    id NUMBER NOT NULL PRIMARY KEY,
    volume NUMBER NOT NULL,
    price NUMBER NOT NULL
);

CREATE TABLE advertising_presentation
(
    id NUMBER NOT NULL PRIMARY KEY,
    address VARCHAR2(100) NOT NULL,
    date_and_time TIMESTAMP NOT NULL
);

CREATE TABLE sex
(
    id NUMBER NOT NULL PRIMARY KEY,
    value VARCHAR2(20) NOT NULL
);

CREATE TABLE manager
(
    id NUMBER NOT NULL PRIMARY KEY,
    name VARCHAR2(100) NOT NULL
);

CREATE TABLE person
(
    id NUMBER NOT NULL PRIMARY KEY,
    mobile_number VARCHAR2(50) NOT NULL,
    sex_id NUMBER NOT NULL,
	name VARCHAR2(100) NOT NULL,
    
    CONSTRAINT person_fk_sex
    FOREIGN KEY (sex_id) REFERENCES sex(id)
);

CREATE TABLE regular_visitor
(
    id NUMBER NOT NULL PRIMARY KEY,
    person_id NUMBER NOT NULL,
    manager_id NUMBER NOT NULL,
    
    CONSTRAINT regular_visitor_fk_person
    FOREIGN KEY (person_id) REFERENCES person(id),
    
    CONSTRAINT regular_visitor_fk_manager
    FOREIGN KEY (manager_id) REFERENCES manager(id)
);

CREATE TABLE regular_customer
(
    id NUMBER NOT NULL PRIMARY KEY,
    person_id NUMBER NOT NULL,
    manager_id NUMBER NOT NULL,
    
    CONSTRAINT regular_customer_fk_person
    FOREIGN KEY (person_id) REFERENCES person(id),
    
    CONSTRAINT regular_customer_fk_manager
    FOREIGN KEY (manager_id) REFERENCES manager(id)
);

CREATE TABLE visitor
(
    id NUMBER NOT NULL PRIMARY KEY,
    person_id NUMBER NOT NULL,
    reaction VARCHAR2(200) NOT NULL,
    
    CONSTRAINT visitor_fk_person
    FOREIGN KEY (person_id) REFERENCES person(id)
);

CREATE TABLE information_call
(
    id NUMBER NOT NULL PRIMARY KEY,
    visitor_id NUMBER NOT NULL,
    advertising_presentation_id NUMBER NOT NULL,
    manager_id NUMBER NOT NULL,
    
    CONSTRAINT information_call_fk_visitor
    FOREIGN KEY (visitor_id) REFERENCES visitor(id),
    
    CONSTRAINT information_call_fk_advertising_presentation
    FOREIGN KEY (advertising_presentation_id) REFERENCES advertising_presentation(id),
    
    CONSTRAINT information_call_fk_manager
    FOREIGN KEY (manager_id) REFERENCES manager(id)
);

CREATE TABLE registration
(
    id NUMBER NOT NULL PRIMARY KEY,
    visitor_id NUMBER NOT NULL,
    manager_id NUMBER NOT NULL,
    advertising_presentation_id NUMBER NOT NULL,
    
    CONSTRAINT registration_fk_visitor
    FOREIGN KEY (visitor_id) REFERENCES visitor(id),
    
    CONSTRAINT registration_fk_manager
    FOREIGN KEY (manager_id) REFERENCES manager(id),
    
    CONSTRAINT registration_fk_advertising_presentation
    FOREIGN KEY (advertising_presentation_id) REFERENCES advertising_presentation(id)
);

CREATE TABLE set_of_stew_pans
(
    id NUMBER NOT NULL PRIMARY KEY,
    manager_id NUMBER NOT NULL,
    person_id NUMBER NOT NULL,
    date_of_creation DATE NOT NULL,

    CONSTRAINT set_of_stew_pans_fk_manager
    FOREIGN KEY (manager_id) REFERENCES manager(id),
    
    CONSTRAINT set_of_stew_pans_fk_person
    FOREIGN KEY (person_id) REFERENCES person(id)
);

CREATE TABLE stew_pan_in_set
(
    id NUMBER NOT NULL PRIMARY KEY,
    stew_pan_id NUMBER NOT NULL,
    set_of_stew_pans_id NUMBER NOT NULL,
    
    CONSTRAINT stew_pan_in_set_fk_stew_pan
    FOREIGN KEY (stew_pan_id) REFERENCES stew_pan(id),
    
    CONSTRAINT stew_pan_in_set_fk_set_of_stew_pans
    FOREIGN KEY (set_of_stew_pans_id) REFERENCES set_of_stew_pans(id)
);


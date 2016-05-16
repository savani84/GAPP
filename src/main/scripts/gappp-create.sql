
    create table app_status (
        id int4 not null,
        status varchar(255),
        primary key (id)
    );

    create table applications (
        id int4 not null,
        application_date date,
        cin int4,
        citizen_country varchar(255),
        date_of_birth date,
        first_name varchar(255),
        gender varchar(255),
        last_name varchar(255),
        phoneno varchar(255),
        term varchar(255),
        department_id int4,
        program_id int4,
        status_id int4,
        users_id int4,
        primary key (id)
    );

    create table comments (
        id int4 not null,
        commentDescription varchar(255),
        commentTime timestamp,
        application_id int4,
        status_id int4,
        primary key (id)
    );

    create table degrees (
        id int4 not null,
        degree_name varchar(255),
        gpa float8,
        major varchar(255),
        time_period varchar(255),
        transcript varchar(255),
        university_name varchar(255),
        application_id int4,
        primary key (id)
    );

    create table departments (
        id int4 not null,
        dept_name varchar(255),
        primary key (id)
    );

    create table others_field (
        id int4 not null,
        name_field varchar(255),
        required boolean,
        type_field varchar(255),
        department_id int4,
        primary key (id)
    );

    create table others_field_value (
        id int4 not null,
        OtherValue varchar(255),
        application_id int4,
        otherField_id int4,
        primary key (id)
    );

    create table programs (
        id int4 not null,
        prog_name varchar(255),
        department_id int4,
        primary key (id)
    );

    create table roles (
        id int4 not null,
        role_name varchar(255),
        primary key (id)
    );

    create table users (
        id int4 not null,
        e_mail varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255),
        department_id int4,
        role_id int4,
        primary key (id)
    );

    alter table applications 
        add constraint FK_78su0wnn02817h354falvtovc 
        foreign key (department_id) 
        references departments;

    alter table applications 
        add constraint FK_fvv8mt4q3l0jlgem0374rwfb5 
        foreign key (program_id) 
        references programs;

    alter table applications 
        add constraint FK_bmuuaimvfefq39ypbc6y2itdu 
        foreign key (status_id) 
        references app_status;

    alter table applications 
        add constraint FK_jrun151d6ktjkayjmy7ylvq8v 
        foreign key (users_id) 
        references users;

    alter table comments 
        add constraint FK_e3qchf4nk3j4hankpwu6f4r1b 
        foreign key (application_id) 
        references applications;

    alter table comments 
        add constraint FK_ikag7dhf1qd9dftfwl0l2on0m 
        foreign key (status_id) 
        references app_status;

    alter table degrees 
        add constraint FK_rqi4wrhhtwwk6r9c9hcjyext9 
        foreign key (application_id) 
        references applications;

    alter table others_field 
        add constraint FK_taa6m99b8oi1blabti4rdlkq5 
        foreign key (department_id) 
        references departments;

    alter table others_field_value 
        add constraint FK_r2rjfwwyvnv50lyioglj6lnf 
        foreign key (application_id) 
        references applications;

    alter table others_field_value 
        add constraint FK_e41d3msgkscfhl8vkoj2xmim5 
        foreign key (otherField_id) 
        references others_field;

    alter table programs 
        add constraint FK_t38cee5jtiwtw07papp2rjlca 
        foreign key (department_id) 
        references departments;

    alter table users 
        add constraint FK_7phkg3qghukhuw9kj3ahkmw 
        foreign key (department_id) 
        references departments;

    alter table users 
        add constraint FK_krvotbtiqhudlkamvlpaqus0t 
        foreign key (role_id) 
        references roles;

    create sequence hibernate_sequence minvalue 100;


    
    
INSERT INTO roles(id, role_name)VALUES (1, 'admin');
INSERT INTO roles(id, role_name)VALUES (2, 'staff');
INSERT INTO roles(id, role_name)VALUES (3, 'student');

INSERT INTO departments(id, dept_name)VALUES (1, 'Accounting');
INSERT INTO departments(id, dept_name)VALUES (2, 'CS');

INSERT INTO programs(id, prog_name, department_id)VALUES (1, 'MS', 1);
INSERT INTO programs(id, prog_name, department_id)VALUES (2, 'BS', 1);
INSERT INTO programs(id, prog_name, department_id)VALUES (3, 'MS', 2);
INSERT INTO programs(id, prog_name, department_id)VALUES (4, 'BS', 2);

INSERT INTO users(id, e_mail, first_name, last_name, password, department_id, role_id)
VALUES (1, 'admin@localhost.localdomain', 'admin', '', 'abcd', null, 1);
INSERT INTO users(id, e_mail, first_name, last_name, password, department_id, role_id)
VALUES (2, 'staff1@localhost.localdomain', 'staff', '1', 'abcd', 1, 2);
INSERT INTO users(id, e_mail, first_name, last_name, password, department_id, role_id)
VALUES (3, 'staff2@localhost.localdomain', 'staff', '2', 'abcd', 2, 2);
INSERT INTO users(id, e_mail, first_name, last_name, password, department_id, role_id)
VALUES (4, 'student1@localhost.localdomain', 'student', '1', 'abcd', null, 3);
INSERT INTO users(id, e_mail, first_name, last_name, password, department_id, role_id)
VALUES (5, 'student2@localhost.localdomain', 'student', '2', 'abcd', null, 3);

INSERT INTO app_status(id, status)VALUES (1, 'Not Submitted');
INSERT INTO app_status(id, status)VALUES (2, 'New Submitted');
INSERT INTO app_status(id, status)VALUES (3, 'Pending Review');
INSERT INTO app_status(id, status)VALUES (4, 'Denied');
INSERT INTO app_status(id, status)VALUES (5, 'Recommend Admit');
INSERT INTO app_status(id, status)VALUES (6, 'Recommend Admit with Condition');

INSERT INTO applications(id,application_date, cin, citizen_country, date_of_birth, first_name,
            gender, last_name, phoneno, term, department_id, program_id, status_id,users_id)
VALUES (1,'2016-03-02',304454961,'India','1992-01-25','student',
           'male','1', '626-454-9729','Fall 2016', 1, 1, 1, 4);

INSERT INTO others_field(id, name_field, required, type_field,department_id)
VALUES (1,'GMAT','1','Number',1);
INSERT INTO others_field(id, name_field, required, type_field,department_id)
VALUES (2,'Statement of Purpose','1','File',1);

INSERT INTO degrees(id, degree_name, gpa, major, time_period, transcript, university_name,application_id)
VALUES (1,'12th',3.5,'Science','2 years', '', 'Ambe Vidhalaya', 1);
INSERT INTO degrees(id, degree_name, gpa, major, time_period, transcript, university_name,application_id)
VALUES (2,'BE',3.7,'Computer Science','4 years', '', 'VTU', 1);

INSERT INTO others_field_value(id, othervalue,application_id,otherfield_id) 
VALUES (1, 1000, 1, 1);

INSERT INTO comments(id, commentdescription, commenttime, application_id, status_id)
VALUES (1,'New Application for fall 2016','2016-01-31', 1, 1);
INSERT INTO comments(id, commentdescription, commenttime, application_id, status_id)
VALUES (2,'Denied for fall 2016','2016-01-31', 1, 2);
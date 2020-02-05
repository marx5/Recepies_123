CREATE table "M_RECEPIES" (
    "ID"            NUMBER GENERATED ALWAYS AS IDENTITY,
    "TITLE"         VARCHAR2(20) NOT NULL,
    "DESCRIPTION"   VARCHAR2(20) NOT NULL,
    "TEXT"          VARCHAR2(4000),
    "KEY_WORDS"     VARCHAR2(100),
    "DIFICULTY"     NUMBER(2),
    "CREATED_BY"    VARCHAR2(50),
    "CREATION_DATE" DATE,
    constraint  "M_RECEPIES_PK" primary key ("ID")
)
/

alter table m_recepies modify creation_date date default sysdate;
/

/*
https://apex.oracle.com/pls/apex/f?p=56287 
username: demo
pwd: demo12345
*/

/*
API URL:
https://apex.oracle.com/pls/apex/dbp_project/mobileapp/recepies
*/
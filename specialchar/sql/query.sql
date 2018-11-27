language table
--------------
id, language_data

create table language_data_table( id number(3), data varchar2(2048));

insert into language_data_table values( 1, 'abcd' );

insert into language_data_table (id,data) values(?,?)

select * from language_data_table where id = ( select max(id) from language_data_table );
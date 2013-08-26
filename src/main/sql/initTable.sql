create schema qdslsampleuser;

create table mytable (
    id          bigint      primary key
    ,myarray    varchar(8)[]
);


insert into mytable (id, myarray) values (1, array['a','b','c']);

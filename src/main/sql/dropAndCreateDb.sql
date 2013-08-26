-- user:postgres
drop database if exists qdslsample;
drop user if exists qdslsampleuser;
create user qdslsampleuser with createdb login password 'qdslsampleuser' ;
create database qdslsample owner qdslsampleuser encoding 'UTF-8';

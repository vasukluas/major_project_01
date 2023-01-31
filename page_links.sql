show databases;
create database searchenginejava;

use searchenginejava;

create table myTable(
serial_no integer,
Name varchar(200),
age integer
);

create table pages(
pageTitle varchar(200),
pageLink text,
pageText mediumtext
);

select * from pages;

select pagetitle,pagelink,(length(lower(pagetext))-length(replace(lower(pagetext),'java','')))/length('java') as countoccurence from pages
order by countoccurence desc
limit 30;
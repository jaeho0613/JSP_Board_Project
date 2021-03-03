drop database if exists Exam;

create database Exam;
use Exam;

create table User(
	userID varchar(20) primary key,
	userName varchar(20) not null,
    userPassword varchar(20) not null,
    userPasswordHash varchar(64) not null
);

create table Board(
	bdNum Integer primary key auto_increment,
    bdTitle varchar(40) not null,
    bdAuthor varchar(20) not null,
    bdCreateDate Date not null,
    bdViews integer    
);

insert into board(bdTitle, bdAuthor, bdCreateDate, bdViews) values ("게시판 제목", "게시판 작성자", now(), null);


select * 
from (select @rownum:=@rownum+1 as abc, bdTitle, bdAuthor, bdCreateDate, bdViews
from board, (select @rownum:=0) tmp
) list
where abc between 1 and 10;

delimiter $$
drop procedure if exists loopInsert $$
create procedure loopInsert()
begin
declare i int default 1;
while(i < 21) do
insert into board(bdTitle, bdAuthor, bdCreateDate, bdViews)
(select bdTitle, bdAuthor, bdCreateDate, bdViews from board);
set i = i + 1;
end while;
end $$

call loopInsert();
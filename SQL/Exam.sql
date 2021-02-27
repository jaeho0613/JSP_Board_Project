drop database if exists Exam;

create database Exam;
use Exam;

create table User(
	userID integer primary key,
	userName varchar(20) not null,
    userPassoword varchar(20) not null,
    userPasswordHasah varchar(64) not null
);

create table Board(
	bdNum Integer primary key auto_increment,
    bdTitle varchar(40) not null,
    bdAuthor varchar(20) not null,
    bdCreateDate Date not null,
    bdViews integer    
);

delimiter $$
drop procedure if exists loopinsert $$
create procedure loopinsert()
begin
declare i int default 1;
while(i <1000000) do
insert into board (bdNum, bdTitle, bdAuthor, bdCreateDate, bdViews) values (null, concat('게시글 쓰기 ', i), "정재호", now(), null),
																		   (null, concat('게시글 쓰기 ', i + 1), "정재호", now(), null),
                                                                           (null, concat('게시글 쓰기 ', i + 2), "정재호", now(), null),
                                                                           (null, concat('게시글 쓰기 ', i + 3), "정재호", now(), null),
                                                                           (null, concat('게시글 쓰기 ', i + 4), "정재호", now(), null),
                                                                           (null, concat('게시글 쓰기 ', i + 5), "정재호", now(), null),
                                                                           (null, concat('게시글 쓰기 ', i + 6), "정재호", now(), null),
                                                                           (null, concat('게시글 쓰기 ', i + 7), "정재호", now(), null),
                                                                           (null, concat('게시글 쓰기 ', i + 8), "정재호", now(), null),
                                                                           (null, concat('게시글 쓰기 ', i + 9), "정재호", now(), null),
                                                                           (null, concat('게시글 쓰기 ', i + 10), "정재호", now(), null),
																		   (null, concat('게시글 쓰기 ', i + 11), "정재호", now(), null),
                                                                           (null, concat('게시글 쓰기 ', i + 12), "정재호", now(), null),
                                                                           (null, concat('게시글 쓰기 ', i + 13), "정재호", now(), null),
                                                                           (null, concat('게시글 쓰기 ', i + 14), "정재호", now(), null),
                                                                           (null, concat('게시글 쓰기 ', i + 15), "정재호", now(), null),
                                                                           (null, concat('게시글 쓰기 ', i + 16), "정재호", now(), null),
                                                                           (null, concat('게시글 쓰기 ', i + 17), "정재호", now(), null),
                                                                           (null, concat('게시글 쓰기 ', i + 18), "정재호", now(), null),
                                                                           (null, concat('게시글 쓰기 ', i + 19), "정재호", now(), null);
set i = i + 20;
end while;
end $$

-- call loopinsert();
create database testdb;
use testdb;

######### 가입회원 테이블 시작 #########
create table member (
num					int 			unique 		auto_increment,
uId 					char(30) 											,
uPw 					char(30) 	not null								,
uName 				char(30) 	not null								,
uEmail 				char(100) 	not null								,
gender 				char(2) 											,
uBirthday 			char(10) 											,
uZipcode 			char(8) 											,
uAddr				char(100) 											,
uHobby				char(10) 											,
uJob					char(30) 											,
joinTM 				datetime											,
constraint 	primary key(uId)
);

desc member;
######### 가입회원 테이블 끝 #########

######### 회원가입용 우편번호 테이블 시작 #########
create table tblZipcode(
zipcode 				char(7),
area1 				char(10),
area2 				char(20),
area3 				char(40),
area4 				char(20)
);

desc tblzipcode;
######### 회원가입용 우편번호 테이블 끝 #########

######### 게시판 테이블 시작 #########
create table tblBoard (
    num          int                    auto_increment,
    uId 			char(30) 			not null,				
    uName		char(30)			not null,
    subject     char(50)			not null,
    content		text					null		,    
    pos			int					not null,
    ref			int					not null,
    depth		int					not null,
    regTM		datetime			not null,
    ip				char(15)			null,    
    readCnt		int				not null,
    fileName	char(50)			null,
    fileSize		int					null,
    delCheck	varchar(10) default 'N',
    constraint		primary key(num),
    foreign key(uId) references member(uId) on delete cascade
);

desc tblboard;

select * from tblBoard order by num desc limit 0, 10;
######### 게시판 테이블 끝 #########

######### 관리자 테이블 시작 #########
create table admin (
num               int     unique   auto_increment,   #고유번호#
uId                char(30)                                     ,   #아이디#
uPw                char(30)    not null                        ,   #비밀번호#
uName             char(30)    not null                        ,   #관리자 이름#
uEmail             char(100)    not null                        ,   #이메일#
kind               char(3)                                ,   #관리자 구분#
joinTM             datetime                                 ,   #등록일#
constraint    primary key(uId)
);
select * from admin;
desc admin;
######### 관리자 테이블 시작 #########

######### 공지게시판 테이블 시작 #########
create table NoticeBoard (
    num          int                    auto_increment,
    uId          char(30)          not null,            
    uName      char(30)         not null,
    subject     char(50)         not null,
    content      text               null      ,    
    regTM      datetime         not null,
    readCnt      int            default 0 not null,
    fileName   char(50)         null,
    fileSize      int           default 0 null,
    constraint      primary key(num)
);

desc noticeboard;
######### 공지게시판 테이블 끝 #########

############   댓글 테이블 시작    #############
create table comments(
   num int auto_increment, # 댓글 고유번호 #
    content text , # 댓글 내용 #
	regdate datetime not null,  #작성 날짜#
    pos int not null, 
    ref int not null, 
    depth int not null, 
   delcheck char(1) default 'N', # 삭제 여부 #
   boardNo int , # 게시판번호 #
	uId char(30) ,# 작성자 회원번호 #
    kind int default 0,
    primary key (num),
    foreign key(boardNo) references tblBoard(num) on delete cascade,
	foreign key(uId) references member(uId) on delete cascade
);

desc comments;
############   댓글 테이블 끝    #############


############ ID 중복 체크용 데이터 입력  #############
insert into member (uId, uPw, uName, uEmail)   
values ('tester01', '1234', '테스터01', 'tester01@google.com' );
insert into member (uId, uPw, uName, uEmail)   
values ('you', '1234', '유재석', 'you@naver.com' );
insert into member (uId, uPw, uName, uEmail)   
values ('lee', '1234', '이미주', 'lee@daum.net' );

###### 공지 게시판 글쓰기 ######
insert into noticeboard(uId, uName, subject, content, regTM, readCnt , fileName , fileSize)
values('admin', '관리자', '공지사항', '첫 공지사항입니다.',now(),0,'파일',0);

###### 관리자 계정 생성 ######
insert into admin(uId,uPw,uName,uEmail,kind,joinTM)
values('admin','1234','관리자','admin@naver.com','sup',now());
insert into admin(uId,uPw,uName,uEmail,kind,joinTM)
values('adminSub','1234','서브관리자','adminSub@naver.com','sub',now());

###### 댓글 쓰기 ######
insert into comments(content, regdate, pos, ref, depth, boardNo, uId, kind)
values('댓글테이블 제목',now(), 0, 0, 0, 1, 'tester01', 0);

############ 자유 게시판 글쓰기 ############
insert into tblBoard(uId, uName, subject, content, pos, ref, depth, regTM, ip, fileName, filesize)
values();


################# select ##################
select * from member;
select * from tblBoard;
select * from admin;
select * from noticeboard;
select * from comments;
select * from tblzipcode;

##########################################
delete from member where num=3;

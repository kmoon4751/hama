--테이블 삭제
drop table member;

--시퀀스삭제
drop sequence member_member_id_seq;

-------
--회원
-------
create table member (
    member_id   number(10),         --내부 관리 아이디
    work_member_id   number(10),         --내부 관리 아이디
    email       varchar2(50),   --로긴 아이디
    passwd      varchar2(12),   --로긴 비밀번호
    tel         varchar2(20),   --연락처 ex)010-1234-5678
    nickname    varchar2(30),   --별칭
    hobby       varchar2(300),  --취미
    gubun       varchar2(11)   default '주마', --회원구분 (일반,우수,관리자..)
    pic         number(10),            --사진
    cdate       timestamp default systimestamp,         --생성일시
    udate       timestamp default systimestamp          --수정일시
);
--기본키생성
alter table member add Constraint member_member_id_pk primary key (member_id);

--제약조건
alter table member modify email constraint member_email_uk unique;
alter table member modify email constraint member_email_nn not null;
alter table member add constraint member_gubun_ck check (gubun in ('주마','하마'));

--시퀀스
create sequence member_member_id_seq;

--샘플데이터 of member
insert into member (member_id,work_member_id,email,passwd,tel,nickname,hobby,gubun)
    values(member_member_id_seq.nextval,1, 'test1@kh.com', '1234', '010-1111-1111','테스터1','안녕하세요', '주마');
insert into member (member_id,email,passwd,tel,nickname,hobby,gubun)
    values(member_member_id_seq.nextval, 'test2@kh.com', '1234', '010-1111-1112','테스터2','안녕하세요', '주마');
insert into member (member_id,email,passwd,tel,nickname,hobby,gubun)
    values(member_member_id_seq.nextval, 'admin1@kh.com', '1234','010-1111-1113','관리자1', '안녕하세요','하마');
insert into member (member_id,email,passwd,tel,nickname,hobby,gubun)
    values(member_member_id_seq.nextval, 'admin2@kh.com', '1234','010-1111-1114','관리자2', '안녕하세요','하마');
insert into member (member_id,work_member_id,email,passwd,tel,nickname,hobby,gubun)
    values(member_member_id_seq.nextval,1, 'test6@kh.com', '1234', '010-1111-1111','테스터3','안녕하세요', '주마');    
select * from member;
commit;
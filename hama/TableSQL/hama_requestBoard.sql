--테이블 삭제
drop table requestBoard;

--시퀀스삭제
drop sequence requestB_requestB_id_seq;

-------
--회원
-------
create table requestBoard (
    board_id        number(10),         --내부 관리 아이디
    member_id     number(10),       -- 올린맴버아이디
    category        varchar2(60) not null,   --카테고리
    area              varchar2(40) not null,   --지역
    hope_date     varchar2(40) not null,   --희망날짜
    hope_text       varchar2(300),  --희망문구
    cdate            timestamp default systimestamp,         --생성일시
    udate            timestamp default systimestamp          --수정일시
);
--기본키생성
alter table requestBoard add Constraint requestBoard_requestBoard_id_pk primary key (board_id);

--외래키생성
alter table requestBoard add constraint request_member_id_fk foreign key(member_id) references member(member_id);

--시퀀스
create sequence requestB_requestB_id_seq;

--샘플데이터 of member
insert into requestBoard (board_id,member_id,category,area,hope_date,hope_text)
    values(requestB_requestB_id_seq.nextval, 1, '디자인/개발  웹 디자인', '울산/신정동','2023-11-20','안녕하세요 빠르게해주실분구합니다');
insert into requestBoard (board_id,member_id,category,area,hope_date,hope_text)
    values(requestB_requestB_id_seq.nextval, 2,'청소/이사 가정집 청소', '부산/영도구','협의 가능해요.','안녕하세요 테스트입니다');
insert into requestBoard (board_id,member_id,category,area,hope_date,hope_text)
    values(requestB_requestB_id_seq.nextval, 3,'레슨/수학 과외', '경상/통영시','가능한 빨리 하고싶어요.','안녕하세요 이것은테스트입니다');
insert into requestBoard (board_id,member_id,category,area,hope_date,hope_text)
    values(requestB_requestB_id_seq.nextval, 4,'도우미/배달 도우미', '경상/창원시','2024-01-20','안녕하세요 테스트트');

select board_id,member_id,category,area,hope_date,hope_text from requestBoard;
select board_id,member_id,category,area,hope_date,hope_text,to_char(cdate,'YY/MM/DD')"cdate"
  from requestboard
 where member_id = 1;
commit;
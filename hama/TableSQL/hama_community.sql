--테이블 삭제
drop table community;

--시퀀스삭제
drop sequence community_comu_post_id_seq;


create table community (
    comu_post_id        number(10),                         -- 게시글 아이디(pk)
    comu_gubun     	varchar2(20),                       -- '궁금'/'얼마'/'모여'
    title               varchar2(1000)not null,              -- 글 제목
    content             varchar2(4000)not null,             -- 글 본문
    member_id           number(10),                         -- 올린멤버아이디 /fk
    area                varchar2(40),                       -- 지역
    board_pic            blob,                              --게시판 사진
    cdate               timestamp default systimestamp,         --생성일시
    udate               timestamp default systimestamp          --수정일시
 ); 
 
--기본키생성
alter table community add Constraint community_comu_post_id_pk primary key (comu_post_id);

--제약조건
alter table community add constraint community_comu_gubun_ck check (comu_gubun in ('궁금해요','얼마예요','모여봐요'));

--외래키생성
alter table community add constraint community_member_id_fk foreign key (member_id) references member(member_id);

--시퀀스
create sequence community_comu_post_id_seq;

--더미
insert into community (comu_post_id,comu_gubun,title,content,member_id,area)
    values(community_comu_post_id_seq.nextval, '궁금해요', '이건 어디서 구해요?', '이 강아지가 입고있는 옷 어디서 구해요?',1,'서울');
insert into community (comu_post_id,comu_gubun,title,content,member_id,area)
    values(community_comu_post_id_seq.nextval, '얼마예요', '이건 얼마인가요?', '이 강아지가 입고 있는 옷 얼마인가요?',2,'울산');
insert into community (comu_post_id,comu_gubun,title,content,member_id,area)
    values(community_comu_post_id_seq.nextval, '모여봐요', '같이 스터디 하실분?','같이 웹개발 스터디 하실 분?',3, '제주도');
    
select * from community;
commit;
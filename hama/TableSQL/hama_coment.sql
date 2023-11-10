--테이블 삭제
drop table coment;

--시퀀스삭제
drop sequence coment_coment_id_seq;

create table coment (
    coment_id      number(10), -- pk,
    estimate_id           number(10), -- requestBoard 테이블의 외래키,
    member_id        number(10), -- member 테이블의 외래키,       -- 올린맴버아이디
    coment_text     varchar2(200),
    cdate               timestamp default systimestamp         --생성일시
 );

--기본키생성
alter table coment add Constraint coment_coment_id_pk primary key (coment_id);

--외래키생성
alter table coment add constraint coment_estimate_id_fk foreign key(estimate_id)
references estimate(estimate_id);
alter table coment add constraint coment_member_id_fk foreign key(member_id)
references member(member_id);

--시퀀스
create sequence coment_coment_id_seq;

--샘플데이터 of estimate
insert into coment (coment_id,estimate_id, member_id, coment_text)
    values(coment_coment_id_seq.nextval, 1, 1, '안녕하세요!');
insert into coment (coment_id,estimate_id, member_id, coment_text)
    values(coment_coment_id_seq.nextval, 1, 2, '안녕하세요 누구누구입니다');
insert into coment (coment_id,estimate_id, member_id, coment_text)
    values(coment_coment_id_seq.nextval, 2, 2,'반갑습니다!');

select * from coment;
commit;
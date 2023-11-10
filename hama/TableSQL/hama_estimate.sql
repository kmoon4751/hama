--테이블 삭제
drop table estimate;

--시퀀스삭제
drop sequence estimate_estimate_id_seq;

create table estimate (
    estimate_id      number(10), -- pk,
    board_id         number(10), -- requestBoard 테이블의 외래키,
    member_id        number(10), -- member 테이블의 외래키,       -- 올린맴버아이디
    work_member_id   number(10),     -- 하마의 아이디
    esti_gubun       varchar2(10) not null, --견적서 작성의 총 비용과 시간 당 구분
    esti_price       varchar2(20) not null,   --견적서 작성의 가격
    esti_text        varchar2(300),
    esti_file        number(10),         --견적서 작성의 파일
    cdate               timestamp default systimestamp,         --생성일시
    udate               timestamp default systimestamp          --수정일시
 ); 
 
--기본키생성
alter table estimate add Constraint estimate_estimate_id_pk primary key (estimate_id);

--외래키생성
alter table estimate add constraint estimate_board_id_fk foreign key(board_id) references requestBoard(board_id);

--시퀀스
create sequence estimate_estimate_id_seq;

--샘플데이터 of estimate
insert into estimate (estimate_id, board_id ,member_id, work_member_id, esti_gubun, esti_price, esti_text)
    values(estimate_estimate_id_seq.nextval, 1, 2, 1,'총 비용', '3,000', '잘해드리겠습니다!');
insert into estimate (estimate_id, board_id ,member_id, work_member_id, esti_gubun, esti_price, esti_text)
    values(estimate_estimate_id_seq.nextval, 2, 1, 2,'시간 당', '9,000', '걱정마세요!');

select * from estimate;
commit;
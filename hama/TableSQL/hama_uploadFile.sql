drop table uploadfile;
drop sequence uploadfile_uploadfile_id_seq;
---------
--첨부파일
---------
create table uploadfile(
    uploadfile_id   number(10),     --파일아이디 
    table_info      varchar2(20),     --참조번호(게시글번호등) ,member의 이미지, 견적서작성의 첨부파일 member냐 estimate냐 구분 하마찾기냐
    table_id        number(10),
    store_filename  varchar2(100),   --서버보관파일명
    upload_filename varchar2(100),   --업로드파일명(유저가 업로드한파일명)
    fsize           varchar2(45),   --업로드파일크기(단위byte)
    ftype           varchar2(100),   --파일유형(mimetype)
    cdate           timestamp default systimestamp -- 등록일시
);
--기본키
alter table uploadfile add constraint uploadfile_uploadfile_id_pk primary key(uploadfile_id);

--외래키


--제약조건
alter table uploadfile modify table_id constraint uploadfile_table_id_nn not null;
alter table uploadfile modify table_info constraint uploadfile_table_info_nn not null;
alter table uploadfile modify store_filename constraint uploadfile_store_filename_nn not null;
alter table uploadfile modify upload_filename constraint uploadfile_upload_filename_nn not null;
alter table uploadfile modify fsize constraint uploadfile_fsize_nn not null;
alter table uploadfile modify ftype constraint uploadfile_ftype_nn not null;

--시퀀스
create sequence uploadfile_uploadfile_id_seq;

commit;
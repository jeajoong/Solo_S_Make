/*
계정명       	: NBEM2_ADM
패스워드      	: nbem02
테이블스페이스  	: NBEM2_WOR, NBEM2_DAT, NBEM2_IDX(인덱스전용)
테이블데이터는 Golden에서 import 기능 이용할것.
*/


/*================================*/
/*=== 테이블 스페이스 및 유저 생성 ===*/
/*================================*/
/* SYSTEM 권한으로 진행. */
-- NBEM2_WOK 테이블스페이스 생성 (2GB)
CREATE TABLESPACE NBEM2_WOK
DATAFILE 'C:\app\jeajoong\oradata\orcl\NBEM2_WOK.dbf'
SIZE 2048M
AUTOEXTEND ON
NEXT 4M MAXSIZE UNLIMITED
LOGGING PERMANENT EXTENT MANAGEMENT LOCAL AUTOALLOCATE
BLOCKSIZE 8K
SEGMENT SPACE MANAGEMENT MANUAL
FLASHBACK ON;

-- NBEM2_DAT 테이블스페이스 생성 (2GB)
CREATE TABLESPACE NBEM2_DAT
DATAFILE 'C:\app\jeajoong\oradata\orcl\NBEM2_DAT.dbf'
SIZE 2048M
AUTOEXTEND ON
NEXT 4M MAXSIZE UNLIMITED
LOGGING PERMANENT EXTENT MANAGEMENT LOCAL AUTOALLOCATE
BLOCKSIZE 8K
SEGMENT SPACE MANAGEMENT MANUAL
FLASHBACK ON;

-- NBEM2_IDX 인덱스 테이블스페이스 생성 (1GB)
CREATE TABLESPACE NBEM2_IDX
DATAFILE 'C:\app\jeajoong\oradata\orcl\NBEM2_IDX.dbf'
SIZE 1024M
AUTOEXTEND ON
NEXT 4M MAXSIZE UNLIMITED
LOGGING PERMANENT EXTENT MANAGEMENT LOCAL AUTOALLOCATE
BLOCKSIZE 8K
SEGMENT SPACE MANAGEMENT MANUAL
FLASHBACK ON;

-- nbem2_adm 유저 생성 (기본 테이블 스페이스 nbem2_wok
CREATE USER NBEM2_ADM IDENTIFIED BY nbem02 DEFAULT TABLESPACE NBEM2_WOK;

-- 권한주기
GRANT CONNECT, RESOURCE, DBA TO NBEM2_ADM;
GRANT CREATE SESSION TO NBEM2_ADM;
GRANT CREATE TABLE TO NBEM2_ADM;


/*================================*/
/*===    테이블 생성하기(총 10개)    ===*/
/*================================*/
/* NBEM2_ADM 계정으로 진행.*/

-- ENT_ENGY_BASIS
ALTER TABLE NBEM2_ADM.ENT_ENGY_BASIS
 DROP PRIMARY KEY CASCADE;

DROP TABLE NBEM2_ADM.ENT_ENGY_BASIS CASCADE CONSTRAINTS;

CREATE TABLE NBEM2_ADM.ENT_ENGY_BASIS
(
  ENGY_SPLY_KIK_CD   CHAR(10 BYTE)              NOT NULL,
  ENGY_ESNCNO        VARCHAR2(20 BYTE)          NOT NULL,
  ENGY_KIND_CD       CHAR(2 BYTE),
  SIGUNGU_CD         VARCHAR2(5 BYTE),
  BJDONG_CD          VARCHAR2(5 BYTE),
  HJDONG_CD          VARCHAR2(3 BYTE),
  SIDO_NM            VARCHAR2(100 BYTE),
  SIGUNGU_NM         VARCHAR2(100 BYTE),
  BJDONG_NM          VARCHAR2(100 BYTE),
  HJDONG_NM          VARCHAR2(100 BYTE),
  PLAT_GB_CD         VARCHAR2(1 BYTE),
  BUN                VARCHAR2(4 BYTE),
  JI                 VARCHAR2(4 BYTE),
  BUNJI              VARCHAR2(20 BYTE),
  BLD_NM             VARCHAR2(200 BYTE),
  DONG_NM            VARCHAR2(200 BYTE),
  FLR_NM             VARCHAR2(200 BYTE),
  HO_NM              VARCHAR2(200 BYTE),
  DONG_CD            CHAR(4 BYTE),
  FLR_CD             CHAR(4 BYTE),
  HO_CD              CHAR(4 BYTE),
  OFFCNM_NM          VARCHAR2(100 BYTE),
  REL_ADDR           VARCHAR2(500 BYTE),
  ENTIR_ADDR         VARCHAR2(500 BYTE),
  NA_ROAD_CD         VARCHAR2(12 BYTE),
  NA_BJDONG_CD       VARCHAR2(5 BYTE),
  NA_GRND_UGRND_CD   VARCHAR2(1 BYTE),
  NA_MAIN_BUN        VARCHAR2(5 BYTE),
  NA_SUB_BUN         VARCHAR2(5 BYTE),
  NA_BLD_NM          VARCHAR2(200 BYTE),
  NA_DETL_BLD_NM     VARCHAR2(500 BYTE),
  MAINT_RSLT_CD      CHAR(2 BYTE),
  MAINT_FIN_DTIME    VARCHAR2(14 BYTE),
  MATCH_RSLT_CD      CHAR(2 BYTE),
  WORK_FIN_YN        VARCHAR2(1 BYTE),
  WORKR_ID           VARCHAR2(50 BYTE),
  REG_DAY            VARCHAR2(8 BYTE),
  AUTO_MATCH_CD      VARCHAR2(2 BYTE),
  AUTO_MATCH_YN      VARCHAR2(1 BYTE),
  AUTO_MATCH_DTIME   VARCHAR2(14 BYTE),
  MATCH_FIN_YN       VARCHAR2(1 BYTE),
  TRNSC_FIN_DTIME    VARCHAR2(14 BYTE),
  OPER_SRVR_RFLC_YN  VARCHAR2(1 BYTE),
  USE_PURPS_CD       VARCHAR2(6 BYTE)
)
TABLESPACE NBEM2_DAT
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING
NOCOMPRESS
NOCACHE
PARALLEL ( DEGREE 2 INSTANCES 1 )
MONITORING;

COMMENT ON TABLE NBEM2_ADM.ENT_ENGY_BASIS IS '통합_에너지_기본정보: 에너지 수용가 정보를 관리';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.ENGY_SPLY_KIK_CD IS '에너지_공급_기관_코드';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.ENGY_ESNCNO IS '에너지_고유번호';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.ENGY_KIND_CD IS '에너지_종류_코드';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.SIGUNGU_CD IS '시군구_코드';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.BJDONG_CD IS '법정동_코드';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.HJDONG_CD IS '행정동_코드';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.SIDO_NM IS '시도_명';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.SIGUNGU_NM IS '시군구_명';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.BJDONG_NM IS '법정동_명';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.HJDONG_NM IS '행정동_명';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.PLAT_GB_CD IS '대지_구분_코드';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.BUN IS '번';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.JI IS '지';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.BUNJI IS '번지';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.BLD_NM IS '건물_명';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.DONG_NM IS '동_명';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.FLR_NM IS '층_명';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.HO_NM IS '호_명';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.DONG_CD IS '동_코드';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.FLR_CD IS '층_코드';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.HO_CD IS '호_코드';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.OFFCNM_NM IS '상호_명';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.REL_ADDR IS '관련_주소';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.ENTIR_ADDR IS '전체_주소';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.NA_ROAD_CD IS '새주소_도로_코드';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.NA_BJDONG_CD IS '새주소_법정동_코드';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.NA_GRND_UGRND_CD IS '새주소_지상_지하_코드';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.NA_MAIN_BUN IS '새주소_본번';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.NA_SUB_BUN IS '새주소_부번';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.NA_BLD_NM IS '새주소_건물_명';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.NA_DETL_BLD_NM IS '새주소_상세_건물_명';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.MAINT_RSLT_CD IS '정비_결과_코드';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.MAINT_FIN_DTIME IS '정비_완료_일시';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.MATCH_RSLT_CD IS '매칭_결과_코드';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.WORK_FIN_YN IS '작업_완료_여부';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.WORKR_ID IS '작업자_아이디';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.REG_DAY IS '등록_일자';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.AUTO_MATCH_CD IS '자동_매칭_코드';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.AUTO_MATCH_YN IS '자동_매칭_여부';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.AUTO_MATCH_DTIME IS '자동_매칭_일시';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.MATCH_FIN_YN IS '매칭_완료_여부';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.TRNSC_FIN_DTIME IS '이관_완료_일시';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.OPER_SRVR_RFLC_YN IS '운영_서버_반영_여부';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_BASIS.USE_PURPS_CD IS '사용_용도_코드';


CREATE UNIQUE INDEX NBEM2_ADM.IU_ENT_ENGY_BASIS_01 ON NBEM2_ADM.ENT_ENGY_BASIS
(ENGY_SPLY_KIK_CD, ENGY_ESNCNO)
LOGGING
TABLESPACE NBEM2_IDX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE INDEX NBEM2_ADM.IX_ENT_ENGY_BASIS_01 ON NBEM2_ADM.ENT_ENGY_BASIS
(SIGUNGU_CD, BJDONG_CD, PLAT_GB_CD, BUN, JI)
LOGGING
TABLESPACE NBEM2_IDX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE INDEX NBEM2_ADM.IX_ENT_ENGY_BASIS_02 ON NBEM2_ADM.ENT_ENGY_BASIS
(ENGY_SPLY_KIK_CD, ENGY_ESNCNO, ENTIR_ADDR)
LOGGING
TABLESPACE NBEM2_IDX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE INDEX NBEM2_ADM.IX_ENT_ENGY_BASIS_03 ON NBEM2_ADM.ENT_ENGY_BASIS
(SIDO_NM, SIGUNGU_NM, BJDONG_NM, PLAT_GB_CD, BUN,
JI)
LOGGING
TABLESPACE NBEM2_IDX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE INDEX NBEM2_ADM.IX_ENT_ENGY_BASIS_04 ON NBEM2_ADM.ENT_ENGY_BASIS
(ENGY_KIND_CD)
LOGGING
TABLESPACE NBEM2_IDX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


DROP SYNONYM NBEM2_KAPT.ENT_ENGY_BASIS;

CREATE SYNONYM NBEM2_KAPT.ENT_ENGY_BASIS FOR NBEM2_ADM.ENT_ENGY_BASIS;


ALTER TABLE NBEM2_ADM.ENT_ENGY_BASIS ADD (
  CONSTRAINT PK_ENT_ENGY_BASIS
  PRIMARY KEY
  (ENGY_SPLY_KIK_CD, ENGY_ESNCNO)
  USING INDEX NBEM2_ADM.IU_ENT_ENGY_BASIS_01);

GRANT SELECT ON NBEM2_ADM.ENT_ENGY_BASIS TO PUBLIC;

--ENT_ENGY_USE
DROP TABLE NBEM2_ADM.ENT_ENGY_USE CASCADE CONSTRAINTS;

CREATE TABLE NBEM2_ADM.ENT_ENGY_USE
(
  ENGY_SPLY_KIK_CD  CHAR(10 BYTE)               NOT NULL,
  ENGY_ESNCNO       VARCHAR2(20 BYTE)           NOT NULL,
  USE_YM            VARCHAR2(6 BYTE)            NOT NULL,
  USE_QTY_SEQNO     NUMBER(3)                   NOT NULL,
  USE_STRT_DAY      VARCHAR2(8 BYTE),
  USE_END_DAY       VARCHAR2(8 BYTE),
  USE_PURPS_CD      VARCHAR2(6 BYTE),
  USE_QTY           NUMBER(29,9),
  UNIT_CD           VARCHAR2(2 BYTE),
  TRNSC_FIN_DTIME   VARCHAR2(14 BYTE),
  SIDO_CD           VARCHAR2(2 BYTE)
)
TABLESPACE NBEM2_DAT
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          16K
           )
LOGGING
PARTITION BY RANGE (USE_YM)
(
  PARTITION P_YM_201001 VALUES LESS THAN ('201002')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201002 VALUES LESS THAN ('201003')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201003 VALUES LESS THAN ('201004')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201004 VALUES LESS THAN ('201005')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201005 VALUES LESS THAN ('201006')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201006 VALUES LESS THAN ('201007')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201007 VALUES LESS THAN ('201008')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201008 VALUES LESS THAN ('201009')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201009 VALUES LESS THAN ('201010')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201010 VALUES LESS THAN ('201011')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201011 VALUES LESS THAN ('201012')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201012 VALUES LESS THAN ('201101')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201101 VALUES LESS THAN ('201102')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201102 VALUES LESS THAN ('201103')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201103 VALUES LESS THAN ('201104')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201104 VALUES LESS THAN ('201105')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201105 VALUES LESS THAN ('201106')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201106 VALUES LESS THAN ('201107')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201107 VALUES LESS THAN ('201108')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201108 VALUES LESS THAN ('201109')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201109 VALUES LESS THAN ('201110')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201110 VALUES LESS THAN ('201111')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201111 VALUES LESS THAN ('201112')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201112 VALUES LESS THAN ('201201')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201201 VALUES LESS THAN ('201202')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201202 VALUES LESS THAN ('201203')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201203 VALUES LESS THAN ('201204')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201204 VALUES LESS THAN ('201205')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201205 VALUES LESS THAN ('201206')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201206 VALUES LESS THAN ('201207')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201207 VALUES LESS THAN ('201208')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201208 VALUES LESS THAN ('201209')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201209 VALUES LESS THAN ('201210')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201210 VALUES LESS THAN ('201211')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201211 VALUES LESS THAN ('201212')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201212 VALUES LESS THAN ('201301')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201301 VALUES LESS THAN ('201302')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201302 VALUES LESS THAN ('201303')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201303 VALUES LESS THAN ('201304')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201304 VALUES LESS THAN ('201305')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201305 VALUES LESS THAN ('201306')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201306 VALUES LESS THAN ('201307')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201307 VALUES LESS THAN ('201308')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201308 VALUES LESS THAN ('201309')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201309 VALUES LESS THAN ('201310')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201310 VALUES LESS THAN ('201311')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201311 VALUES LESS THAN ('201312')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201312 VALUES LESS THAN ('201401')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201401 VALUES LESS THAN ('201402')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201402 VALUES LESS THAN ('201403')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201403 VALUES LESS THAN ('201404')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201404 VALUES LESS THAN ('201405')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201405 VALUES LESS THAN ('201406')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201406 VALUES LESS THAN ('201407')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201407 VALUES LESS THAN ('201408')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201408 VALUES LESS THAN ('201409')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201409 VALUES LESS THAN ('201410')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201410 VALUES LESS THAN ('201411')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201411 VALUES LESS THAN ('201412')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201412 VALUES LESS THAN ('201501')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201501 VALUES LESS THAN ('201502')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201502 VALUES LESS THAN ('201503')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201503 VALUES LESS THAN ('201504')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201504 VALUES LESS THAN ('201505')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201505 VALUES LESS THAN ('201506')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201506 VALUES LESS THAN ('201507')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201507 VALUES LESS THAN ('201508')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201508 VALUES LESS THAN ('201509')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201509 VALUES LESS THAN ('201510')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201510 VALUES LESS THAN ('201511')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201511 VALUES LESS THAN ('201512')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201512 VALUES LESS THAN ('201601')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201601 VALUES LESS THAN ('201602')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201602 VALUES LESS THAN ('201603')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201603 VALUES LESS THAN ('201604')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201604 VALUES LESS THAN ('201605')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201605 VALUES LESS THAN ('201606')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201606 VALUES LESS THAN ('201607')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201607 VALUES LESS THAN ('201608')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201608 VALUES LESS THAN ('201609')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201609 VALUES LESS THAN ('201610')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201610 VALUES LESS THAN ('201611')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201611 VALUES LESS THAN ('201612')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201612 VALUES LESS THAN ('201701')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201701 VALUES LESS THAN ('201702')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201702 VALUES LESS THAN ('201703')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201703 VALUES LESS THAN ('201704')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201704 VALUES LESS THAN ('201705')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201705 VALUES LESS THAN ('201706')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201706 VALUES LESS THAN ('201707')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201707 VALUES LESS THAN ('201708')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201708 VALUES LESS THAN ('201709')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201709 VALUES LESS THAN ('201710')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201710 VALUES LESS THAN ('201711')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201711 VALUES LESS THAN ('201712')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201712 VALUES LESS THAN ('201801')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201801 VALUES LESS THAN ('201802')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201802 VALUES LESS THAN ('201803')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201803 VALUES LESS THAN ('201804')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201804 VALUES LESS THAN ('201805')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201805 VALUES LESS THAN ('201806')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201806 VALUES LESS THAN ('201807')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201807 VALUES LESS THAN ('201808')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201808 VALUES LESS THAN ('201809')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201809 VALUES LESS THAN ('201810')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201810 VALUES LESS THAN ('201811')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201811 VALUES LESS THAN ('201812')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201812 VALUES LESS THAN ('201901')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201901 VALUES LESS THAN ('201902')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201902 VALUES LESS THAN ('201903')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201903 VALUES LESS THAN ('201904')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201904 VALUES LESS THAN ('201905')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201905 VALUES LESS THAN ('201906')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201906 VALUES LESS THAN ('201907')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201907 VALUES LESS THAN ('201908')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201908 VALUES LESS THAN ('201909')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201909 VALUES LESS THAN ('201910')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201910 VALUES LESS THAN ('201911')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201911 VALUES LESS THAN ('201912')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201912 VALUES LESS THAN ('202001')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_202001 VALUES LESS THAN ('202002')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_202002 VALUES LESS THAN ('202003')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_202003 VALUES LESS THAN ('202004')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_202004 VALUES LESS THAN ('202005')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_202005 VALUES LESS THAN ('202006')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_202006 VALUES LESS THAN ('202007')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_202007 VALUES LESS THAN ('202008')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_202008 VALUES LESS THAN ('202009')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_202009 VALUES LESS THAN ('202010')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_202010 VALUES LESS THAN ('202011')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_202011 VALUES LESS THAN ('202012')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_202012 VALUES LESS THAN ('202101')
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_MAX VALUES LESS THAN (MAXVALUE)
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          16K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               )
)
NOCOMPRESS
NOCACHE
NOPARALLEL
MONITORING;

COMMENT ON TABLE NBEM2_ADM.ENT_ENGY_USE IS '통합_에너지_사용: 매칭 완료된 수용가의 에너지 공급기관별, 월별 에너지 사용 현황 테이블';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_USE.ENGY_SPLY_KIK_CD IS '에너지_공급_기관_코드: 공통코드(CMC_COMM_CD_MDM)의 대분류코드 BE022 에 해당 에너지종류(2) + 공급기관(3) + 지사(3) + 사무소 혹은 단지(2)';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_USE.ENGY_ESNCNO IS '에너지_고유번호: 2개의 계량기를 가진 경우 서로 다른 고유번호를 가진다.';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_USE.USE_YM IS '사용_년월';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_USE.USE_QTY_SEQNO IS '사용_량_일련번호:';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_USE.USE_STRT_DAY IS '사용_시작_일자';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_USE.USE_END_DAY IS '사용_종료_일자';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_USE.USE_PURPS_CD IS '사용_용도_코드';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_USE.USE_QTY IS '사용_량';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_USE.UNIT_CD IS '단위_코드: 01(KWh), 02(Nm3), 03(Gcal), 04(MWh), 05(Ton), 06(Mcal), 07(Kcal), 08(MJ), 09(L), 10(m3)';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_USE.TRNSC_FIN_DTIME IS '이관_완료_일시';

COMMENT ON COLUMN NBEM2_ADM.ENT_ENGY_USE.SIDO_CD IS '시도_코드';


CREATE UNIQUE INDEX NBEM2_ADM.IU_ENT_ENGY_USE_01 ON NBEM2_ADM.ENT_ENGY_USE
(ENGY_SPLY_KIK_CD, ENGY_ESNCNO, USE_YM, USE_QTY_SEQNO)
  PCTFREE    10
  INITRANS   2
  MAXTRANS   255
LOCAL (
  PARTITION P_YM_201001
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201002
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201003
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201004
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201005
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201006
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201007
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201008
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201009
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201010
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201011
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201012
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201101
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201102
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201103
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201104
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201105
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201106
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201107
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201108
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201109
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201110
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201111
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201112
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201201
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201202
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201203
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201204
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201205
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201206
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201207
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201208
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201209
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201210
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201211
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201212
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201301
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201302
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201303
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201304
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201305
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201306
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201307
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201308
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201309
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201310
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201311
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201312
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201401
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201402
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201403
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201404
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201405
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201406
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201407
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201408
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201409
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201410
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201411
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201412
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201501
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201502
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201503
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201504
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201505
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201506
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201507
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201508
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201509
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201510
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201511
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201512
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201601
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201602
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201603
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201604
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201605
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201606
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201607
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201608
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201609
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201610
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201611
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201612
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201701
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201702
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201703
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201704
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201705
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201706
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201707
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201708
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201709
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201710
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201711
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201712
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201801
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201802
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201803
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201804
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201805
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201806
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201807
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201808
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201809
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201810
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201811
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201812
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_IDX
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201901
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201902
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201903
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201904
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201905
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201906
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201907
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201908
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201909
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201910
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201911
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_201912
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_202001
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_202002
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_202003
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_202004
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_202005
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_202006
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_202007
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_202008
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_202009
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_202010
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_202011
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_202012
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_BAK
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               ),
  PARTITION P_YM_MAX
    LOGGING
    NOCOMPRESS
    TABLESPACE NBEM2_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                NEXT             1M
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                BUFFER_POOL      DEFAULT
               )
)
NOPARALLEL;


DROP SYNONYM NBEM2_KAPT.ENT_ENGY_USE;

CREATE SYNONYM NBEM2_KAPT.ENT_ENGY_USE FOR NBEM2_ADM.ENT_ENGY_USE;


DROP SYNONYM NBEM2_WOK.ENT_ENGY_USE;

CREATE SYNONYM NBEM2_WOK.ENT_ENGY_USE FOR NBEM2_ADM.ENT_ENGY_USE;


GRANT SELECT ON NBEM2_ADM.ENT_ENGY_USE TO NBEM2_WOK;

GRANT SELECT ON NBEM2_ADM.ENT_ENGY_USE TO PUBLIC;

-- CMC_BJDONG_MGM
ALTER TABLE NBEM2_ADM.CMC_BJDONG_MGM
 DROP PRIMARY KEY CASCADE;

DROP TABLE NBEM2_ADM.CMC_BJDONG_MGM CASCADE CONSTRAINTS;

CREATE TABLE NBEM2_ADM.CMC_BJDONG_MGM
(
  SIGUNGU_CD        VARCHAR2(5 BYTE)            NOT NULL,
  BJDONG_CD         VARCHAR2(5 BYTE)            NOT NULL,
  HJDONG_CD         VARCHAR2(3 BYTE)            NOT NULL,
  SIDO_NM           VARCHAR2(50 BYTE),
  SIGUNGU_NM        VARCHAR2(50 BYTE),
  BJDONG_NM         VARCHAR2(50 BYTE),
  HJDONG_NM         VARCHAR2(50 BYTE),
  HJDONG_EXT_NM     VARCHAR2(50 BYTE),
  CHANG_SIGUNGU_CD  VARCHAR2(5 BYTE),
  CHANG_BJDONG_CD   VARCHAR2(5 BYTE),
  CHANG_HJDONG_CD   VARCHAR2(3 BYTE),
  APPLY_STRT_DAY    VARCHAR2(8 BYTE),
  APPLY_EXP_DAY     VARCHAR2(8 BYTE)            NOT NULL
)
TABLESPACE NBEM2_DAT
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          9M
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING
NOCOMPRESS
NOCACHE
NOPARALLEL
MONITORING;

COMMENT ON TABLE NBEM2_ADM.CMC_BJDONG_MGM IS '공통_법정동_관리';

COMMENT ON COLUMN NBEM2_ADM.CMC_BJDONG_MGM.SIGUNGU_CD IS '시군구_코드';

COMMENT ON COLUMN NBEM2_ADM.CMC_BJDONG_MGM.BJDONG_CD IS '법정동_코드';

COMMENT ON COLUMN NBEM2_ADM.CMC_BJDONG_MGM.HJDONG_CD IS '행정동_코드';

COMMENT ON COLUMN NBEM2_ADM.CMC_BJDONG_MGM.SIDO_NM IS '시도_명';

COMMENT ON COLUMN NBEM2_ADM.CMC_BJDONG_MGM.SIGUNGU_NM IS '시군구_명';

COMMENT ON COLUMN NBEM2_ADM.CMC_BJDONG_MGM.BJDONG_NM IS '법정동_명';

COMMENT ON COLUMN NBEM2_ADM.CMC_BJDONG_MGM.HJDONG_NM IS '행정동_명';

COMMENT ON COLUMN NBEM2_ADM.CMC_BJDONG_MGM.HJDONG_EXT_NM IS '행정동_별도_명';

COMMENT ON COLUMN NBEM2_ADM.CMC_BJDONG_MGM.CHANG_SIGUNGU_CD IS '변경_시군구_코드';

COMMENT ON COLUMN NBEM2_ADM.CMC_BJDONG_MGM.CHANG_BJDONG_CD IS '변경_법정동_코드';

COMMENT ON COLUMN NBEM2_ADM.CMC_BJDONG_MGM.CHANG_HJDONG_CD IS '변경_행정동_코드';

COMMENT ON COLUMN NBEM2_ADM.CMC_BJDONG_MGM.APPLY_STRT_DAY IS '적용_시작_일자';

COMMENT ON COLUMN NBEM2_ADM.CMC_BJDONG_MGM.APPLY_EXP_DAY IS '적용_만료_일자';


CREATE UNIQUE INDEX NBEM2_ADM.IU_CMC_BJDONG_MGM_01 ON NBEM2_ADM.CMC_BJDONG_MGM
(SIGUNGU_CD, BJDONG_CD, HJDONG_CD, APPLY_EXP_DAY)
LOGGING
TABLESPACE NBEM2_IDX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE INDEX NBEM2_ADM.IX_CMC_BJDONG_MGM_01 ON NBEM2_ADM.CMC_BJDONG_MGM
(SIGUNGU_CD, BJDONG_CD, HJDONG_CD)
LOGGING
TABLESPACE NBEM2_IDX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE INDEX NBEM2_ADM.IX_CMC_BJDONG_MGM_02 ON NBEM2_ADM.CMC_BJDONG_MGM
(SIDO_NM, SIGUNGU_NM, BJDONG_NM, HJDONG_NM)
LOGGING
TABLESPACE NBEM2_IDX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


ALTER TABLE NBEM2_ADM.CMC_BJDONG_MGM ADD (
  CONSTRAINT IU_CMC_BJDONG_MGM_01
  PRIMARY KEY
  (SIGUNGU_CD, BJDONG_CD, HJDONG_CD, APPLY_EXP_DAY)
  USING INDEX NBEM2_ADM.IU_CMC_BJDONG_MGM_01);

GRANT SELECT ON NBEM2_ADM.CMC_BJDONG_MGM TO PUBLIC;

--CMC_DONG_CD_MGM
DROP TABLE NBEM2_ADM.CMC_DONG_CD_MGM CASCADE CONSTRAINTS;

CREATE TABLE NBEM2_ADM.CMC_DONG_CD_MGM
(
  SEQNO    NUMBER(10)                           NOT NULL,
  DONG_CD  VARCHAR2(4 BYTE),
  CD_NM    VARCHAR2(100 BYTE),
  DONG_NM  VARCHAR2(100 BYTE),
  USE_YN   VARCHAR2(1 BYTE)
)
TABLESPACE NBEM2_DAT
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          448K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING
NOCOMPRESS
NOCACHE
NOPARALLEL
MONITORING;

--CMC_ENGY_PURPS_CD
DROP TABLE NBEM2_ADM.CMC_ENGY_PURPS_CD CASCADE CONSTRAINTS;

CREATE TABLE NBEM2_ADM.CMC_ENGY_PURPS_CD
(
  USE_YM            CHAR(6 BYTE),
  ENGY_SPLY_KIK_CD  VARCHAR2(10 BYTE)           NOT NULL,
  ENGY_ESNCNO       VARCHAR2(20 BYTE)           NOT NULL,
  USE_PURPS_CD      VARCHAR2(10 BYTE),
  USE_PURPS_NM      VARCHAR2(100 BYTE)
)
TABLESPACE NBEM2_WOK
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING
NOCOMPRESS
NOCACHE
NOPARALLEL
MONITORING;

COMMENT ON TABLE NBEM2_ADM.CMC_ENGY_PURPS_CD IS '공통_에너지_사용용도_코드_관리';


CREATE UNIQUE INDEX NBEM2_ADM.IU_CMC_ENGY_PURPS_CD_01 ON NBEM2_ADM.CMC_ENGY_PURPS_CD
(ENGY_SPLY_KIK_CD, ENGY_ESNCNO)
LOGGING
TABLESPACE NBEM2_IDX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;

--CMC_ENGY_USE_PURPS_MGM
DROP TABLE NBEM2_ADM.CMC_ENGY_USE_PURPS_MGM CASCADE CONSTRAINTS;

CREATE TABLE NBEM2_ADM.CMC_ENGY_USE_PURPS_MGM
(
  ENGY_SPLY_KIK_CD  VARCHAR2(10 BYTE)           NOT NULL,
  USE_PURPS_CD      VARCHAR2(6 BYTE)            NOT NULL,
  USE_PURPS_NM      VARCHAR2(100 BYTE),
  IN_USE_PURPS_CD   VARCHAR2(6 BYTE),
  APPLY_STRT_DAY    VARCHAR2(8 BYTE),
  APPLY_EXP_DAY     VARCHAR2(8 BYTE)
)
TABLESPACE NBEM2_DAT
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          128K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING
NOCOMPRESS
NOCACHE
NOPARALLEL
MONITORING;

COMMENT ON TABLE NBEM2_ADM.CMC_ENGY_USE_PURPS_MGM IS '공통_에너지_사용_용도_관리';

COMMENT ON COLUMN NBEM2_ADM.CMC_ENGY_USE_PURPS_MGM.ENGY_SPLY_KIK_CD IS '에너지_공급_기관_코드';

COMMENT ON COLUMN NBEM2_ADM.CMC_ENGY_USE_PURPS_MGM.USE_PURPS_CD IS '사용_용도_코드';

COMMENT ON COLUMN NBEM2_ADM.CMC_ENGY_USE_PURPS_MGM.USE_PURPS_NM IS '사용_용도_명';

COMMENT ON COLUMN NBEM2_ADM.CMC_ENGY_USE_PURPS_MGM.APPLY_STRT_DAY IS '적용_시작_일자';

COMMENT ON COLUMN NBEM2_ADM.CMC_ENGY_USE_PURPS_MGM.APPLY_EXP_DAY IS '적용_만료_일자';


CREATE UNIQUE INDEX NBEM2_ADM.IU_CMC_ENGY_USE_PURPS_MGM_01 ON NBEM2_ADM.CMC_ENGY_USE_PURPS_MGM
(ENGY_SPLY_KIK_CD, USE_PURPS_CD, APPLY_EXP_DAY)
LOGGING
TABLESPACE NBEM2_IDX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE INDEX NBEM2_ADM.IX_CMC_ENGY_USE_PURPS_MGM_01 ON NBEM2_ADM.CMC_ENGY_USE_PURPS_MGM
(ENGY_SPLY_KIK_CD, USE_PURPS_CD, USE_PURPS_NM)
LOGGING
TABLESPACE NBEM2_IDX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


GRANT SELECT ON NBEM2_ADM.CMC_ENGY_USE_PURPS_MGM TO PUBLIC;

--CMC_FLR_CD_MGM
DROP TABLE NBEM2_ADM.CMC_FLR_CD_MGM CASCADE CONSTRAINTS;

CREATE TABLE NBEM2_ADM.CMC_FLR_CD_MGM
(
  SEQNO   NUMBER(10)                            NOT NULL,
  FLR_CD  VARCHAR2(4 BYTE),
  CD_NM   VARCHAR2(100 BYTE),
  FLR_NM  VARCHAR2(100 BYTE),
  USE_YN  VARCHAR2(1 BYTE)
)
TABLESPACE NBEM2_DAT
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          448K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING
NOCOMPRESS
NOCACHE
NOPARALLEL
MONITORING;

-- CMC_HO_CD_MGM

DROP TABLE NBEM2_ADM.CMC_HO_CD_MGM CASCADE CONSTRAINTS;

CREATE TABLE NBEM2_ADM.CMC_HO_CD_MGM
(
  SEQNO   NUMBER(10)                            NOT NULL,
  HO_CD   VARCHAR2(4 BYTE),
  CD_NM   VARCHAR2(100 BYTE),
  HO_NM   VARCHAR2(100 BYTE),
  USE_YN  VARCHAR2(1 BYTE)
)
TABLESPACE NBEM2_DAT
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          640K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING
NOCOMPRESS
NOCACHE
NOPARALLEL
MONITORING;

--CMC_KIK_CD_MGM
DROP TABLE NBEM2_ADM.CMC_KIK_CD_MGM CASCADE CONSTRAINTS;

CREATE TABLE NBEM2_ADM.CMC_KIK_CD_MGM
(
  ENGY_SPLY_KIK_CD  VARCHAR2(10 BYTE)           NOT NULL,
  ENGY_SPLY_KIK_NM  VARCHAR2(100 BYTE),
  ENGY_KIND_CD      VARCHAR2(2 BYTE),
  ENGY_KIND_NM      VARCHAR2(100 BYTE),
  ENGY_USE_AREA     VARCHAR2(100 BYTE),
  USE_END_YM        VARCHAR2(6 BYTE)
)
TABLESPACE NBEM2_DAT
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING
NOCOMPRESS
NOCACHE
NOPARALLEL
MONITORING;

-- CMC_NEWADDR_MGM
DROP TABLE NBEM2_ADM.CMC_NEWADDR_MGM CASCADE CONSTRAINTS;

CREATE TABLE NBEM2_ADM.CMC_NEWADDR_MGM
(
  SIGUNGU_CD        VARCHAR2(10 BYTE),
  BJDONG_CD         VARCHAR2(10 BYTE),
  SIDO_NM           VARCHAR2(40 BYTE),
  SIGUNGU_NM        VARCHAR2(40 BYTE),
  BJDONG_NM         VARCHAR2(40 BYTE),
  BJDONG_NM_1       VARCHAR2(40 BYTE),
  PLAT_GB_CD        VARCHAR2(1 BYTE),
  BUN               VARCHAR2(4 BYTE),
  JI                VARCHAR2(4 BYTE),
  NA_ROAD_CD        VARCHAR2(12 BYTE),
  NA_ROAD_NM        VARCHAR2(80 BYTE),
  NA_GRND_UGRND_CD  VARCHAR2(1 BYTE),
  NA_MAIN_BUN       VARCHAR2(5 BYTE),
  NA_SUB_BUN        VARCHAR2(5 BYTE)
)
TABLESPACE NBEM2_DAT
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING
NOCOMPRESS
NOCACHE
NOPARALLEL
MONITORING;

COMMENT ON TABLE NBEM2_ADM.CMC_NEWADDR_MGM IS '공통_도로명주소(새주소)_관리';


CREATE INDEX NBEM2_ADM.IX_CMC_NEWADDR_MGM_01 ON NBEM2_ADM.CMC_NEWADDR_MGM
(SIGUNGU_CD, BJDONG_CD, PLAT_GB_CD, BUN, JI)
LOGGING
TABLESPACE NBEM2_IDX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          216M
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


CREATE INDEX NBEM2_ADM.IX_CMC_NEWADDR_MGM_02 ON NBEM2_ADM.CMC_NEWADDR_MGM
(NA_ROAD_CD, NA_GRND_UGRND_CD, NA_MAIN_BUN, NA_SUB_BUN)
LOGGING
TABLESPACE NBEM2_IDX
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          216M
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;


-- CMC_USER_MGM
DROP TABLE NBEM2_ADM.CMC_USER_MGM CASCADE CONSTRAINTS;

CREATE TABLE NBEM2_ADM.CMC_USER_MGM
(
  USER_ID            VARCHAR2(30 BYTE)          NOT NULL,
  PWD                VARCHAR2(30 BYTE)          NOT NULL,
  USER_NM            VARCHAR2(10 BYTE)           NOT NULL,
  DEPT_NM            VARCHAR2(50 BYTE),
  USER_AUTHRT_CD     VARCHAR2(10 BYTE)           NOT NULL
)
TABLESPACE NBEM2_DAT
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING
NOCOMPRESS
NOCACHE
PARALLEL ( DEGREE 2 INSTANCES 1 )
MONITORING;

COMMENT ON TABLE NBEM2_ADM.CMC_USER_MGM IS '사용자 관리';

COMMENT ON COLUMN NBEM2_ADM.CMC_USER_MGM.USER_ID IS '사용자_ID';

COMMENT ON COLUMN NBEM2_ADM.CMC_USER_MGM.PWD IS '사용자_PWD';

COMMENT ON COLUMN NBEM2_ADM.CMC_USER_MGM.USER_NM IS '사용자_이름';

COMMENT ON COLUMN NBEM2_ADM.CMC_USER_MGM.DEPT_NM IS '사용자_부서명';

COMMENT ON COLUMN NBEM2_ADM.CMC_USER_MGM.USER_AUTHRT_CD IS '사용자_권한_코드';
       
-- BDT_BLDRGST
DROP TABLE NBEM2_ADM.BDT_BLDRGST CASCADE CONSTRAINTS;

CREATE TABLE NBEM2_ADM.BDT_BLDRGST
(
BLD_TYPE_GB_CD          CHAR(1)              NOT NULL,
MGM_BLD_PK              VARCHAR2(33)         NOT NULL,
REGSTR_GB_CD            VARCHAR2(1)          NULL,
REGSTR_KIND_CD          VARCHAR2(1)          NULL,
SIGUNGU_CD              VARCHAR2(5)          NULL,
BJDONG_CD               VARCHAR2(5)          NULL,
HJDONG_CD               VARCHAR2(3)          NULL,
PLAT_GB_CD              VARCHAR2(1)          NULL,
BUN                     VARCHAR2(4)          NULL,
JI                      VARCHAR2(4)          NULL,
SPLOT_NM                VARCHAR2(300)        NULL,
BLOCK                   VARCHAR2(30)         NULL,
LOT                     VARCHAR2(30)         NULL,
BYLOT_CNT               NUMBER(5)            NULL,
BLD_NM                  VARCHAR2(200)        NULL,
DONG_NM                 VARCHAR2(200)        NULL,
HO_NM                   VARCHAR2(200)        NULL,
DONG_CD                 CHAR(4)              NULL,
HO_CD                   CHAR(4)              NULL,
ETC_RCD_ITEM            VARCHAR2(1000)       NULL,
VIOL_BLD_YN             VARCHAR2(1)          NULL,
REGSTR_SEQNO            NUMBER(8)            NULL,
EXT_BLD_GB_CD           CHAR(2)              NULL,
NEW_OLD_REGSTR_GB_CD    CHAR(1)              NULL,
MAIN_ATCH_GB_CD         CHAR(1)              NULL,
MAIN_ATCH_SEQNO         NUMBER(4)            NULL,
PSTV_YN                 CHAR(1)              NULL,
SPCMT                   VARCHAR2(3000)       NULL,
PLAT_AREA               NUMBER(19,9)         NULL,
ARCH_AREA               NUMBER(19,9)         NULL,
BC_RAT                  NUMBER(19,9)         NULL,
VL_RAT_ESTM_TOTAREA     NUMBER(19,9)         NULL,
VL_RAT                  NUMBER(19,9)         NULL,
TOTAREA                 NUMBER(19,9)         NULL,
MAIN_PURPS_CD           CHAR(5)              NULL,
ETC_PURPS               VARCHAR2(1000)       NULL,
STRCT_CD                CHAR(2)              NULL,
ETC_STRCT               VARCHAR2(1000)       NULL,
ROOF_CD                 CHAR(2)              NULL,
ETC_ROOF                VARCHAR2(1000)       NULL,
HHLD_CNT                NUMBER(5)            NULL,
FMLY_CNT                NUMBER(5)            NULL,
HO_CNT                  NUMBER(5)            NULL,
HEIT                    NUMBER(19,9)         NULL,
GRND_FLR_CNT            NUMBER(5)            NULL,
UGRND_FLR_CNT           NUMBER(5)            NULL,
MAIN_BLD_CNT            NUMBER(5)            NULL,
ATCH_BLD_CNT            NUMBER(5)            NULL,
ATCH_BLD_AREA           NUMBER(19,9)         NULL,
TOT_DONG_TOTAREA        NUMBER(19,9)         NULL,
RIDE_USE_ELVT_CNT       NUMBER(5)            NULL,
EMGEN_USE_ELVT_CNT      NUMBER(5)            NULL,
TOT_PKNG_CNT            NUMBER(7)            NULL,
PMS_DAY                 VARCHAR2(8)          NULL,
STCNS_DAY               VARCHAR2(8)          NULL,
USEAPR_DAY              VARCHAR2(8)          NULL,
ENGY_EFCNCY_GRADE       VARCHAR2(5)          NULL,
ENGY_REDCT_RAT          NUMBER(19,9)         NULL,
ENGY_EFCNCY_EPI         NUMBER(5)            NULL,
GN_BLD_GRADE            VARCHAR2(1)          NULL,
GN_BLD_CERT             NUMBER(5)            NULL,
ITG_BLD_GRADE           VARCHAR2(1)          NULL,
ITG_BLD_CERT            NUMBER(5)            NULL,
MGM_UPPER_BLD_PK        VARCHAR2(33)         NULL,
MAINT_BLD_NM            VARCHAR2(200)        NULL,
MAINT_DONG_NM           VARCHAR2(200)        NULL,
MAINT_HO_NM             VARCHAR2(200)        NULL,
NA_ROAD_CD              VARCHAR2(12)         NULL,
NA_BJDONG_CD            VARCHAR2(5)          NULL,
NA_GRND_UGRND_CD        VARCHAR2(1)          NULL,
NA_MAIN_BUN             VARCHAR2(5)          NULL,
NA_SUB_BUN              VARCHAR2(5)          NULL,
OPER_SRVR_RFLC_YN       CHAR(1)              NULL
)
TABLESPACE NBEM2_DAT
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING
NOCOMPRESS
NOCACHE
PARALLEL ( DEGREE 2 INSTANCES 1 )
MONITORING;


COMMIT;

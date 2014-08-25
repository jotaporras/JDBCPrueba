--select owner,table_name,tablespace_name,num_rows 
select *
from dba_tables
where 
TABLESPACE_NAME='USERS'
AND owner<>'SYS' 
AND owner<>'OUTLN' 
AND owner<>'SYSTEM' 
AND owner<>'DBSNMP' 
AND owner<>'APPQOSSYS'
AND owner<>'XDB' 
AND owner<>'GSMADMIN_INTERNAL' 
AND owner<>'DVSYS'
AND owner<>'WMSYS' 
AND owner<>'CTXSYS' 
AND owner<>'OJVMSYS' 
AND owner<>'ORDSYS' 
AND owner<>'ORDDATA' 
AND owner<>'MDSYS' 
AND owner<>'OLAPSYS' 
AND owner<>'LBACSYS' 
AND owner<>'APEX_040200' 
AND owner<>'FLOWS_FILES' 
AND owner<>'AUDSYS' 
;

select owner,table_name,tablespace_name
from dba_tables
where 
TABLESPACE_NAME NOT LIKE '%SYS%'
AND OWNER NOT LIKE '%SYS%';

select owner, table_name, round((num_rows*avg_row_len)/(1024*1024)) MB 
from all_tables 
where owner not like '%SYS%'
and owner<>'XDB'
and owner<>'ORDDATA'
and owner<>'GSMADMIN_INTERNAL'  -- Exclude system tables.
and num_rows > 0  -- Ignore empty Tables.
order by MB desc -- Biggest first.
;

Select * FROM DBA_USERS;
Select * FROM DBA_XS_USERS;
select * from dictionary where table_name like '%USER%';
SELECT * FROM DBA_USERS_WITH_DEFPWD;

SELECT t.tablespace_name "Tablespace", t.status "Estado",
ROUND(MAX(d.bytes)/1024/1024,2) "MB Tamaño",
ROUND((MAX(d.bytes)/1024/1024) -
(SUM(decode(f.bytes, NULL,0, f.bytes))/1024/1024),2) "MB Usados",
ROUND(SUM(decode(f.bytes, NULL,0, f.bytes))/1024/1024,2) "MB Libres",
t.pct_increase "% incremento",
SUBSTR(d.file_name,1,80) "Fichero de datos"
FROM DBA_FREE_SPACE f, DBA_DATA_FILES d, DBA_TABLESPACES t
WHERE t.tablespace_name = d.tablespace_name AND
f.tablespace_name(+) = d.tablespace_name
AND f.file_id(+) = d.file_id GROUP BY t.tablespace_name,
d.file_name, t.pct_increase, t.status ORDER BY 1,3 DESC;

SELECT (SUM(BYTES)/1024/1024) FROM DBA_FREE_SPACE GROUP BY TABLESPACE_NAME;
SELECT * FROM DBA_FREE_SPACE;


select blocks, empty_blocks, num_freelist_blocks,owner,table_name
from   all_tables
where  owner = 'C##JOTA'
;
select *
from   all_tables
where  tablespace_name = 'USERS'
AND OWNER NOT LIKE '%SYS%'
;

select distinct bytes/blocks from user_segments;

select * from v$session;

select * from v$logfile;

select * from v$sgastat where pool = 'shared pool' and name = 'free memory';

select group#, status, user, blocksize, archived from v$log;

select v$log.group#,sequence#,bytes/1048576 as MB,members,archived,v$log.status, member from v$log, v$logfile where v$log.group#=v$logfile.GROUP#;


select owner,segment_name,bytes,blocks from dba_extents WHERE segment_name not like '%SYS%' AND tablespace_name not like '%SYS%' AND owner not like '%SYS%' AND SEGMENT_NAME NOT LIKE '%IX%' AND SEGMENT_NAME NOT LIKE '%PK%' ;

select *  from dba_tables;

select segment_name,segment_type,bytes MB,blocks
 from dba_segments
;

select v$log.group#,sequence#,bytes/1048576 as MB,members,archived,v$log.status, member as location from v$log, v$logfile where v$log.group#=v$logfile.GROUP#;


SELECT TABLE_NAME, COLUMN_NAME, DATA_TYPE, DATA_LENGTH, LAST_ANALYZED
    FROM DBA_TAB_COLUMNS
    WHERE OWNER = 'C##JOTA'
    ORDER BY TABLE_NAME;

select v$log.group#,sequence#,bytes/1048576 as MB,members,archived,v$log.status, member as location from v$log, v$logfile where v$log.group#=v$logfile.GROUP#;
SELECT * FROM V$log;
select * from v$logfile;
select * from v$log;



COLUMN pool    HEADING "Pool"
COLUMN name    HEADING "Name"
COLUMN sgasize HEADING "Allocated" FORMAT 999,999,999
COLUMN bytes   HEADING "Free" FORMAT 999,999,999

SELECT
    f.pool
  , f.name
  , s.sgasize
  , f.bytes
  , ROUND(f.bytes/s.sgasize*100, 2) "% Free"
FROM
    (SELECT SUM(bytes) sgasize, pool FROM v$sgastat GROUP BY pool) s
  , v$sgastat f
WHERE
    f.name = 'free memory'
  AND f.pool = s.pool
/
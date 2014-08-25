--Tablas y sus dueños
select owner,table_name,tablespace_name,num_rows,
from dba_tables
where 
TABLESPACE_NAME NOT LIKE '%SYS%'
AND OWNER NOT LIKE '%SYS%';
--Tamaño de la tabla segun su extent.
select owner,segment_name,bytes,blocks 
from dba_extents WHERE segment_name not like '%SYS%' 
AND tablespace_name not like '%SYS%' 
AND owner not like '%SYS%' 
AND SEGMENT_NAME NOT LIKE '%IX%' AND SEGMENT_NAME NOT LIKE '%PK%';
--Usuarios sin filtrar
SELECT username,
       default_tablespace,
       temporary_tablespace,
       ACCOUNT_STATUS,
 FROM dba_users; 
SELECT *
 FROM dba_tables; 


show databases;
create database vmap4;
use vmap4;
# drop database vmap4;

show engines;
show variables like "have%";
show variables like "default_storage_engine";
set default_storage_engine=MyISAM;
set default_storage_engine=InnoDB;
show variables like "%storage_engine%";

create table main_list (
    id int primary key auto_increment,
    GID varchar(255),
    GRepeat varchar(255),
    bam varchar(255),
    accession varchar(255),
    ChineseName varchar(255),
    taxa varchar(255),
    dataSet varchar(255));

insert into main_list (GID, GRepeat, bam, accession, ChineseName, taxa, dataSet) values
    ('ABD02905', 'a', 'P0007_deduped.bam', 'PI 350832', '', 'Common Wheat', 'Vmap 4'),
    ('ABD02769', ' ', 'P0042_deduped.bam', 'PI 330453', '', 'Common Wheat', 'Vmap 4'),
    ('ABD06303', ' ', 'P0063_deduped.bam', '01C0201196', '', 'Common Wheat', 'Vmap 4'),
    ('ABD02770', ' ', 'P0068_deduped.bam', 'PI 330454', '', 'Common Wheat', 'Vmap 4'),
    ('ABD05141', 'a', 'P0446_deduped.bam', 'PI 366577', '', 'Common Wheat', 'Vmap 4');
select * from main_list;

create table json_list (jdoc json);
# drop table json_list;
insert into json_list (jdoc) values
    ('{"ABD02905":["a", "P0007_deduped.bam", "PI 350832", "", "Common Wheat", "Vmap 4"]}'),
    ('{"ABD02769":[" ", "P0042_deduped.bam", "PI 330453", "", "Common Wheat", "Vmap 4"]}'),
    ('{"ABD06303":[" ", "P0063_deduped.bam", "01C0201196","", "Common Wheat", "Vmap 4"]}'),
    ('{"ABD02770":[" ", "P0068_deduped.bam", "PI 330454", "", "Common Wheat", "Vmap 4"]}'),
    ('{"ABD05141":["a", "P0446_deduped.bam", "PI 366577", "", "Common Wheat", "Vmap 4"]}');
select * from json_list;

describe main_list;

show create table main_list;

show character set;

show collation like 'utf8mb4%';

show variables like 'character%';

set names utf8mb4;

show global variables like 'a%';

show databases;

show variables like 'InnoDB_file_per_table';

show create table germplasm.germplasm;

select * from germplasm.experiment where Accession like 'PGL%';
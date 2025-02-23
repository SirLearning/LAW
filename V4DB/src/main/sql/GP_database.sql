show databases;
create database germplasm;
use germplasm;
set default_storage_engine=InnoDB;
show variables like "%storage_engine%";

create table main (
    id int primary key auto_increment,
    GID varchar(255),
    GRepeat varchar(255),
    Bams varchar(255),
    Accession varchar(255),
    ID_yin varchar(255),
    Sample_name varchar(255),
    English_name varchar(255),
    Chinese_name varchar(255),
    Data_sources varchar(255),
    Genome_type varchar(255),
    Type_original8 varchar(255),
    Type_USDA8 varchar(255),
    Common_name varchar(255),
    Type_final varchar(255),
    Latitude double,
    Longitude double,
    Continent varchar(255),
    Continent_Abbreviate varchar(255),
    Country varchar(255),
    City varchar(255),
    City_USDA varchar(255),
    Genus varchar(255),
    Species varchar(255),
    Full_Taxonomy varchar(255),
    Elevation double,
    Received_year_USDA varchar(255),
    Source_data_USDA varchar(255),
    Growth_habit varchar(255),
    Released_or_introduced_year varchar(255),
    Pedigree varchar(255),
    Eco_region varchar(255),
    Germplasm_bank varchar(255),
    Growth_class varchar(255),
    WEGA varchar(255),
    Data_set varchar(255));

alter table main
add column `Accession_name_GS` VARCHAR(255),
add column `Ploidy` VARCHAR(255),
add column `Taxonomy_update_GS` VARCHAR(255),
add column `Simple_classification` VARCHAR(255),
add column `Group` VARCHAR(255),
add column `Biological_status_of_accession_upadate_GS` VARCHAR(255),
add column `Provenance_of_material_GS` VARCHAR(255),
add column `Province` VARCHAR(255),
add column `Acquisition_date_GS` VARCHAR(255),
add column `Location_of_collecting_site_GS` VARCHAR(255),
add column `Latitude_GS` DOUBLE,
add column `Longitude_GS` DOUBLE,
add column `Elevation_GS` DOUBLE,
add column `R1` VARCHAR(255),
add column `WAP2085_to_2588` VARCHAR(255),
add column `R_and_S` VARCHAR(255),
add column `Genesys_URL` VARCHAR(255);

alter table main
drop column `Group`;

alter table main
add column `Group_yin` VARCHAR(255);

UPDATE main
SET
    GID = CASE WHEN GID IN ('-', 'NA') THEN '' ELSE GID END,
    GRepeat = CASE WHEN GRepeat IN ('-', 'NA') THEN '' ELSE GRepeat END,
    Bams = CASE WHEN Bams IN ('-', 'NA') THEN '' ELSE Bams END,
    Accession = CASE WHEN Accession IN ('-', 'NA') THEN '' ELSE Accession END,
    ID_yin = CASE WHEN ID_yin IN ('-', 'NA') THEN '' ELSE ID_yin END,
    Sample_name = CASE WHEN Sample_name IN ('-', 'NA') THEN '' ELSE Sample_name END,
    English_name = CASE WHEN English_name IN ('-', 'NA') THEN '' ELSE English_name END,
    Chinese_name = CASE WHEN Chinese_name IN ('-', 'NA') THEN '' ELSE Chinese_name END,
    Data_sources = CASE WHEN Data_sources IN ('-', 'NA') THEN '' ELSE Data_sources END,
    Genome_type = CASE WHEN Genome_type IN ('-', 'NA') THEN '' ELSE Genome_type END,
    Type_original8 = CASE WHEN Type_original8 IN ('-', 'NA') THEN '' ELSE Type_original8 END,
    Type_USDA8 = CASE WHEN Type_USDA8 IN ('-', 'NA') THEN '' ELSE Type_USDA8 END,
    Common_name = CASE WHEN Common_name IN ('-', 'NA') THEN '' ELSE Common_name END,
    Type_final = CASE WHEN Type_final IN ('-', 'NA') THEN '' ELSE Type_final END,
    Continent = CASE WHEN Continent IN ('-', 'NA') THEN '' ELSE Continent END,
    Continent_Abbreviate = CASE WHEN Continent_Abbreviate IN ('-', 'NA') THEN '' ELSE Continent_Abbreviate END,
    Country = CASE WHEN Country IN ('-', 'NA') THEN '' ELSE Country END,
    City = CASE WHEN City IN ('-', 'NA') THEN '' ELSE City END,
    City_USDA = CASE WHEN City_USDA IN ('-', 'NA') THEN '' ELSE City_USDA END,
    Genus = CASE WHEN Genus IN ('-', 'NA') THEN '' ELSE Genus END,
    Species = CASE WHEN Species IN ('-', 'NA') THEN '' ELSE Species END,
    Full_Taxonomy = CASE WHEN Full_Taxonomy IN ('-', 'NA') THEN '' ELSE Full_Taxonomy END,
    Received_year_USDA = CASE WHEN Received_year_USDA IN ('-', 'NA') THEN '' ELSE Received_year_USDA END,
    Source_data_USDA = CASE WHEN Source_data_USDA IN ('-', 'NA') THEN '' ELSE Source_data_USDA END,
    Growth_habit = CASE WHEN Growth_habit IN ('-', 'NA') THEN '' ELSE Growth_habit END,
    Released_or_introduced_year = CASE WHEN Released_or_introduced_year IN ('-', 'NA') THEN '' ELSE Released_or_introduced_year END,
    Pedigree = CASE WHEN Pedigree IN ('-', 'NA') THEN '' ELSE Pedigree END,
    Eco_region = CASE WHEN Eco_region IN ('-', 'NA') THEN '' ELSE Eco_region END,
    Germplasm_bank = CASE WHEN Germplasm_bank IN ('-', 'NA') THEN '' ELSE Germplasm_bank END,
    Growth_class = CASE WHEN Growth_class IN ('-', 'NA') THEN '' ELSE Growth_class END,
    WEGA = CASE WHEN WEGA IN ('-', 'NA') THEN '' ELSE WEGA END,
    Data_set = CASE WHEN Data_set IN ('-', 'NA') THEN '' ELSE Data_set END,
    Accession_name_GS = CASE WHEN Accession_name_GS IN ('-', 'NA') THEN '' ELSE Accession_name_GS END,
    Ploidy = CASE WHEN Ploidy IN ('-', 'NA') THEN '' ELSE Ploidy END,
    Taxonomy_update_GS = CASE WHEN Taxonomy_update_GS IN ('-', 'NA') THEN '' ELSE Taxonomy_update_GS END,
    Simple_classification = CASE WHEN Simple_classification IN ('-', 'NA') THEN '' ELSE Simple_classification END,
    Group_yin = CASE WHEN Group_yin IN ('-', 'NA') THEN '' ELSE Group_yin END,
    Biological_status_of_accession_upadate_GS = CASE WHEN Biological_status_of_accession_upadate_GS IN ('-', 'NA') THEN '' ELSE Biological_status_of_accession_upadate_GS END,
    Provenance_of_material_GS = CASE WHEN Provenance_of_material_GS IN ('-', 'NA') THEN '' ELSE Provenance_of_material_GS END,
    Province = CASE WHEN Province IN ('-', 'NA') THEN '' ELSE Province END,
    Acquisition_date_GS = CASE WHEN Acquisition_date_GS IN ('-', 'NA') THEN '' ELSE Acquisition_date_GS END,
    Location_of_collecting_site_GS = CASE WHEN Location_of_collecting_site_GS IN ('-', 'NA') THEN '' ELSE Location_of_collecting_site_GS END,
    R1 = CASE WHEN R1 IN ('-', 'NA') THEN '' ELSE R1 END,
    WAP2085_to_2588 = CASE WHEN WAP2085_to_2588 IN ('-', 'NA') THEN '' ELSE WAP2085_to_2588 END,
    R_and_S = CASE WHEN R_and_S IN ('-', 'NA') THEN '' ELSE R_and_S END,
    Genesys_URL = CASE WHEN Genesys_URL IN ('-', 'NA') THEN '' ELSE Genesys_URL END;

drop table if exists main;

create table tmp_main (
    GID varchar(255) primary key,
    Bams varchar(255)
);

UPDATE main m
JOIN tmp_main o ON m.GID = o.GID
SET m.Bams = o.Bams;

drop table if exists tmp_main;

select * from germplasm.main where GID='ABD06101';
select * from germplasm.main where English_name='Watkins';
select * from germplasm.main where ID_yin='Watkins';
select * from germplasm.main where Sample_name='Watkins';
select * from germplasm.main where Accession_name_GS='Watkins';

show columns from main;

create table Taxonomy (
    number int primary key auto_increment,
    Genus varchar(255),
    Species varchar(255),
    Full_Taxonomy varchar(255),
    Common_name varchar(255),
    Genome_type varchar(255),
    Ploidy varchar(255));

create table Provenance (
    number int primary key auto_increment,
    Continent varchar(255),
    Continent_abbr varchar(255),
    Country varchar(255),
    Country_abbr varchar(255),
    Province varchar(255),
    City varchar(255));

create table Germplasm (
    Accession varchar(255) primary key,
    Name varchar(255),
    Other_name varchar(255),
    Chinese_name varchar(255),
    Wheat_type varchar(255),
    Taxonomy_number int,
    Pedigree varchar(255),
    Growth_habit varchar(255),
    Provenance_number int,
    Latitude double,
    Longitude double,
    Elevation double,
    Introduced_year varchar(255),
    Eco_region varchar(255),
    foreign key (Taxonomy_number) references Taxonomy(number),
    foreign key (Provenance_number) references Provenance(number));


set foreign_key_checks = 0;
truncate table wgs;
set foreign_key_checks = 1;


create table WGS (
    GID varchar(255) primary key,
    GID_update varchar(255),
    Bams varchar(255),
    Dataset varchar(255)
);

create table WEGA (
    ID varchar(255) primary key);

create table Experiment (
    Accession varchar(255),
    GID varchar(255),
    GRepeat varchar(255),
    WEGA_ID varchar(255),
    Data_sources varchar(255),
    Time varchar(255),
    R1 varchar(255),
    WAP2085_to_2588 varchar(255),
    R_and_S varchar(255),
    foreign key (Accession) references Germplasm(Accession),
    foreign key (GID) references WGS(GID),
    foreign key (WEGA_ID) references WEGA(ID));

set foreign_key_checks = 0;
drop table Inherit;
set foreign_key_checks = 1;

create table Inherit (
    Accession varchar(255),
    USDA boolean,
    Genesys boolean,
    IGDB boolean,
    WGD_V7 boolean,
    CGRIS boolean,
    USA boolean,
    CHN boolean,
    NSGC boolean,
    CAAS boolean,
    HENU boolean,
    UCDAVIS boolean,
    foreign key (Accession) references Germplasm(Accession));

create table USDA (
    Accession varchar(255) primary key,
    Wheat_type varchar(255),
    City varchar(255),
    Received_year varchar(255),
    Source_date varchar(255));

create table Genesys (
    Accession varchar(255) primary key,
    Accession_name varchar(255),
    Taxonomy varchar(255),
    Biological_status_of_accession varchar(255),
    Provenance_of_material varchar(255),
    Acquisition_date varchar(255),
    Location_of_collecting_site varchar(255),
    Latitude double,
    Longitude double,
    Elevation double,
    URL varchar(255));

set foreign_key_checks = 0;
truncate table taxonomy;
truncate table provenance;
truncate table germplasm;
truncate table wgs;
truncate table wega;
truncate table experiment;
truncate table inherit;
truncate table usda;
truncate table igdb;
truncate table wgd_v7;
set foreign_key_checks = 1;

truncate table igdb;

create table IGDB (
    Accession varchar(255) primary key,
    Chinese_name varchar(255),
    Wheat_type varchar(255),
    Growth_habit varchar(255));

create table WGD_V7 (
    Accession varchar(255) primary key,
    ID_yin varchar(255),
    Group_yin varchar(255),
    Common_name varchar(255));

show tables;

SELECT COUNT(*) FROM germplasm.taxonomy WHERE Genus = 'Triticum' AND Species = 'b';

set foreign_key_checks = 0;
truncate table taxonomy;
truncate table provenance;
set foreign_key_checks = 1;

set foreign_key_checks = 0;
truncate table germplasm;
set foreign_key_checks = 1;

drop table main;



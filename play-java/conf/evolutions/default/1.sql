# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table messages (
  id                        bigint auto_increment not null,
  contact                   varchar(255),
  arrived_at                datetime(6),
  body                      varchar(255),
  lat                       double,
  lng                       double,
  constraint pk_messages primary key (id))
;

create table outreachteam (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  contact                   varchar(255),
  constraint pk_outreachteam primary key (id))
;

create table tasks (
  id                        bigint auto_increment not null,
  contact                   varchar(255),
  outreach_id               bigint,
  created_at                datetime(6),
  dispatched_at             datetime(6),
  received_at               datetime(6),
  status                    varchar(255),
  constraint pk_tasks primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table messages;

drop table outreachteam;

drop table tasks;

SET FOREIGN_KEY_CHECKS=1;


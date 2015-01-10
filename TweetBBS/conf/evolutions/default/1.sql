# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table tweet (
  id                        bigint not null,
  content                   varchar(255),
  post_date                 varchar(255),
  constraint pk_tweet primary key (id))
;

create sequence tweet_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists tweet;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists tweet_seq;


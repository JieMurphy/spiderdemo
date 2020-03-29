DROP TABLE IF EXISTS user;
CREATE TABLE user(
  id INT NOT NULL AUTO_INCREMENT primary key ,
  name VARCHAR(20) NOT NULL ,
  password VARCHAR(20) NOT NULL ,
  power varchar(20) NOT NULL ,
  create_time timestamp not null default current_timestamp
)engine = innodb default charset = utf8;

insert into user (id, name, password, power) VALUES (1,"admin","123456","管理员");

drop table if exists resources;
create table resources(
  id int auto_increment not null primary key,
  title varchar(200),
  body text,
  user_id int,
  path varchar(200),
  first int,
  second int,
  third int,
  forth int,
  status varchar(100),
  create_time timestamp not null default current_timestamp,
  fulltext(title,body)with parser ngram
)engine = innodb default charset = utf8;

insert into resources (id, title, body, user_id, path,first,second,third,forth,status) VALUES (1,"冰冻水果","冰冻水果真好吃",1,"/a.png",1,2,3,4,"已通过");
insert into resources (title, body, user_id, path,first,second,third,forth,status) VALUES ("热带水果","热带水果也还行",1,"/a.mp4",2,3,2,2,"待删除");
insert into resources (title, body, user_id, path,first,second,third,forth,status) VALUES ("热带鱼","热带鱼有什么特别",1,"/a.txt",1,2,3,3,"审核中");

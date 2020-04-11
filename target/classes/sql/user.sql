drop table if exists resources;
DROP TABLE IF EXISTS user;
drop table if exists classify;

CREATE TABLE user(
  id INT NOT NULL AUTO_INCREMENT primary key ,
  name VARCHAR(20) NOT NULL ,
  password VARCHAR(20) NOT NULL ,
  email varchar(100),
  power int NOT NULL ,
  create_time timestamp not null default current_timestamp
)engine = innodb default charset = utf8;

insert into user (id, name, password, power) VALUES (1,"admin","123456",1);
insert into user (name, password, power,email) VALUES ("Jie","654321",0,"1925847831@qq.com");
insert into user (name, password, power,email) VALUES ("Jack","111111",-1,"mpuygu@163.com");


create table classify(
  id int auto_increment not null primary key,
  type varchar(100) not null,
  parent_id int not null ,
  level int
)engine = innodb default charset = utf8;

insert into classify (id, type, parent_id, level) VALUES (1,"首页",-1,0);
insert into classify (id, type, parent_id, level) VALUES (2,"百科",1,1);
insert into classify (id, type, parent_id, level) VALUES (3,"术语百科",2,2);
insert into classify (id, type, parent_id, level) VALUES (4,"农作物",3,3);
insert into classify (id, type, parent_id, level) VALUES (5,"养殖业",3,3);
insert into classify (id,type, parent_id, level) VALUES (6,"水果",4,4);
insert into classify (id,type, parent_id, level) VALUES (7,"鱼类",5,4);


create table resources(
  id int auto_increment not null primary key,
  title varchar(200) not null ,
  body text,
  user_id int,
  path varchar(200),
  first int,
  second int,
  third int,
  forth int,
  status int,
  ftype varchar(20),
  create_time timestamp default current_timestamp,
  update_time timestamp default current_timestamp on update current_timestamp,
  foreign key (user_id) references user(id),
  fulltext(title,body)with parser ngram
)engine = innodb default charset = utf8;

insert into resources (title, body, user_id, path,first,second,third,forth,status,ftype) VALUES ("冰冻水果","冰冻水果真好吃",1,"/a.png",2,3,4,6,1,"图片");
insert into resources (title, body, user_id, path,first,second,third,forth,status,ftype) VALUES ("热带水果","热带水果也还行",1,"/a.mp4",2,3,4,6,1,"视频");
insert into resources (title, body, user_id, path,first,second,third,forth,status,ftype) VALUES ("热带鱼","热带鱼有什么特别",1,"/a.txt",2,3,5,7,0,"文本");
insert into resources (title, body, user_id, path,first,second,third,forth,status,ftype) VALUES ("淡水鱼养殖","淡水鱼与咸水鱼差很多",1,"/b.txt",2,3,5,7,-1,"文本");
insert into resources (title, body, user_id, path,first,second,third,forth,status,ftype) VALUES ("咸水鱼捕获","咸水鱼该如何捕获",1,"/c.txt",2,3,5,7,1,"文本");


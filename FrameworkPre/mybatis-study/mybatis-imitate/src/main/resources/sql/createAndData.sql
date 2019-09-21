-- 创建用户表
create table user(
	id int(11) primary key auto_increment comment 'id',
	name varchar(255) comment '账号',
	age int(11) comment '密码'
);

-- 插入数据
insert into user values(default, 'a', 10);
insert into user values(default, 'b', 20);
insert into user values(default, 'c', 30);
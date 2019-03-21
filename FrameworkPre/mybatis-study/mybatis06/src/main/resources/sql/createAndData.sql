-- 创建账号表
create table account(
	id int(10) primary key auto_increment comment 'id',
	accno varchar(18) unique comment '账号',
	password int(6) comment '密码',
	balance double comment '可用余额',
	name varchar(20) comment '用户姓名'
);

-- 插入数据
insert into account values(default, '1', '2', 1000, '张三');
insert into account values(default, '3', '4', 2000, '李四');
insert into account values(default, '5', '6', 3000, '王五');

-- 创建转账记录表
create table log(
	id int(10) primary key auto_increment,
	accout varchar(18),
	accin varchar(18),
	money double
);
-- 创建转账记录表
create table log(
	id int(10) primary key auto_increment,
	accout varchar(18),
	accin varchar(18),
	money double
);

-- 插入数据
insert into log values(default, '1', '3', 100);
insert into log values(default, '1', '3', 100);
insert into log values(default, '1', '3', 100);
insert into log values(default, '1', '3', 100);
insert into log values(default, '2', '3', 200);
insert into log values(default, '2', '3', 200);
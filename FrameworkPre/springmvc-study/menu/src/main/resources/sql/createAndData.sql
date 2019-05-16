-- 创建表
create table menu(
id int(10) primary key auto_increment,
name varchar(20),
pid int(10));

-- 插入数据
insert into menu values(default, '系统设置', 0);
insert into menu values(default, '销售管理', 0);
insert into menu values(default, '修改密码', 1);
insert into menu values(default, '添加用户', 1);
insert into menu values(default, '新增销售人员', 2);
insert into menu values(default, '删除销售人员', 2);
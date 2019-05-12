-- 创建表
create table users(
id int(10) primary key auto_increment,
username varchar(20) unique,
password varchar(20)
);

-- 插入数据
insert into users values(default, '张三', '123');
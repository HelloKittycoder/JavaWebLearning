drop table if exists student;

-- 创建学生表
create table student(
	id int primary key auto_increment,
	name varchar(50),
	age int
);

insert into student(id, name, age) values(null, '张三', 10);
insert into student(id, name, age) values(null, '李四', 20);
insert into student(id, name, age) values(null, '王五', 30);

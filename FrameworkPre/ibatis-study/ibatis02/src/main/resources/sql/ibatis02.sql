-- 创建学生表
create table student(
	id int primary key auto_increment,
	name varchar(50),
	age int
) auto_increment=2;

insert into student(id, name, age) values(1, '张三', 10);
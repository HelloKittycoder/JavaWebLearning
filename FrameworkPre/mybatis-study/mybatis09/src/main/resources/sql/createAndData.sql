-- 老师表
create table teacher(
id int(10) primary key auto_increment,
name varchar(20)
);

-- 学生表
create table student(
id int(10) primary key auto_increment,
name varchar(20),
age int(3),
tid int(10)
-- 如果要添加外键约束，添加下面这行
-- constraint fk_teacher foreign key (tid) references teacher(id)
);

-- 插入测试数据
insert into teacher values(default, '老师1');
insert into teacher values(default, '老师2');

insert into student values(default, '学生1', 1, 1);
insert into student values(default, '学生2', 2, 1);
insert into student values(default, '学生3', 3, 1);
insert into student values(default, '学生4', 4, 1);
insert into student values(default, '学生5', 5, 1);
insert into student values(default, '学生6', 6, 1);
insert into student values(default, '学生7', 7, 1);
insert into student values(default, '学生8', 8, 2);
insert into student values(default, '学生9', 9, 2);
insert into student values(default, '学生10', 10, 2);
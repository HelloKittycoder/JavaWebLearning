-- 飞机场表
create table airport(
id int(10) primary key auto_increment,
portname varchar(20),
cityname varchar(20)
);

insert into airport values(default, '首都机场', '北京');
insert into airport values(default, '南苑机场', '北京');
insert into airport values(default, '虹桥机场', '上海');

-- 航班信息表
create table airplane(
id int(10) primary key auto_increment,
airno varchar(20),
time int(5) comment '单位分钟',
price double,
takeid int(10) comment '起飞机场',
landid int(10) comment '降落机场'
);

insert into airplane values(default, '波音747', 123, 100, 1, 3);
insert into airplane values(default, '波音858', 56, 300, 3, 2);
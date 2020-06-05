-- 创建测试表
create table test_handler(
	l_id int(10) primary key auto_increment comment 'id',
	vc_name varchar(255) comment '名称',
	vc_sex varchar(5) comment '1男；2女',
	vc_age int(10) comment '年龄',
	vc_json_str varchar(255) comment 'json数据'
) engine=innodb auto_increment=50;

-- 插入数据
insert into test_handler values(default, '张三', '1', 10, '{\"className\":\"一年级1班\",\"teacherName\":\"张三丰\"}');
insert into test_handler values(default, '李四', '1', 20, '{\"className\":\"一年级2班\",\"teacherName\":\"张无忌\"}');
insert into test_handler values(default, '小红', '2', 30, '{\"className\":\"一年级3班\",\"teacherName\":\"灭绝师太\"}');

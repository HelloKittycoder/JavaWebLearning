-- 创建表
create table flower (
  id int(11) not null auto_increment comment '花卉编号',
  name varchar(255) default null comment '花卉名称',
  price double default null comment '价格',
  production varchar(255) default null comment '原产地',
  primary key (id)
) comment '花卉信息表';

-- 插入数据
insert into flower values(default, '矮牵牛', 2.5, '南美阿根廷');
insert into flower values(default, '百日草', 5.0, '墨西哥');
insert into flower values(default, '半枝莲', 4.3, '巴西');
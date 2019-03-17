-- 创建表
CREATE TABLE `flower` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '花卉编号',
  `name` varchar(255) DEFAULT NULL COMMENT '花卉名称',
  `price` double DEFAULT NULL COMMENT '价格',
  `production` varchar(255) DEFAULT NULL COMMENT '原产地',
  PRIMARY KEY (`id`)
);

-- 插入数据
insert into flower values(default, '矮牵牛', 2.5, '南美阿根廷');
insert into flower values(default, '百日草', 5.0, '墨西哥');
insert into flower values(default, '半枝莲', 4.3, '巴西');
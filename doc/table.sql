CREATE TABLE `tb_service_route` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `service_name` varchar(32) NOT NULL DEFAULT '' COMMENT '服务名',
  `service_method` varchar(32) NOT NULL DEFAULT '' COMMENT '服务方法名',
  `service_host` varchar(128) NOT NULL COMMENT '服务url',
  `type` tinyint(4) NOT NULL COMMENT '服务类型（1.rpc2.http）',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
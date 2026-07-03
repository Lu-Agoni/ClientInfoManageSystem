-- 创建数据库
create database if not exists clientInfo
default character set utf8mb4
default collate utf8mb4_unicode_ci;

-- 使用该数据库
use clientInfo;

-- 用户表 系统的登录用户 员工 管理员
CREATE TABLE user (
    uid int(11) not null auto_increment comment '用户ID',
    username varchar(50) not null comment '登录账号',
    password varchar(255) not null comment '登录密码',
    email varchar(100) default null comment '邮箱',
    PRIMARY KEY (uid)
);

-- 客户表 企业客户信息
create table client(
    cid int(11) not null auto_increment comment '客户id',
    cno varchar(30) not null unique comment '客户编号',
    cname varchar(100) not null comment '客户姓名',
    cphone varchar(20) default null comment '客户电话',
    caddress varchar(200) default null comment '地址',
    industry varchar(50) default null comment '客户公司名称',
    level varchar(20) default null comment '客户等级 A B C',
    source varchar(50) default null comment '客户来源',
    primary key (cid)
);

-- 联系人表 一个客户公司下有多个联系人 对接人 boss 采购...
create table contact(
    coid int(11) not null auto_increment comment '联系人id',
    cid int(11) not null comment '客户id',
    coname varchar(50) not null comment '联系人姓名',
    position varchar(50) default null comment '职位',
    cophone varchar(20) default null comment '联系人电话',
    coemail varchar(100) default null comment '联系人邮箱',
    isMain tinyint(1) default '0' comment '是否主要联系人',
    primary key (coid),
    foreign key (cid) references client (cid) on delete cascade
);

-- 商机表 销售机会，订单意向 潜在生意
create table opportunity(
    opid int(11) not null auto_increment comment '商机id',
    cid int(11) not null comment '所属客户',
    title varchar(200) not null comment '商机标题',
    amount decimal(12,2) not null comment '预计金额',
    probability int(3) default null comment '成交概率 0-100',
    status varchar(20) default 'prospecting' comment 'prospecting 初步沟通, negotiation 协商方案, won 成交, lost 丢失',
    primary key (opid),
    foreign key (cid) references client (cid) on delete cascade
);

-- 跟进活动表 每一次跟客户沟通的记录 电话 上门他 线上沟通
create table activity(
    aid int(11) not null auto_increment,
    opid int(11) not null comment '所属商机',
    content text not null comment '跟进内容',
    type varchar(20) default 'call' comment 'call,meeting,email',
    actime datetime default current_timestamp comment '跟进时间',
    createUser varchar(50) default null comment '操作人',
    primary key (aid),
    foreign key (opid) references opportunity (opid) on delete cascade
);

-- 产品表 公司卖的产品
create table product(
    pid int(11) not null unique comment '产品id',
    pno varchar(30) not null unique comment '产品编号',
    pname varchar(100) not null comment '产品名称',
    price decimal(10,2) default null comment '单价',
    unit varchar(10) default null comment '单位',
    category varchar(50) default null comment '分类',
    primary key (pid)
);

-- 客户产品关联表 客户买了什么产品
create table client_product(
    cpid int(11) not null auto_increment,
    cid int(11) not null comment '客户id',
    pid int(11) not null comment '产品id',
    number int(11) default '1' comment '购买数量',
    primary key (cpid),
    foreign key (cid) references client (cid) on delete cascade,
    foreign key (pid) references product (pid)
);

-- 角色表 系统角色 管理员，销售 经理 客服
create table role(
    rid int(11) not null auto_increment comment '角色id',
    rname varchar(50) not null unique comment '角色名称',
    primary key (rid)
);

-- 用户角色关联表 一个用户对应多个角色
create table user_role(
    uid int(11) not null comment '用户id',
    rid int(11) not null comment '角色id',
    primary key (uid,rid),
    foreign key (uid) references user (uid),
    foreign key (rid) references role (rid)
);

-- 权限表 系统功能和权限
create table permission(
    peid int(11) not null auto_increment,
    peCode varchar(100) not null unique comment '权限编码',
    peName varchar(50) not null comment '权限名称',
    primary key (peid)
);

-- 角色权限关联表 什么角色对应拥有什么权限
create table role_pre(
    rid int(11) not null comment '角色id',
    peid int(11) not null comment '权限id',
    primary key (rid,peid),
    foreign key (rid) references role(rid),
    foreign key (peid) references permission(peid)
);

/* 数据插入
3个用户：管理员、销售、经理
3个客户：北京科技、上海贸易、广州制造
3个联系人：每个客户对应 1~2 个联系人
3个商机：洽谈中、待跟进、已成交
3条跟进记录：电话、拜访、邮件
3个产品：CRM、进销存、数据服务
3个角色 + 4个权限，完整权限体系
*/
-- 插入用户数据 password 123456
insert into user (username, password, email) values
('admin', 'e10adc3949ba59abbe56e057f20f883e', 'admin@test.com'),
('zhangsan', 'e10adc3949ba59abbe56e057f20f883e', 'zhangsan@test.com'),
('lisi','e10adc3949ba59abbe56e057f20f883e', 'lisi@test.com');

-- 插入客户数据
insert into client (cno, cname, cphone, caddress, industry, level, source) values
('C001', '北京科技有限公司', '010-12345678', '北京市海淀区', 'IT', 'A', '线上推广'),
('C002', '上海贸易有限公司', '021-87654321', '上海市浦东新区', '贸易', 'B', '客户介绍'),
('C003', '广州制造有限公司', '020-11223344', '广州市天河区', '制造', 'C', '展会');

-- 插入联系人数据
insert into contact (cid, coname, position, cophone, coemail, isMain) values
(1, '张三', '总经理', '13800138000', 'zs@bjtech.com', 1),
(1, '李四', '采购经理', '13900139000', 'ls@bjtech.com', 0),
(2, '王五', 'CEO', '13700137000', 'ww@shtrade.com', 1);

-- 插入商机数据
insert into opportunity (cid, title, amount, probability, status) values
(1, '采购管理系统', 50000.00, 80, 'negotiation'),
(2, '进销存软件', 30000.00, 50, 'prospecting'),
(3, '生产线监控系统', 120000.00, 90, 'won');

-- 插入跟进活动数据
insert into activity (opid, content, type, actime, createUser) values
(1, '电话沟通需求，客户很感兴趣', 'call', NOW(), 'admin'),
(2, '上门演示产品', 'meeting', NOW(), 'zhangsan'),
(3, '发送合同邮件', 'email', NOW(), 'lisi');

-- 插入产品数据
insert into product (pid, pno, pname, price, unit, category) values
(1, 'P001', 'CRM客户管理系统', 29800.00, '套', '软件'),
(2, 'P002', '进销存管理系统', 19800.00, '套', '软件'),
(3, 'P003', '数据监控服务', 500.00, '月', '服务');

-- 插入客户购买产品记录
insert into client_product (cid, pid, number) values
(1, 1, 1),
(2, 2, 1),
(3, 3, 12);

-- 插入角色
insert into role (rname) values
('管理员'),
('销售'),
('经理');

-- 插入用户角色关联
insert into user_role (uid, rid) values
(1, 1),
(2, 2),
(3, 3);

-- 插入权限
insert into permission (peCode, peName) values
('user:list', '查看用户'),
('user:add', '添加用户'),
('customer:list', '查看客户'),
('customer:edit', '编辑客户');

-- 插入角色权限关联
insert into role_pre (rid, peid) values
(1, 1), (1, 2), (1, 3), (1, 4),
(2, 3),
(3, 3), (3, 4);
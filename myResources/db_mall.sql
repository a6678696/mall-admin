/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.6.24 : Database - db_mall
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_mall` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_mall`;

/*Table structure for table `t_address` */

DROP TABLE IF EXISTS `t_address`;

CREATE TABLE `t_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customerId` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `phoneNum` varchar(100) DEFAULT NULL,
  `isSelected` tinyint(4) DEFAULT NULL,
  `area` varchar(500) DEFAULT NULL,
  `areaCode` varchar(100) DEFAULT NULL,
  `details` varchar(500) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `customerId` (`customerId`),
  CONSTRAINT `t_address_ibfk_1` FOREIGN KEY (`customerId`) REFERENCES `t_customer` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `t_address` */

insert  into `t_address`(`id`,`customerId`,`name`,`phoneNum`,`isSelected`,`area`,`areaCode`,`details`,`description`) values (6,34,'冯浩','13458202555',0,'山西省运城市绛县','140826','卫庄镇23号','家'),(7,34,'冯浩','13837331638',1,'宁夏回族自治区固原市泾源县','640424','六盘山镇12号','学校');

/*Table structure for table `t_administrator` */

DROP TABLE IF EXISTS `t_administrator`;

CREATE TABLE `t_administrator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(500) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_administrator` */

insert  into `t_administrator`(`id`,`userName`,`password`) values (1,'111','111');

/*Table structure for table `t_announcement` */

DROP TABLE IF EXISTS `t_announcement`;

CREATE TABLE `t_announcement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `addDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

/*Data for the table `t_announcement` */

insert  into `t_announcement`(`id`,`title`,`content`,`addDate`) values (29,'公告1','公告1','2022-12-15 18:44:10'),(30,'公告2','公告2公告2','2022-12-15 18:44:55'),(31,'公告3','公告3公告3公告3','2022-12-15 18:45:07');

/*Table structure for table `t_customer` */

DROP TABLE IF EXISTS `t_customer`;

CREATE TABLE `t_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickName` varchar(100) DEFAULT NULL,
  `openid` varchar(100) DEFAULT NULL,
  `avatarImageName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

/*Data for the table `t_customer` */

insert  into `t_customer`(`id`,`nickName`,`openid`,`avatarImageName`) values (34,'乐道','o4ycD5AQHbIns2rqn8EeWqbpW7ho','202212312230561672497056670.jpg');

/*Table structure for table `t_goods` */

DROP TABLE IF EXISTS `t_goods`;

CREATE TABLE `t_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) DEFAULT NULL,
  `smallTypeId` int(11) DEFAULT NULL,
  `typeName` varchar(500) DEFAULT NULL,
  `details` text,
  `price` decimal(10,0) DEFAULT NULL,
  `priceOld` decimal(10,0) DEFAULT NULL,
  `cardImageName` varchar(200) DEFAULT NULL,
  `hotGoods` tinyint(1) DEFAULT NULL,
  `setHotGoodsDate` datetime DEFAULT NULL,
  `swiperGoods` tinyint(1) DEFAULT NULL,
  `setSwiperGoodsDate` datetime DEFAULT NULL,
  `recommendGoods` tinyint(1) DEFAULT NULL,
  `recommendDate` datetime DEFAULT NULL,
  `swiperImageName` varchar(200) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `salesVolume` int(11) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `goodsDetailsSwiperImageStr` varchar(500) DEFAULT NULL,
  `addDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `smallTypeId` (`smallTypeId`),
  CONSTRAINT `t_goods_ibfk_1` FOREIGN KEY (`smallTypeId`) REFERENCES `t_type_small` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*Data for the table `t_goods` */

insert  into `t_goods`(`id`,`name`,`smallTypeId`,`typeName`,`details`,`price`,`priceOld`,`cardImageName`,`hotGoods`,`setHotGoodsDate`,`swiperGoods`,`setSwiperGoodsDate`,`recommendGoods`,`recommendDate`,`swiperImageName`,`description`,`salesVolume`,`stock`,`goodsDetailsSwiperImageStr`,`addDate`) values (16,'一加 Ace',7,'数码/手机','<p>网络类型：5G全网通 </p><p>品牌：OnePlus/一加 </p><p>型号：一加 Ace</p><p>售后服务：全国联保 </p><p>接口类型：Type-C </p><p>分辨率：1080*2412</p><p>机身颜色：回蓝 开黑 青装 </p><p>套餐类型：官方标配 </p><p>后置摄像头：5000 万 + 800万 + 200万</p><p>生产企业：深圳市万普拉斯科技有限公司 </p><p>存储容量：8GB+128GB 8GB+256GB 12GB+256GB 12GB+512GB </p><p>屏幕材质：AMOLED 柔性</p><p>手机类型：智能手机 </p><p>摄像头类型：四摄像头（前一后三） </p><p>网络模式：双卡双待</p><p>电信设备进网许可证编号：00-B182-228209 </p><p>屏幕刷新率：120Hz </p><p>版本类型：中国大陆</p><p>CPU品牌：联发科 </p><p>上市时间：2022-04 </p><p>屏幕尺寸：6.7英寸</p><p>电池容量：4500mAh </p><p>机身厚度：8.2mm </p><p>CPU型号：天玑 8100-MAX</p><p>是否支持NFC：是 </p><p>充电功率：150W </p><p>前置摄像头像素：1600万像素</p><p>商品系列：一加Ace系列 </p><p>3C证书编号：2022011606454871</p><p><br></p><p><img src=\"http://localhost:8080/image/goods/details/202212180436501671309410754.jpg\" alt=\"\" data-href=\"\" style=\"\" width=\"733\" height=\"978.3056719168901\"></p><p><img src=\"http://localhost:8080/image/goods/details/202212180437001671309420485.jpg\" alt=\"\" data-href=\"\" style=\"\" width=\"734\" height=\"924.84\"></p><p><img src=\"http://localhost:8080/image/goods/details/202212180437091671309429816.jpg\" alt=\"\" data-href=\"\" style=\"\" width=\"734\" height=\"864.1626666666667\"></p>','2499','2399','202212191510351671433835827.jpg',1,'2022-12-27 20:54:07',1,'2022-12-19 20:02:59',1,'2022-12-19 15:30:00','default.jpg','天玑81O0-MAX|长寿版150W闪充',5,96,',202212191533511671435231198.jpg,202212191534311671435271530.jpg,202212191534561671435296477.jpg,202212191536561671435416657.jpg','2022-12-15 20:14:27'),(21,'HUAWEI/华为 P50',7,'数码/手机','<p>网络类型：4G 全网通 &nbsp; &nbsp; &nbsp; &nbsp;</p><p>华为型号：P50 &nbsp; &nbsp; &nbsp; &nbsp;</p><p>品牌：Huawei/华为</p><p>售后服务：全国联保 &nbsp; &nbsp; &nbsp; &nbsp;</p><p>接口类型：Type-C &nbsp; &nbsp; &nbsp; &nbsp;</p><p>分辨率：FHD+ 2700 x 1224 像素</p><p>蓝牙版本：5.2CPU &nbsp; &nbsp; &nbsp; </p><p>核心数：八核机身 &nbsp; &nbsp; &nbsp; &nbsp;</p><p>颜色：曜金黑 雪域白 可可茶金</p><p>套餐类型：官方标配 套餐一 &nbsp; &nbsp; &nbsp; &nbsp;</p><p>后置摄像头：5000万+1300万+1200万 &nbsp; &nbsp; &nbsp; &nbsp;</p><p>生产企业：华为终端有限公司</p><p>存储容量：8+128GB 8+256GB &nbsp; &nbsp; &nbsp; </p><p>屏幕材质：OLED &nbsp; &nbsp; &nbsp; &nbsp;</p><p>最大光圈：F1.8</p><p>手机类型：智能手机 &nbsp; &nbsp; &nbsp; &nbsp;</p><p>摄像头类型：四摄像头（前一后三） &nbsp; &nbsp; &nbsp; &nbsp;</p><p>网络模式：双卡双待</p><p>电信设备进网许可证编号：02-D710-222168 &nbsp; &nbsp; &nbsp; &nbsp;</p><p>屏幕刷新率：90HZ &nbsp; &nbsp; &nbsp; &nbsp;</p><p>版本类型：中国大陆</p><p>操作系统：HarmonyOS &nbsp; &nbsp; &nbsp; &nbsp;</p><p>耳机插头类型：TYPE-CCPU &nbsp; &nbsp; &nbsp; &nbsp;</p><p>品牌：高通</p><p>上市时间：2021-09 &nbsp; &nbsp; &nbsp; &nbsp;</p><p>屏幕尺寸：6.5英寸 &nbsp; &nbsp; &nbsp; &nbsp;</p><p>电池容量：4100mAh</p><p>CPU型号：骁龙888 4G &nbsp; &nbsp; &nbsp; &nbsp;</p><p>解锁方式：屏下指纹 &nbsp; &nbsp; &nbsp; &nbsp;</p><p>是否支持无线充电：否</p><p>是否支持NFC：是 &nbsp; &nbsp; &nbsp; &nbsp;</p><p>充电功率：66W &nbsp; &nbsp; &nbsp; &nbsp;</p><p>前置摄像头像素：1300万像素</p><p>3C证书编号：2021011606406275</p>','4488',NULL,'202212191540261671435626633.jpg',0,NULL,0,NULL,1,'2022-12-19 16:15:53',NULL,'直屏影像旗舰新款智能手机鸿蒙手机拍照游戏通话新款',3,499,',202212191543491671435829644.jpg,202212191543521671435832446.jpg,202212191543541671435834565.jpg,202212191543561671435836899.jpg,202212191544001671435840564.jpg','2022-12-16 20:14:46'),(22,'HUAWEI/华为nova 10',7,'数码/手机','<p>网络类型：4G全网通</p><p>华为型号：NOVA 10</p><p>品牌：Huawei/华为</p><p>售后服务：全国联保</p><p>接口类型：Type-C</p><p>分辨率：FHD+ 2400 x 1080 像素</p><p>CPU核心数：八核机身</p><p>颜色：曜金黑 10号色 绮境森林 普罗旺斯</p><p>套餐类型：官方标配</p><p>后置摄像头：5000万像素超感知摄像头+ 800万像素超广角微距摄像头+ 200万人像虚化景深摄像头</p><p>生产企业：华为终端有限公司</p><p>存储容量：128GB 256GB</p><p>屏幕材质：OLED</p><p>手机类型：智能手机</p><p>摄像头类型：后置三摄+前置单摄</p><p>网络模式：双卡双待</p><p>电信设备进网许可证编号：02-D710-221088</p><p>屏幕刷新率：120Hz</p><p>版本类型：中国大陆</p><p>操作系统：HarmonyOS</p><p>耳机插头类型：TYPE-C</p><p>CPU品牌：高通</p><p>上市时间：2022-07</p><p>屏幕尺寸：6.67英寸</p><p>电池容量：4000mAh</p><p>CPU型号：高通骁龙&trade; 778G 4G</p><p>是否支持NFC：是</p><p>充电功率：66W</p><p>前置摄像头像素：60百万像素</p><p><span style=\"color: rgb(0, 0, 0);\">3C证书编号：2022011606459892</span></p>','2699',NULL,'202212191546221671435982868.jpg',0,NULL,1,'2022-12-19 20:17:44',1,'2022-12-19 16:15:51',NULL,'前置6000万镜头学生补贴轻薄鸿蒙66W快充游戏拍照摄影新款智能手机',6,197,',202212191548011671436081271.jpg,202212191548041671436084960.jpg,202212191548071671436087634.jpg,202212191548101671436090243.jpg','2022-12-17 20:14:51'),(23,'iPhone/苹果14',7,'数码/手机','<p>网络类型：无需合约版</p><p>品牌：Apple/苹果</p><p>Apple型号：iPhone 14</p><p>售后服务：全国联保</p><p>接口类型：Lightning</p><p>分辨率：2532x1170</p><p>机身颜色：蓝色 紫色 红色 星光色 午夜色 苹果14Plus（星光色） 苹果14Plus（午夜黑） 苹果14Plus（蓝色） 苹果14Plus（紫色） 苹果14Plus（红色） 苹果14Pro（深空黑色） 苹果14Pro（银色） 苹果14Pro（暗紫色） 苹果14Pro（金色） 苹果14Promax（深空黑色） 苹果14Promax（银色） 苹果14Promax（暗紫色） 苹果14Promax（金色）</p><p>套餐类型：官方标配</p><p>后置摄像头：1200万</p><p>生产企业：苹果</p><p>存储容量：128GB 256GB 512GB 1TB</p><p>屏幕材质：超视网膜 XDR 显示屏</p><p>网络模式：5G</p><p>电信设备进网许可证编号：00-8573-217559</p><p>操作系统：iOS</p><p>CPU品牌：Apple/苹果</p><p>屏幕尺寸：6.1英寸</p><p>机身厚度：7.8mm</p><p>CPU型号：A15 仿生芯片</p><p>是否支持无线充电：是</p><p><span style=\"color: rgb(0, 0, 0);\">3C证书编号：2021011606410117</span></p>','5399','5499','202212191552151671436335223.jpg',0,NULL,1,'2022-12-19 20:17:43',1,'2022-12-19 20:18:57',NULL,'国行正品5G全网通新品苹果14手机',7,197,',202212191553481671436428811.jpg,202212191553521671436432225.jpg,202212191553551671436435725.jpg,202212191553581671436438836.jpg','2022-12-18 20:14:55'),(24,'联想小新Air14 Plus',8,'数码/电脑','<p>品牌：Lenovo/联想</p><p>系列：小新</p><p>型号：小新 Air14</p><p>内存容量：16GB</p><p>硬盘容量：512G</p><p>固态硬盘CPU：AMD R5-6600HS</p><p>售后服务：全国联保</p><p>屏幕比例：16:10</p><p>显存容量：共享系统内存</p><p>光驱类型：无光驱</p><p>厚度：15.0mm(含)-18.0mm(不含)</p><p>颜色分类：R5 6600HS/16G/512G SSD/集显 . R7 6800HS/16G/512G SSD/集显</p><p>商品条形码：0000000000000</p><p>套餐类型：官方标配 +59元购 Howard蓝牙无线双模鼠标充电款(樱花白) +44元购 拯救者鼠标垫 +49元购 小新便携支架 +35元购 联想X3 Lite32G U盘 +94元购 小新新选双肩包</p><p>生产企业：联想</p><p>输入设备：触摸板</p><p>是否触摸屏：否</p><p>固态硬盘：512GB</p><p>能效等级：一级</p><p>是否超极本：是</p><p>笔记本电脑类型：轻薄本</p><p>显卡类型：核芯显卡</p><p>操作系统：Windows 11</p><p>适用场景：家庭影音 女性定位 轻薄便携 学生 商务办公 家庭娱乐 炒股理财</p><p>锂电池电芯数量：内置锂离子电池</p><p>通信技术类型：无线网卡 </p><p>蓝牙屏幕类型：IPS面板</p><p>是否PC平板二合一：否</p><p>CPU品牌：AMD</p><p>屏幕分辨率：2240x1400像素</p><p>屏幕尺寸：14英寸</p><p>屏幕刷新率：60Hz</p><p>保修期：24个月</p><p>3C证书编号：2020010902355796</p>','4799',NULL,'202212191557331671436653217.jpg',1,'2022-12-27 20:54:10',1,'2022-12-19 20:17:42',1,'2022-12-19 20:19:11',NULL,'锐龙6000系处理器标压14英寸2.2K护眼屏轻薄本笔记本电脑商务办公学生电脑',12,93,',202212191602171671436937668.jpg,202212191602201671436940971.jpg,202212191602231671436943255.jpg,202212191602251671436945289.jpg','2022-12-19 20:14:58'),(25,'HUAWEI/华为一体机电脑MateStation X',8,'数码/电脑','<p>品牌：Huawei/华为</p><p>型号：CZNM-W56T</p><p>售后服务：全国联保</p><p>适用品牌：华为</p><p>内存类型：DDR4</p><p>光驱类型：无光驱</p><p>颜色分类：皓月银 R5/16GB/512GB/指纹键盘/无线鼠标 深空灰 R5/16GB/512GB/指纹键盘/无线鼠标 皓月银 R7/16GB/512GB/指纹键盘/无线鼠标 深空灰 R7/16GB/512GB/指纹键盘/无线鼠标</p><p>台式机类型：高端家用</p><p>套餐类型：官方标配</p><p>生产企业：华为终端有限公司</p><p>是否触摸屏：是</p><p>能效等级：一级</p><p>操作系统：Windows 10家庭版 / Windows 11家庭版</p><p>上市时间：2021-09-13</p><p>屏幕尺寸：28.2英寸</p><p>内存容量：16GB</p><p>能效备案号：202106-27-11027226-462651443728322560</p><p>保修期：36个月</p><p>固态硬盘容量：512GB</p><p>硬盘容量：512GB</p><p>显存容量：0MB</p><p>3C证书编号：2021010901402658</p>','9999',NULL,'202212191609021671437342763.jpg',0,NULL,1,'2022-12-19 20:03:20',1,'2022-12-19 16:15:46',NULL,'28.2英寸窄边框4K+触控全面屏 五代AMD R5/R7+16GB+512GB SSD WIFI 6',9,97,',202212191609101671437350416.jpg,202212191609141671437354670.jpg,202212191609171671437357902.jpg,202212191609201671437360323.jpg','2022-12-20 20:15:03'),(26,'OPPO Pad平板电脑',9,'数码/平板','<p>网络类型：WIFI</p><p>品牌：OPPO</p><p>型号：Pad</p><p>内存容量：8GB 6GB</p><p>存储容量：6GB+128GB 8GB+128GB 6GB+256GB 8GB+256GB</p><p>售后服务：全国联保</p><p>分辨率：2560×1600</p><p>颜色分类：耀夜黑 极光紫</p><p>CPU主频：3.2GHz</p><p>套餐类型：官方标配</p><p>器型号：高通骁龙870</p><p>触摸屏类型：电容屏</p><p>生产企业：OPPO</p><p>电信设备进网许可证编号：00-D124-228315</p><p>处理器构架：Kryo 585</p><p>处理器型号信息：高通骁龙870（SDM870）</p><p>屏幕尺寸：11寸</p><p>操作系统：ColorOS 12.0</p><p>是否PC平板二合一：否</p><p>处理器品牌：高通骁龙</p><p>通讯类型：不可插卡</p><p>保修期：12个月</p><p><span style=\"color: rgb(0, 0, 0);\">3C证书编号：2021010902443570</span></p>','1999',NULL,'202212210357331671566253298.jpg',1,'2022-12-27 20:54:08',0,NULL,0,NULL,NULL,'平板电脑骁龙870处理器新款网课学习办公绘画游戏商务专用',3,597,',202212210404231671566663615.jpg,202212210404271671566667599.jpg,202212210404351671566675619.jpg,202212210404401671566680276.jpg,202212210404451671566685786.jpg','2022-12-21 20:15:06'),(27,'2022新款vivo Pad平板电脑',9,'数码/平板','<p>网络类型：WIFI</p><p>品牌：vivo</p><p>型号：Pad</p><p>存储类型：UFS3.1</p><p>内存容量：8GB</p><p>存储容量：8GB+128GB 8GB+256GB</p><p>售后服务：全国联保</p><p>接口类型：Type-C</p><p>分辨率：2560*1600</p><p>核心数：八核心</p><p>颜色分类：天蓝色 深空灰 雪青紫</p><p>CPU主频：3.2GHz</p><p>套餐类型：官方标配</p><p>处理器型号：高通骁龙870</p><p>触摸屏类型：电容式多点触控</p><p>像素：前置800万</p><p>生产企业：vivo</p><p>电信设备进网许可证编号：空</p><p>处理器构架：Kryo585</p><p>处理器型号信息：高通骁龙870</p><p>屏幕尺寸：11英寸</p><p>热门功能：跨屏协同办公、高刷屏</p><p>操作系统：OriginOS HD（基于Android开发）</p><p>后置摄像头像素：1300万主摄+800万超广角</p><p>是否PC平板二合一：否</p><p>处理器品牌：高通</p><p>智能类型：其他智能</p><p>上市时间：2022-04-11</p><p>通讯类型：不可插卡</p><p>保修期：12个月</p><p><span style=\"color: rgb(0, 0, 0);\">3C证书编号：2022010902446193</span></p>','2499',NULL,'202212210402531671566573647.jpg',0,'2022-12-27 20:53:33',1,'2022-12-22 00:38:18',1,'2022-12-22 00:38:12',NULL,'新款超薄笔记本电脑11英寸手机平板优惠学习专用游戏办公全网通大学生',5,995,',202212210408001671566880130.jpg,202212210408031671566883885.jpg,202212210408081671566888800.jpg,202212210408121671566892830.jpg,202212210408161671566896749.jpg','2022-12-22 20:15:10'),(28,'大疆 DJI Osmo Action 3',10,'数码/相机','<p><span style=\"color: rgb(0, 0, 0);\">品牌：DJI/大疆</span></p><p>型号：Osmo Action 3</p><p>颜色分类：全能套装 标准套装 滑雪套装 潜水套装 旅行套装 骑行套装 标准套装 + DJI Mic 全能套装 + 随心换 2 年版 标准套装 + 随心换 2 年版 滑雪套装 + 随心换 2 年版 潜水套装 + 随心换 2 年版 旅行套装 + 随心换 2 年版 骑行套装 + 随心换 2 年版 标准套装 + DJI Mic + 随心换 2 年版 全能套装 + 随心换 1 年版 标准套装 + 随心换 1 年版 滑雪套装 + 随心换 1 年版 潜水套装 + 随心换 1 年版 旅行套装 + 随心换 1 年版 骑行套装 + 随心换 1 年版 标准套装 + DJI Mic + 随心换 1 年版</p><p>套餐类型：官方标配 256G内存卡 128G内存卡</p><p>生产企业：DJI 大疆</p><p>保修期：12个月</p>','2299',NULL,'202212210422261671567746836.jpg',1,'2022-12-27 20:54:11',0,'2022-12-21 04:25:19',0,NULL,NULL,'长续航高清4K运动相机 手持vlog录像神器 摩托车骑行潜水滑雪头戴式摄像机',4,996,',202212210424491671567889686.jpg,202212210424531671567893686.jpg,202212210424571671567897656.jpg,202212210425011671567901628.jpg,202212210425061671567906628.jpg','2022-12-23 20:15:25'),(29,'Xiaomi/小米平板5',9,'数码/平板','<p>网络类型：WIFI666</p><p>品牌：MIUI/小米</p><p>型号：小米平板5</p><p>内存容量：6GB</p><p>存储容量：128GB 256GB 8GB+256GB 8GB+128GB</p><p>颜色分类：前黑后黑 前黑后白 前黑后绿</p><p>CPU主频：2.96GHz</p><p>套餐类型：官方标配</p><p>生产企业：小米</p><p>电信设备进网许可证编号：无</p><p>处理器构架：Kyro485</p><p>处理器型号信息：骁龙860</p><p>通讯类型：不可插卡</p><p>保修期：12个月</p><p><span style=\"color: rgb(0, 0, 0);\">3C证书编号：2021010902404823</span></p><p><img src=\"http://localhost:8080/image/goods/details/202212250010161671898216376.jpg\" style=\"\" width=\"645\" height=\"998.4387600806451\"></p><p><img src=\"http://localhost:8080/image/goods/details/202212250010251671898225770.jpg\" style=\"\" width=\"644\" height=\"996.9051356589148\"></p><p><img src=\"http://localhost:8080/image/goods/details/202212250010361671898236444.jpg\" style=\"\" width=\"644\" height=\"996.904024767802\"></p><p><img src=\"http://localhost:8080/image/goods/details/202212250010451671898245599.jpg\" style=\"\" width=\"645\" height=\"998.457538167939\"></p>','1999','2222','202212220048111671641291684.jpg',0,'2022-12-27 20:53:21',1,'2022-12-22 01:38:53',0,NULL,NULL,'骁龙学生学习绘画商务办公游戏娱乐高清护眼认证专用平板电脑',3,997,',202212220138341671644314742.jpg,202212220138391671644319921.jpg,202212220138431671644323770.jpg,202212220138471671644327590.jpg','2022-12-24 20:15:30'),(30,'海尔无线吸尘器',11,'数码/生活家电','<p><span style=\"color: rgb(0, 0, 0);\">吸尘器品牌：Haier/海尔</span></p><p>型号：HZ-G581G</p><p>功能：吸尘</p><p>颜色分类：石墨灰 晶釉蓝</p><p>电压：≤36V(含)</p><p>线长：无线</p><p>生产企业：青岛海尔成套家电服务有限公司</p><p>储尘类型：旋风尘盒</p><p>附加功能：除螨 拖地</p><p>吸尘器类型：手持式</p><p>电机类型：无刷电机特殊</p><p>吸嘴类型：沙发刷 角落折叠刷头 除螨刷头 扁吸嘴 擦吸二合一地刷 防静电地面刷 宠物刷</p><p>最大噪音：80dB</p><p>转速：80000转/分钟</p><p>采购地：中国大陆</p><p>保修期：12个月</p><p>续航时间：60</p><p>吸嘴数量：4个</p><p>电器基站功能：充电</p><p>最大吸入功率：20000Pa</p>','1299',NULL,'202301010121531672507313198.jpg',0,NULL,0,NULL,0,NULL,NULL,'家用车载一体洗擦拖地小型大吸力手持吸尘除螨机G5',0,1000,',202301010122021672507322105.jpg,202301010122071672507327233.jpg,202301010122121672507332095.jpg,202301010122171672507337155.jpg,202301010122211672507341755.jpg','2023-01-01 01:21:41');

/*Table structure for table `t_order` */

DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addDate` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `customerId` int(11) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `phoneNum` varchar(100) DEFAULT NULL,
  `customerName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

/*Data for the table `t_order` */

insert  into `t_order`(`id`,`addDate`,`state`,`price`,`customerId`,`address`,`phoneNum`,`customerName`) values (28,'2022-12-31 20:41:01',2,'14487',34,'山西省运城市绛县卫庄镇23号','13458202555','冯浩'),(31,'2022-12-31 20:41:43',3,'2299',34,'山西省运城市绛县卫庄镇23号','13458202555','冯浩'),(32,'2022-12-31 22:32:17',1,'4298',34,'宁夏回族自治区固原市泾源县六盘山镇12号','13837331638','冯浩'),(33,'2023-01-01 01:06:09',4,'20197',34,'宁夏回族自治区固原市泾源县六盘山镇12号','13837331638','冯浩');

/*Table structure for table `t_order_goods` */

DROP TABLE IF EXISTS `t_order_goods`;

CREATE TABLE `t_order_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` int(11) DEFAULT NULL,
  `goodsId` int(11) DEFAULT NULL,
  `orderId` int(11) DEFAULT NULL,
  `appraiseState` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `orderId` (`orderId`),
  KEY `goodsId` (`goodsId`),
  CONSTRAINT `t_order_goods_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `t_order` (`id`) ON DELETE CASCADE,
  CONSTRAINT `t_order_goods_ibfk_2` FOREIGN KEY (`goodsId`) REFERENCES `t_goods` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

/*Data for the table `t_order_goods` */

insert  into `t_order_goods`(`id`,`num`,`goodsId`,`orderId`,`appraiseState`) values (38,1,25,28,0),(39,1,21,28,0),(44,1,28,31,0),(45,1,26,32,0),(46,1,28,32,0),(47,1,25,33,1),(48,1,24,33,1),(49,1,23,33,1);

/*Table structure for table `t_type_big` */

DROP TABLE IF EXISTS `t_type_big`;

CREATE TABLE `t_type_big` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `sortNum` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `t_type_big` */

insert  into `t_type_big`(`id`,`name`,`sortNum`) values (1,'美食',2),(13,'数码',1);

/*Table structure for table `t_type_small` */

DROP TABLE IF EXISTS `t_type_small`;

CREATE TABLE `t_type_small` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `bigTypeId` int(11) DEFAULT NULL,
  `sortNum` int(11) DEFAULT NULL,
  `bigTypeName` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bigTypeId` (`bigTypeId`),
  CONSTRAINT `t_type_small_ibfk_1` FOREIGN KEY (`bigTypeId`) REFERENCES `t_type_big` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `t_type_small` */

insert  into `t_type_small`(`id`,`name`,`bigTypeId`,`sortNum`,`bigTypeName`) values (7,'手机',13,1,'数码'),(8,'电脑',13,2,'数码'),(9,'平板',13,3,'数码'),(10,'相机',13,4,'数码'),(11,'生活家电',13,5,'数码'),(12,'大家电',13,6,'数码'),(13,'个人家电',13,7,'数码'),(14,'休闲零食',1,1,'美食'),(15,'粮油米面',1,2,'美食'),(16,'茗茶冲饮',1,3,'美食'),(17,'各地特产',1,4,'美食'),(18,'各类坚果',1,5,'美食'),(19,'生鲜蔬果',1,6,'美食');

/*Table structure for table `t_valuation` */

DROP TABLE IF EXISTS `t_valuation`;

CREATE TABLE `t_valuation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderGoodsId` int(11) DEFAULT NULL,
  `rate` int(11) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `addDate` datetime DEFAULT NULL,
  `goodsId` int(11) DEFAULT NULL,
  `customerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `orderGoodsId` (`orderGoodsId`),
  CONSTRAINT `t_valuation_ibfk_1` FOREIGN KEY (`orderGoodsId`) REFERENCES `t_order_goods` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `t_valuation` */

insert  into `t_valuation`(`id`,`orderGoodsId`,`rate`,`content`,`addDate`,`goodsId`,`customerId`) values (19,49,5,'还行','2023-01-01 01:10:19',23,34),(20,48,5,'还行','2023-01-01 01:10:26',24,34),(21,47,5,'不错','2023-01-01 01:10:31',25,34);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

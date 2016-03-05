/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : mangobbs

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2013-04-19 19:14:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `commodity`
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `type` int(11) NOT NULL DEFAULT '0',
  `introduce` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `charm` int(11) NOT NULL,
  `imgpath` varchar(255) NOT NULL,
  `showmain` int(11) NOT NULL,
  `sellsum` int(10) unsigned zerofill NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES ('9', '银泰网300-30优惠券', '0', '银泰网300-30优惠券 ', '32', '34', 'images/shop/face/face9.jpg', '1', '0000000001');
INSERT INTO `commodity` VALUES ('10', '属于您的时尚圈', '1', '属于您的时尚圈', '3', '65', 'images/shop/flower/flower1.jpg', '1', '0000000003');
INSERT INTO `commodity` VALUES ('11', '将军出征礼包', '1', '将军出征礼包', '345', '45', 'images/shop/flower/flower2.jpg', '1', '0000000001');
INSERT INTO `commodity` VALUES ('12', '360可爱安仔U盘', '1', '360可爱安仔U盘', '54325', '54325', 'images/shop/flower/flower3.jpg', '1', '0000000000');
INSERT INTO `commodity` VALUES ('13', '360可爱安仔U盘', '1', '360可爱安仔U盘', '4', '5426', 'images/shop/flower/flower4.jpg', '1', '0000000000');
INSERT INTO `commodity` VALUES ('14', '暗黑破坏神III-典藏版', '1', '暗黑破坏神III-典藏版', '2', '34', 'images/shop/flower/flower5.jpg', '1', '0000000000');
INSERT INTO `commodity` VALUES ('15', '魔之精灵超萌徽章套装', '1', '魔之精灵超萌徽章套装', '3213', '12321', 'images/shop/flower/flower6.jpg', '1', '0000000000');
INSERT INTO `commodity` VALUES ('16', '仙落凡尘特供鼠标', '1', '仙落凡尘特供鼠标', '214354', '345', 'images/shop/flower/flower7.jpg', '1', '0000000000');
INSERT INTO `commodity` VALUES ('17', '暗黑破坏神III-典藏版', '1', '暗黑破坏神III-典藏版', '32131', '23132', 'images/shop/flower/flower8.jpg', '1', '0000000000');
INSERT INTO `commodity` VALUES ('18', '将军出征礼包', '1', '将军出征礼包', '435435', '564', 'images/shop/flower/flower9.jpg', '1', '0000000000');
INSERT INTO `commodity` VALUES ('19', '秦美人大众礼包 ', '0', '秦美人大众礼包 ', '4321', '0', 'images/shop/face/face10.jpg', '1', '0000000000');
INSERT INTO `commodity` VALUES ('20', '榜眼礼包', '0', '榜眼礼包', '4321', '0', 'images/shop/face/face11.jpg', '1', '0000000000');
INSERT INTO `commodity` VALUES ('21', '聚尚名品折扣 198-20优惠券', '0', '聚尚名品折扣 198-20优惠券', '4321', '0', 'images/shop/face/face12.jpg', '1', '0000000001');
INSERT INTO `commodity` VALUES ('22', '买茶网299-40优惠券', '0', '买茶网299-40优惠券 ', '4321', '0', 'images/shop/face/face13.jpg', '1', '0000000000');
INSERT INTO `commodity` VALUES ('23', '国美5元代金券', '0', '国美5元代金券', '4321', '0', 'images/shop/face/face14.jpg', '1', '0000000000');
INSERT INTO `commodity` VALUES ('24', '银泰网300-30优惠券', '0', '银泰网300-30优惠券', '4321', '0', 'images/shop/face/face15.jpg', '1', '0000000000');
INSERT INTO `commodity` VALUES ('25', '植物僵尸23服专属', '0', '植物僵尸23服专属', '4321', '0', 'images/shop/face/face16.jpg', '1', '0000000000');
INSERT INTO `commodity` VALUES ('26', '仙盟至尊礼包', '0', '仙盟至尊礼包', '4321', '0', 'images/shop/face/face17.jpg', '1', '0000000000');
INSERT INTO `commodity` VALUES ('27', '神仙道可爱玩偶', '0', '神仙道可爱玩偶', '4321', '0', 'images/shop/face/face18.jpg', '1', '0000000000');
INSERT INTO `commodity` VALUES ('28', '360水杯', '0', '360水杯', '4321', '0', 'images/shop/face/face19.jpg', '1', '0000000000');
INSERT INTO `commodity` VALUES ('29', '魔之精灵超萌徽章套装', '0', '魔之精灵超萌徽章套装', '4321', '0', 'images/shop/face/face20.jpg', '1', '0000000000');
INSERT INTO `commodity` VALUES ('30', '360哈哈O(∩_∩)O哈哈~', '0', '360哈哈O(∩_∩)O哈哈~', '345', '45', 'images/shop/face/face21.jpg', '1', '0000000000');
INSERT INTO `commodity` VALUES ('34', 'CKtest', '0', 'test', '12', '23', 'images/shop/face/shop20130118203006.jpg', '0', '0000000000');

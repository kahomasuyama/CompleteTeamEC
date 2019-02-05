set names utf8;
set foreign_key_checks=0;

drop database if exists yellow;
create database if not exists yellow;

use yellow;

CREATE TABLE user_info(

	id INT PRIMARY KEY NOT NULL auto_increment comment "ID",
	user_id VARCHAR(16) NOT NULL unique comment "ユーザーID",
	password VARCHAR(16) NOT NULL comment "パスワード",
	family_name VARCHAR(32) NOT NULL comment "姓",
	first_name VARCHAR(32) NOT NULL comment "名",
	family_name_kana VARCHAR(32) NOT NULL comment "姓かな",
	first_name_kana VARCHAR(32) NOT NULL comment "名かな",
	sex TINYINT NOT NULL DEFAULT 0 comment "性別",
	email VARCHAR(32) NOT NULL comment "メールアドレス",
	status TINYINT NOT NULL DEFAULT 0 comment "ステータス",
	logined TINYINT NOT NULL DEFAULT 0 comment "ログインフラグ",
	regist_date DATETIME NOT NULL comment "登録日",
	update_date DATETIME comment "更新日"

)
DEFAULT charset=utf8 comment="会員情報テーブル";

INSERT INTO user_info values
(1, "guest", "guest", "インターノウス", "ゲストユーザー", "いんたーのうす", "げすとゆーざー", 0, "guest@gmail.com", 0, 0, now(), now()),
(2, "guest2", "guest2", "インターノウス", "ゲストユーザー2", "いんたーのうす", "げすとゆーざー2", 0, "guest2@gmail.com", 0, 0, now(), now()),
(3, "guest3", "guest3", "インターノウス", "ゲストユーザー2", "いんたーのうす", "げすとゆーざー2", 0, "guest2@gmail.com", 0, 0, now(), now()),
(4, "guest4", "guest4", "インターノウス", "ゲストユーザー2", "いんたーのうす", "げすとゆーざー2", 0, "guest2@gmail.com", 0, 0, now(), now()),
(5, "guest5", "guest5", "インターノウス", "ゲストユーザー2", "いんたーのうす", "げすとゆーざー2", 0, "guest2@gmail.com", 0, 0, now(), now()),
(6, "guest6", "guest6", "インターノウス", "ゲストユーザー2", "いんたーのうす", "げすとゆーざー2", 0, "guest2@gmail.com", 0, 0, now(), now()),
(7, "guest7", "guest7", "インターノウス", "ゲストユーザー2", "いんたーのうす", "げすとゆーざー2", 0, "guest2@gmail.com", 0, 0, now(), now()),
(8, "guest8", "guest8", "インターノウス", "ゲストユーザー2", "いんたーのうす", "げすとゆーざー2", 0, "guest2@gmail.com", 0, 0, now(), now()),
(9, "guest9", "guest9", "インターノウス", "ゲストユーザー2", "いんたーのうす", "げすとゆーざー2", 0, "guest2@gmail.com", 0, 0, now(), now()),
(10, "guest10", "guest10", "インターノウス", "ゲストユーザー2", "いんたーのうす", "げすとゆーざー2", 0, "guest2@gmail.com", 0, 0, now(), now()),
(11, "guest11", "guest11", "インターノウス", "ゲストユーザー2", "いんたーのうす", "げすとゆーざー2", 0, "guest2@gmail.com", 0, 0, now(), now()),
(12, "guest12", "guest12", "インターノウス", "ゲストユーザー2", "いんたーのうす", "げすとゆーざー2", 0, "guest2@gmail.com", 0, 0, now(), now())

CREATE TABLE product_info(
	id INT PRIMARY KEY NOT NULL auto_increment comment "ID",
	product_id INT NOT NULL unique,
	product_name VARCHAR(100)
)
DEFAULT charset=utf8 comment="商品情報テーブル";
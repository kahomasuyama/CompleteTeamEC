SET names utf8;
SET foreign_key_checks=0;

DROP database IF EXISTS yellow;
CREATE database IF NOT EXISTS yellow;

use yellow;

CREATE TABLE user_info
(

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

INSERT INTO user_info VALUES
(1, "guest", "guest", "インターノウス", "ゲストユーザー", "いんたーのうす", "げすとゆーざー", 0, "guest@gmail.com", 0, 0, now(), now()),
(2, "guest2", "guest2", "インターノウス", "ゲストユーザー2", "いんたーのうす", "げすとゆーざー2", 0, "guest2@gmail.com", 0, 0, now(), now()),
(3, "guest3", "guest3", "インターノウス", "ゲストユーザー3", "いんたーのうす", "げすとゆーざー3", 0, "guest3@gmail.com", 0, 0, now(), now()),
(4, "guest4", "guest4", "インターノウス", "ゲストユーザー4", "いんたーのうす", "げすとゆーざー4", 0, "guest4@gmail.com", 0, 0, now(), now()),
(5, "guest5", "guest5", "インターノウス", "ゲストユーザー5", "いんたーのうす", "げすとゆーざー5", 0, "guest5@gmail.com", 0, 0, now(), now()),
(6, "guest6", "guest6", "インターノウス", "ゲストユーザー6", "いんたーのうす", "げすとゆーざー6", 0, "guest6@gmail.com", 0, 0, now(), now()),
(7, "guest7", "guest7", "インターノウス", "ゲストユーザー7", "いんたーのうす", "げすとゆーざー7", 0, "guest7@gmail.com", 0, 0, now(), now()),
(8, "guest8", "guest8", "インターノウス", "ゲストユーザー8", "いんたーのうす", "げすとゆーざー8", 0, "guest8@gmail.com", 0, 0, now(), now()),
(9, "guest9", "guest9", "インターノウス", "ゲストユーザー9", "いんたーのうす", "げすとゆーざー9", 0, "guest9@gmail.com", 0, 0, now(), now()),
(10, "guest10", "guest10", "インターノウス", "ゲストユーザー10", "いんたーのうす", "げすとゆーざー10", 0, "guest10@gmail.com", 0, 0, now(), now()),
(11, "guest11", "guest11", "インターノウス", "ゲストユーザー11", "いんたーのうす", "げすとゆーざー11", 0, "guest11@gmail.com", 0, 0, now(), now()),
(12, "guest12", "guest12", "インターノウス", "ゲストユーザー12", "いんたーのうす", "げすとゆーざー12", 0, "guest12@gmail.com", 0, 0, now(), now());

CREATE TABLE product_info
(

	id INT PRIMARY KEY NOT NULL auto_increment comment "ID",
	product_id INT NOT NULL unique,
	product_name VARCHAR(100) unique NOT NULL comment "商品名",
	product_name_kana VARCHAR(100) unique NOT NULL comment "商品名かな",
	product_description VARCHAR(255) NOT NULL comment "商品詳細",
	category_id INT NOT NULL NOT NULL comment "カテゴリID",
	price INT comment "価格",
	image_file_path VARCHAR(100) comment "画像ファイルパス",
	image_file_name VARCHAR(50) comment "画像ファイル名",
	release_date DATETIME NOT NULL comment "発売年月",
	release_company VARCHAR(50) comment "発売会社",
	status TINYINT NOT NULL DEFAULT 0 comment "登録日",
	regist_date DATETIME NOT NULL comment "登録日",
	update_date datetime comment "更新日",
	foreign key(category_id) references m_category(category_id)

)
DEFAULT charset=utf8 comment="商品情報テーブル";

INSERT INTO product_info VALUES
( 1, 1,"Wash House Thig","うぉっしゅはうすてぃんぐ","洋楽１の商品詳細",2,100,"./images","洋楽1.jpg",now(),"発売会社",0,now(),now()),
( 2, 2,"洋楽２","ようがく２","洋楽２の商品詳細",2,200,"./images","洋楽2.jpg",now(),"発売会社",0,now(),now()),
( 3, 3,"洋楽３","ようがく３","洋楽３の商品詳細",2,300,"./images","洋楽3.jpg",now(),"発売会社",0,now(),now()),
( 4, 4,"洋楽４","ようがく４","洋楽４の商品詳細",2,100,"./images","洋楽4.jpg",now(),"発売会社",0,now(),now()),
( 5, 5,"洋楽５","ようがく５","洋楽５の商品詳細",2,100,"./images","洋楽5.jpg",now(),"発売会社",0,now(),now()),
( 6, 6, "アンビバレント","あんびばれんと","邦楽１の商品詳細",3,100,"./images","邦楽1.jpg",now(),"発売会社",0,now(),now()),
( 7, 7, "Neyagawa City Pop","ねやがわしてぃぽっぷ","邦楽２の商品詳細",3,100,"./images","邦楽2.jpg",now(),"発売会社",0,now(),now()),
( 8, 8, "イチブトゼンブ","いちぶとぜんぶ","邦楽３の商品詳細",3,100,"./images","邦楽3.jpg",now(),"発売会社",0,now(),now()),
( 9, 9, "Ambitions","あんびしょんず","邦楽４の商品詳細",3,100,"./images","邦楽4.jpg",now(),"発売会社",0,now(),now()),
( 10, 10, "おジャ魔女どれみドッカ〜ン","おじゃまじょどれみどっか〜ん","邦楽５の商品詳細",3,100,"./images","邦楽5.jpg",now(),"発売会社",0,now(),now()),
( 11, 11, "BOHUMIYAN RHAPSODY","ぼふみやん らぷそでぃー","サウンドトラック１の商品詳細",4,100,"./images","soundtrack1.jpg",now(),"発売会社",0,now(),now()),
( 12, 12, "マリオ オリジナルサントラ","まりおおりじなるさんとら","サウンドトラック２の商品詳細",4,100,"./images","soundtrack2.jpg",now(),"発売会社",0,now(),now()),
( 13, 13, "フミーポピンズ","ふみーぽぴんず","サウンドトラック３の商品詳細",4,100,"./images","soundtrack3.jpg",now(),"発売会社",0,now(),now()),
( 14, 14, "GREITEST SHOWMAN","ぐれいてすとしょーまん","サウンドトラック４の商品詳細",4,100,"./images","soundtrack4.jpg",now(),"発売会社",0,now(),now()),
( 15, 15, "Hummy Potter","ふみーぽったー","サウンドトラック５の商品詳細",4,100,"./images","soundtrack5.jpg",now(),"発売会社",0,now(),now());

CREATE TABLE cart_info
(

	id INT PRIMARY KEY NOT NULL auto_increment comment "ID",
	user_id VARCHAR(16) NOT NULL comment "ユーザーID",
	temp_user_id VARCHAR(16) comment "仮ユーザーID",
	product_id INT NOT NULL comment "商品ID",
	product_count INT NOT NULL comment "個数",
	price INT NOT NULL comment "金額",
	regist_date DATETIME NOT NULL comment "登録日",
	update_date DATETIME comment "更新日"

)
DEFAULT charset=utf8 comment="カート情報テーブル";

CREATE TABLE purchase_history_info
(

	id INT PRIMARY KEY NOT NULL auto_increment comment "ID",
	user_id VARCHAR(16) NOT NULL comment "ユーザーID",
	product_id INT NOT NULL comment "商品ID",
	product_count INT NOT NULL comment "個数",
	price INT NOT NULL comment "金額",
	destination_id INT NOT NULL comment "宛先情報ID",
	regist_date DATETIME NOT NULL comment "登録日",
	update_date DATETIME NOT NULL comment "更新日",
	FOREIGN KEY(product_id) REFERENCES product_info(product_id)

)
DEFAULT charset=utf8 comment="商品情報テーブル";

CREATE TABLE destination_info
(

	id INT PRIMARY KEY NOT NULL auto_increment comment "ID",
	user_id VARCHAR(16) NOT NULL comment "ユーザーID",
	family_name VARCHAR(32) NOT NULL comment "姓",
	first_name VARCHAR(32) NOT NULL comment "名",
	family_name_kana VARCHAR(32) NOT NULL comment "姓かな",
	first_name_kana VARCHAR(32) NOT NULL comment "名かな",
	email VARCHAR(32) NOT NULL comment "メールアドレス",
	tel_number VARCHAR(13) NOT NULL comment "電話番号",
	user_address VARCHAR(50) NOT NULL comment "住所",
	regist_date DATETIME NOT NULL comment "登録日",
	update_date DATETIME comment "更新日"

)
DEFAULT charset=utf8 comment="購入履歴情報テーブル";

INSERT INTO destination_info VALUES
(1, "guest", "インターノウス", "テストユーザー", "いんたーのうす", "てすとゆーざー", "guest@internous.co.jp", "080-1234-5678", "東京都千代田区三番町１ー１　ＫＹ三番町ビル１Ｆ", now(), now());

CREATE TABLE m_category
(

	id INT PRIMARY KEY NOT NULL auto_increment comment "ID",
	category_id INT NOT NULL UNIQUE comment "カテゴリID",
	category_name VARCHAR(20) NOT NULL UNIQUE comment "カテゴリ名",
	category_description VARCHAR(100) comment "カテゴリ詳細",
	insert_date datetime NOT NULL comment "登録日",
	update_date datetime comment "更新日"

)DEFAULT charset=utf8 comment="カテゴリマスタテーブル";
INSERT INTO m_category VALUES
(1,1,"全てのカテゴリー","洋楽、邦楽、サウンドトラック全てのカテゴリーが対象となります",now(), null),
(2,2,"洋楽","洋楽に関するカテゴリーが対象となります",now(),null),
(3,3,"邦楽","邦楽に関するカテゴリーが対象となります",now(),null),
(4,4, "サウンドトラック", "サウンドトラックに関するカテゴリーが対象となります",now(),null);

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






CREATE TABLE product_inf
(

	id INT PRIMARY KEY NOT NULL auto_increment comment "ID",
	product_id INT NOT NULL unique,
	product_name VARCHAR(100) unique NOT NULL comment "商品名",
	product_name_kana VARCHAR(100) NOT NULL comment "商品名かな",
	product_description VARCHAR(255) NOT NULL comment "商品詳細",
	category_id INT NOT NULL NOT NULL comment "カテゴリID",
	price INT comment "価格",
	image_file_path VARCHAR(100) comment "画像ファイルパス",
	image_file_name VARCHAR(50) comment "画像ファイル名",
	release_date DATETIME NOT NULL comment "発売年月",
	release_company VARCHAR(50) comment "発売会社",
	status TINYINT NOT NULL comment "登録日",
	regist_date DATETIME NOT NULL comment "登録日",
	update_date datetime comment "更新日",
	foreign key(category_id) references m_category(category_id)

)
DEFAULT charset=utf8 comment="商品情報テーブル";

INSERT INTO product_info VALUES
( 1, 1,"本１","ほん１","本１の商品詳細",2,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 2, 2,"本２","ほん２","本２の商品詳細",2,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 3, 3,"本３","ほん３","本３の商品詳細",2,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 4, 4,"本４","ほん４","本４の商品詳細",2,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 5, 5,"本５","ほん５","本５の商品詳細",2,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 6, 6,"本６","ほん６","本６の商品詳細",2,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 7, 7,"本７","ほん７","本７の商品詳細",2,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 8, 8,"本８","ほん８","本８の商品詳細",2,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 9, 9,"本９","ほん９","本９の商品詳細",2,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 10, 10,"本１０","ほん１０","本１０の商品詳細",2,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 11, 11,"本１１","ほん１１","本１１の商品詳細",2,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 12, 12,"本１２","ほん１２","本１２の商品詳細",2,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 13, 13,"本１３","ほん１３","本１３の商品詳細",2,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 14, 14,"本１４","ほん１４","本１４の商品詳細",2,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 15, 15,"本１５","ほん１５","本１５の商品詳細",2,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 16, 16,"本１６","ほん１６","本１６の商品詳細",2,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 17, 17,"本１７","ほん１７","本１７の商品詳細",2,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 18, 18,"本１８","ほん１８","本１８の商品詳細",2,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 19, 19,"本１９","ほん１９","本１９の商品詳細",2,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 20, 20,"本２０","ほん２０","本２０の商品詳細",2,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 21, 21,"本２１","ほん２１","本２１の商品詳細",2,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 22, 22,"本２２","ほん２２","本２２の商品詳細",2,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 23, 23,"本２３","ほん２３","本２３の商品詳細",2,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 24, 24,"本２４","ほん２４","本２４の商品詳細",2,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 25, 25,"本２５","ほん２５","本２５の商品詳細",2,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 26, 26,"本２６","ほん２６","本２６の商品詳細",2,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 27, 27,"本２７","ほん２７","本２７の商品詳細",2,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 28,28,"家電・パソコン２８","かでん・ぱそこん２８","家電・パソコン２８の商品詳細",3,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 29,29,"家電・パソコン２９","かでん・ぱそこん２９","家電・パソコン２９の商品詳細",3,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 30,30,"家電・パソコン３０","かでん・ぱそこん３０","家電・パソコン３０の商品詳細",3,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 31,31,"家電・パソコン３１","かでん・ぱそこん３１","家電・パソコン３１の商品詳細",3,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 32,32,"家電・パソコン３２","かでん・ぱそこん３２","家電・パソコン３２の商品詳細",3,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 33,33,"家電・パソコン３３","かでん・ぱそこん３３","家電・パソコン３３の商品詳細",3,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 34,34,"家電・パソコン３４","かでん・ぱそこん３４","家電・パソコン３４の商品詳細",3,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 35,35,"家電・パソコン３５","かでん・ぱそこん３５","家電・パソコン３５の商品詳細",3,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 36,36,"家電・パソコン３６","かでん・ぱそこん３６","家電・パソコン３６の商品詳細",3,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 37,37,"家電・パソコン３７","かでん・ぱそこん３７","家電・パソコン３７の商品詳細",3,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 38,38,"家電・パソコン３８","かでん・ぱそこん３８","家電・パソコン３８の商品詳細",3,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 39,39,"家電・パソコン３９","かでん・ぱそこん３９","家電・パソコン３９の商品詳細",3,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 40,40,"家電・パソコン４０","かでん・ぱそこん４０","家電・パソコン４０の商品詳細",3,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 41,41,"家電・パソコン４１","かでん・ぱそこん４１","家電・パソコン４１の商品詳細",3,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 42,42,"家電・パソコン４２","かでん・ぱそこん４２","家電・パソコン４２の商品詳細",3,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 43,43,"家電・パソコン４３","かでん・ぱそこん４３","家電・パソコン４３の商品詳細",3,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 44,44,"家電・パソコン４４","かでん・ぱそこん４４","家電・パソコン４４の商品詳細",3,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 45,45,"家電・パソコン４５","かでん・ぱそこん４５","家電・パソコン４５の商品詳細",3,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 46,46,"家電・パソコン４６","かでん・ぱそこん４６","家電・パソコン４６の商品詳細",3,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 47,47,"家電・パソコン４７","かでん・ぱそこん４７","家電・パソコン４７の商品詳細",3,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 48,48,"家電・パソコン４８","かでん・ぱそこん４８","家電・パソコン４８の商品詳細",3,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 49,49,"家電・パソコン４９","かでん・ぱそこん４９","家電・パソコン４９の商品詳細",3,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 50,50,"家電・パソコン５０","かでん・ぱそこん５０","家電・パソコン５０の商品詳細",3,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 51,51,"家電・パソコン５１","かでん・ぱそこん５１","家電・パソコン５１の商品詳細",3,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 52,52,"家電・パソコン５２","かでん・ぱそこん５２","家電・パソコン５２の商品詳細",3,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 53,53,"家電・パソコン５３","かでん・ぱそこん５３","家電・パソコン５３の商品詳細",3,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 54,54,"家電・パソコン５４","かでん・ぱそこん５４","家電・パソコン５４の商品詳細",3,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 55,55,"おもちゃ・ゲーム５５","おもちゃ・げーむ５５","おもちゃ・ゲーム１の商品詳細",4,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 56,56,"おもちゃ・ゲーム５６","おもちゃ・げーむ５６","おもちゃ・ゲーム２の商品詳細",4,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 57,57,"おもちゃ・ゲーム５７","おもちゃ・げーむ５７","おもちゃ・ゲーム３の商品詳細",4,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 58,58,"おもちゃ・ゲーム５８","おもちゃ・げーむ５８","おもちゃ・ゲーム４の商品詳細",4,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 59,59,"おもちゃ・ゲーム５９","おもちゃ・げーむ５９","おもちゃ・ゲーム５の商品詳細",4,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 60,60,"おもちゃ・ゲーム６０","おもちゃ・げーむ６０","おもちゃ・ゲーム６の商品詳細",4,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 61,61,"おもちゃ・ゲーム６１","おもちゃ・げーむ６１","おもちゃ・ゲーム７の商品詳細",4,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 62,62,"おもちゃ・ゲーム６２","おもちゃ・げーむ６２","おもちゃ・ゲーム８の商品詳細",4,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 63,63,"おもちゃ・ゲーム６３","おもちゃ・げーむ６３","おもちゃ・ゲーム９の商品詳細",4,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 64,64,"おもちゃ・ゲーム６４","おもちゃ・げーむ６４","おもちゃ・ゲーム１の商品詳細",4,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 65,65,"おもちゃ・ゲーム６５","おもちゃ・げーむ６５","おもちゃ・ゲーム２の商品詳細",4,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 66,66,"おもちゃ・ゲーム６６","おもちゃ・げーむ６６","おもちゃ・ゲーム３の商品詳細",4,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 67,67,"おもちゃ・ゲーム６７","おもちゃ・げーむ６７","おもちゃ・ゲーム４の商品詳細",4,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 68,68,"おもちゃ・ゲーム６８","おもちゃ・げーむ６８","おもちゃ・ゲーム５の商品詳細",4,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 69,69,"おもちゃ・ゲーム６９","おもちゃ・げーむ６９","おもちゃ・ゲーム６の商品詳細",4,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 70,70,"おもちゃ・ゲーム７０","おもちゃ・げーむ７０","おもちゃ・ゲーム７の商品詳細",4,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 71,71,"おもちゃ・ゲーム７１","おもちゃ・げーむ７１","おもちゃ・ゲーム８の商品詳細",4,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 72,72,"おもちゃ・ゲーム７２","おもちゃ・げーむ７２","おもちゃ・ゲーム９の商品詳細",4,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 73,73,"おもちゃ・ゲーム７３","おもちゃ・げーむ７３","おもちゃ・ゲーム１の商品詳細",4,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 74,74,"おもちゃ・ゲーム７４","おもちゃ・げーむ７４","おもちゃ・ゲーム２の商品詳細",4,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 75,75,"おもちゃ・ゲーム７５","おもちゃ・げーむ７５","おもちゃ・ゲーム３の商品詳細",4,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 76,76,"おもちゃ・ゲーム７６","おもちゃ・げーむ７６","おもちゃ・ゲーム４の商品詳細",4,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 77,77,"おもちゃ・ゲーム７７","おもちゃ・げーむ７７","おもちゃ・ゲーム５の商品詳細",4,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 78,78,"おもちゃ・ゲーム７８","おもちゃ・げーむ７８","おもちゃ・ゲーム６の商品詳細",4,300,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 79,79,"おもちゃ・ゲーム７９","おもちゃ・げーむ７９","おもちゃ・ゲーム７の商品詳細",4,100,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 80,80,"おもちゃ・ゲーム８０","おもちゃ・げーむ８０","おもちゃ・ゲーム８の商品詳細",4,200,"./images","sample.jpg",now(),"発売会社",0,now(),now()),
( 81,81,"おもちゃ・ゲーム８１","おもちゃ・げーむ８１","おもちゃ・ゲーム９の商品詳細",4,300,"./images","sample.jpg",now(),"発売会社",0,now(),now());





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
	FOREIGN KEY(user_id) REFERENCES user_info(user_id),
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
	update_Date DATETIME NOT NULL comment "更新日"

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
	insert_date datetime comment "登録日",
	update_date datetime comment "更新日"

)DEFAULT charset=utf8 comment="カテゴリマスタテーブル";
INSERT INTO m_category VALUES
(1,1,"全てのカテゴリー","本、家電・パソコン、おもちゃ・ゲーム全てのカテゴリーが対象となります",now(), null),
(2,2,"本","本に関するカテゴリーが対象となります",now(),null),
(3,3,"家電・パソコン","家電・パソコンに関するカテゴリーが対象となります",now(),null),
(4,4,"おもちゃ・ゲーム","おもちゃ・ゲームに関するカテゴリーが対象となります",now(),null);

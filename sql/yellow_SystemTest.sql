set names utf8;
set foreign_key_checks=0;

drop database if exists yellow;
create database if not exists yellow;

use yellow;

drop table if exists user_info;
create table user_info(
id int primary key not null auto_increment  /*ID*/,
user_id varchar(16) unique not null  /*ユーザーID*/,
password varchar(16) not null  /*パスワード*/,
family_name varchar(32) not null  /*姓*/,
first_name varchar(32) not null  /*名*/,
family_name_kana varchar(32) not null  /*姓かな*/,
first_name_kana varchar(32) not null  /*名かな*/,
sex tinyint not null default 0  /*性別 0.男性 1.女性*/,
email varchar(32) not null  /*メールアドレス*/,
status tinyint not null default 0  /*ステータス 0.一般 1.管理者*/,
logined tinyint not null default 0  /*ログインフラグ 0.未ログイン 1.ログイン済*/,
regist_date datetime not null  /*登録日*/,
update_date datetime  /*更新日*/
)
default charset=utf8
/*会員情報テーブル*/
;
insert into user_info values
(1,"guest","guest","インターノウス","ゲストユーザー","いんたーのうす","げすとゆーざー",0,"internous.guest@gmail.com",0,0,now(),now()),
(2,"guest2","guest2","インターノウス","ゲストユーザー2","いんたーのうす","げすとゆーざー2",1,"internous.guest2@gmail.com",0,0,now(),now()),
(3,"guest3","guest3","インターノウス","ゲストユーザー3","いんたーのうす","げすとゆーざー3",0,"guest3@gmail.com",0,0,now(),now()),
(4,"guest4","guest4","インターノウス","ゲストユーザー4","いんたーのうす","げすとゆーざー4",1,"guest4@gmail.com",0,0,now(),now()),
(5,"guest5","guest5","インターノウス","ゲストユーザー5","いんたーのうす","げすとゆーざー5",0,"guest5@gmail.com",0,0,now(),now()),
(6,"guest6","guest6","インターノウス","ゲストユーザー6","いんたーのうす","げすとゆーざー6",1,"guest6@gmail.com",0,0,now(),now()),
(7,"guest7","guest7","インターノウス","ゲストユーザー7","いんたーのうす","げすとゆーざー7",0,"guest7@gmail.com",0,0,now(),now()),
(8,"guest8","guest8","インターノウス","ゲストユーザー8","いんたーのうす","げすとゆーざー8",1,"guest8@gmail.com",0,0,now(),now()),
(9,"guest9","guest9","インターノウス","ゲストユーザー9","いんたーのうす","げすとゆーざー9",0,"guest9@gmail.com",0,0,now(),now()),
(10,"guest10","guest10","インターノウス","ゲストユーザー10","いんたーのうす","げすとゆーざー10",1,"guest10@gmail.com",0,0,now(),now()),
(11,"guest11","guest11","インターノウス","ゲストユーザー11","いんたーのうす","げすとゆーざー11",0,"guest11@gmail.com",0,0,now(),now()),
(12,"guest12","guest12","インターノウス","ゲストユーザー12","いんたーのうす","げすとゆーざー12",1,"guest12@gmail.com",0,0,now(),now());

drop table if exists product_info;
create table product_info(
id int primary key not null auto_increment  /*ID*/,
product_id int unique not null  /*商品ID*/,
product_name varchar(100) unique not null  /*商品名*/,
product_name_kana varchar(100) unique not null  /*商品名かな*/,
product_description varchar(255) not null  /*商品詳細*/,
category_id int not null not null  /*カテゴリID*/,
price int  /*価格*/,
image_file_path varchar(100)  /*画像ファイルパス*/,
image_file_name varchar(50)  /*画像ファイル名*/,
release_date datetime not null  /*発売年月*/,
release_company varchar(50)  /*発売会社*/,
status tinyint not null default 0  /*ステータス 0.無効 1.有効*/,
regist_date datetime not null  /*登録日*/,
update_date datetime  /*更新日*/,
foreign key(category_id) references m_category(category_id)
)
default charset=utf8
/*商品情報テーブル*/
;

insert into product_info values
( 1, 1,"Wash House Thig","うぉっしゅはうすてぃんぐ","洋楽１の商品詳細",2,100,"./images","洋楽1.jpg",now(),"発売会社",0,now(),now()),
( 2, 2,"MY WORLDS ACOUSTIC","まいわーるどあこーすてぃっく","洋楽２の商品詳細",2,200,"./images","洋楽2.jpg",now(),"発売会社",0,now(),now()),
( 3, 3,"DIRTY WORK","だーてぃーうぉーく","洋楽３の商品詳細",2,300,"./images","洋楽3.jpg",now(),"発売会社",0,now(),now()),
( 6, 6, "アンビバレント","あんびばれんと","邦楽１の商品詳細",3,100,"./images","邦楽1.jpg",now(),"発売会社",0,now(),now()),
( 7, 7, "Neyagawa City Pop","ねやがわしてぃぽっぷ","邦楽２の商品詳細",3,100,"./images","邦楽2.jpg",now(),"発売会社",0,now(),now()),
( 8, 8, "イチブトゼンブ","いちぶとぜんぶ","邦楽３の商品詳細",3,100,"./images","邦楽3.jpg",now(),"発売会社",0,now(),now()),
( 9, 9, "Ambitions","あんびしょんず","邦楽４の商品詳細",3,100,"./images","邦楽4.jpg",now(),"発売会社",0,now(),now()),
( 11, 11, "BOHUMIYAN RHAPSODY","ぼふみやん らぷそでぃー","サウンドトラック１の商品詳細",4,100,"./images","soundtrack1.jpg",now(),"発売会社",0,now(),now()),
( 12, 12, "マリオ オリジナルサントラ","まりおおりじなるさんとら","サウンドトラック２の商品詳細",4,100,"./images","soundtrack2.jpg",now(),"発売会社",0,now(),now()),
( 13, 13, "フミーポピンズ","ふみーぽぴんず","サウンドトラック３の商品詳細",4,100,"./images","soundtrack3.jpg",now(),"発売会社",0,now(),now()),
( 14, 14, "GREITEST SHOWMAN","ぐれいてすとしょーまん","サウンドトラック４の商品詳細",4,100,"./images","soundtrack4.jpg",now(),"発売会社",0,now(),now()),
( 15, 15, "Hummy Potter","ふみーぽったー","サウンドトラック５の商品詳細",4,100,"./images","soundtrack6.jpg",now(),"発売会社",0,now(),now());


drop table if exists cart_info;
create table cart_info(
id int primary key not null auto_increment  /*ID*/,
user_id varchar(16) not null  /*ユーザーID*/,
temp_user_id varchar(16)  /*仮ユーザーID*/,
product_id int not null  /*商品ID*/,
product_count int not null  /*個数*/,
price int not null  /*金額*/,
regist_date datetime not null  /*登録日*/,
update_date datetime  /*更新日*/
)
default charset=utf8
/*カート情報テーブル*/
;

drop table if exists purchase_history_info;
create table purchase_history_info(
id int primary key not null auto_increment  /*ID*/,
user_id varchar(16) not null  /*ユーザーID*/,
product_id int not null  /*商品ID*/,
product_count int not null  /*個数*/,
price int not null  /*金額*/,
destination_id int not null  /*宛先情報ID*/,
regist_date datetime not null  /*登録日*/,
update_date datetime  /*更新日*/,
foreign key(product_id) references product_info(product_id)
)
default charset=utf8
/*購入履歴情報テーブル*/
;

drop table if exists destination_info;
create table destination_info(
id int primary key not null auto_increment  /*ID*/,
user_id varchar(16) not null  /*ユーザーID*/,
family_name varchar(32) not null  /*姓*/,
first_name varchar(32) not null  /*名*/,
family_name_kana varchar(32) not null  /*姓かな*/,
first_name_kana varchar(32) not null  /*名かな*/,
email varchar(32) not null  /*メールアドレス*/,
tel_number varchar(13) not null  /*電話番号*/,
user_address varchar(50) not null  /*住所*/,
regist_date datetime not null  /*登録日*/,
update_date datetime  /*更新日*/
)
default charset=utf8
/*宛先情報テーブル*/
;
insert into destination_info values
(1,"guest","インターノウス","ゲストユーザー","いんたーのうす","げすとゆーざー","internous.guest@gmail.com","09000000000","東京都千代田区霞が関 3-6-15",now(),now());

drop table if exists m_category;
create table m_category(
id int primary key not null auto_increment  /*ID*/,
category_id int unique not null  /*カテゴリID*/,
category_name varchar(20) unique not null  /*カテゴリ名*/,
category_description varchar(100)  /*カテゴリ詳細*/,
insert_date datetime not null  /*登録日*/,
update_date datetime  /*更新日*/
)
default charset=utf8
/*カテゴリマスタテーブル*/
;
insert into m_category values
(1,1,"全てのカテゴリー","洋楽、邦楽、サウンドトラック全てのカテゴリーが対象となります",now(), now()),
(2,2,"洋楽","洋楽に関するカテゴリーが対象となります",now(),now()),
(3,3,"邦楽","邦楽に関するカテゴリーが対象となります",now(),now()),
(4,4, "サウンドトラック", "サウンドトラックに関するカテゴリーが対象となります",now(), now());

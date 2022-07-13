CREATE TABLE IF NOT EXISTS category (
	id SERIAL,
	name VARCHAR(16),
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS item (
	name VARCHAR(16) UNIQUE NOT NULL,
	category_id INTEGER,
	manufacturer_id INTEGER,
	image BYTEA,
	PRIMARY KEY (name)
);

CREATE TABLE IF NOT EXISTS factory (
	code SERIAL,
	name VARCHAR(16),
	PRIMARY KEY (code)
);

CREATE TABLE IF NOT EXISTS sales_office (
	name VARCHAR(16) UNIQUE PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS sales_rep (
	number VARCHAR(16) UNIQUE PRIMARY KEY,
	name VARCHAR(16),
	office VARCHAR(16)
);

INSERT INTO category (name) VALUES ('映像');
INSERT INTO category (name) VALUES ('音響');
INSERT INTO category (name) VALUES ('調理');

INSERT INTO factory (name) VALUES ('東京');
INSERT INTO factory (name) VALUES ('大阪');

INSERT INTO item (category_id, manufacturer_id, name) VALUES (
	1, 1, 'テレビ'
);
INSERT INTO item (category_id, manufacturer_id, name) VALUES (
	1, 2, 'ビデオカメラ'
);
INSERT INTO item (category_id, manufacturer_id, name) VALUES (
	2, 2, 'ヘッドフォン'
);
INSERT INTO item (category_id, manufacturer_id, name) VALUES (
	2, 2, 'ラジオ'
);
INSERT INTO item (category_id, manufacturer_id, name) VALUES (
	3, 1, '冷蔵庫'
);
INSERT INTO item (category_id, manufacturer_id, name) VALUES (
	3, 1, '洗濯機'
);

INSERT INTO sales_office (name) VALUES ('札幌'), ('仙台'), ('東京'),
('名古屋'), ('大阪'), ('広島'), ('福岡');

INSERT INTO sales_rep (name, office) ('山田太郎', '東京'), ('鈴木一郎', '札幌'), ('佐藤花子', '大阪')

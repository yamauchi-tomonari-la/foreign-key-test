CREATE TABLE IF NOT EXISTS category (
	id SERIAL,
	name VARCHAR(16),
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS item (
	id SERIAL,
	category_id INTEGER,
	manufacuturer_id INTEGER,
	name VARCHAR(16),
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS factory (
	code SERIAL,
	name VARCHAR(16),
	PRIMARY KEY (code)
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

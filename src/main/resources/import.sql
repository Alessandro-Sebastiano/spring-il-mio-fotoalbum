INSERT INTO album_db.photos(description, title, url, visible) VALUES("description", "title1", "https://picsum.photos/id/237/1920/1080", 1);
INSERT INTO album_db.photos(description, title, url, visible) VALUES("description", "title2", "https://picsum.photos/id/126/1920/1080", 1);
INSERT INTO album_db.photos(description, title, url, visible) VALUES("description", "title3", "https://picsum.photos/id/80/1920/1080", 1);
INSERT INTO album_db.photos(description, title, url, visible) VALUES("description", "title4", "https://picsum.photos/id/254/1920/1080", 1);
INSERT INTO album_db.photos(description, title, url, visible) VALUES("description", "title5", "https://picsum.photos/id/20/1920/1080", 0);
INSERT INTO album_db.photos(description, title, url, visible) VALUES("description", "title6", "https://picsum.photos/id/36/1920/1080", 1);

INSERT INTO album_db.categories(`type`) VALUES('Animals');
INSERT INTO album_db.categories(`type`) VALUES('Sport');
INSERT INTO album_db.categories(`type`) VALUES('Nature');
INSERT INTO album_db.categories(`type`) VALUES('Art');
INSERT INTO album_db.categories(`type`) VALUES('Architectural');

INSERT INTO album_db.photos_categories(photo_id, categories_id) VALUES(1, 1);
INSERT INTO album_db.photos_categories(photo_id, categories_id) VALUES(1, 3);

INSERT INTO album_db.users(email, first_name, last_name, password) VALUES('admin@email.com', 'admin', 'admin', '{noop}adminpasswd');

INSERT INTO album_db.roles(id, name) VALUES(1, 'ADMIN');

INSERT INTO album_db.users_roles(user_id, roles_id) VALUES(1, 1);

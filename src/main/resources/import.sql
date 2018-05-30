INSERT INTO users (user_id, name, username, password) VALUES (1, 'petar', 'pera', '12pera');
INSERT INTO users (user_id, name, username, password) VALUES (2, 'miroslav', 'mika', '12mika');

INSERT INTO posts (post_id, title, description, date, likes, dislikes, longitude, latitude, user_id) VALUES (1, 'Title 1', 'Description 1', '2018-02-04', 4, 2, 42, 38, 1);
INSERT INTO posts (post_id, title, description, date, likes, dislikes, longitude, latitude, user_id) VALUES (2, 'Title 2', 'Description 2', '2018-02-18', 35, 4,85,18, 2);

INSERT INTO comments (comments_id, title, description, date, likes, dislikes, post_id, user_id) VALUES (1, 'Kom1', 'Opis kom1', '2018-05-04', 7, 8, 1, 1);
INSERT INTO comments (comments_id, title, description, date, likes, dislikes, post_id, user_id) VALUES (2, 'Kom2', 'Opis kom2', '2018-05-26', 48, 35, 2, 2);
INSERT INTO comments (comments_id, title, description, date, likes, dislikes, post_id, user_id) VALUES (3, 'Kom3', 'opis kom3', '2018-08-14', 974, 845, 1, 1);

INSERT INTO tags (name) VALUES ('tag1');
INSERT INTO tags (name) VALUES ('tag2');

INSERT INTO post_tags (post_id, tag_id) VALUES (1,1);
INSERT INTO post_tags (post_id, tag_id) VALUES (2,2);
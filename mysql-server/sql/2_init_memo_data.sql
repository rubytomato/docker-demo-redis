USE sample_db;

START TRANSACTION;

INSERT INTO memo (title, description, done, updated) VALUES ('Memo A', 'aISms0a02jsy47xk4kao28FlqUqnwl', false, '2017-10-01');
INSERT INTO memo (title, description, done, updated) VALUES ('Memo B', 'i5hxoG8rm29quqububMr9gu1OQia75', false, '2017-10-02');
INSERT INTO memo (title, description, done, updated) VALUES ('Memo C', '5L18coGlrHao3yz9xur8c9vpDhfu6o', true, '2017-10-02');
INSERT INTO memo (title, description, done, updated) VALUES ('Memo D', 'Mw8Xu1itnr8fkdn4lbpWJfpe91mg71', false, '2017-10-03');
INSERT INTO memo (title, description, done, updated) VALUES ('Memo E', 'Xl6ngkzic8w05orn6hc7b82hthXJrK', true, '2017-10-03');
INSERT INTO memo (title, description, done, updated) VALUES ('Memo F', '5kspemtnWUQ94lemehf0f0aM482iqo', false, '2017-10-03');
INSERT INTO memo (title, description, done, updated) VALUES ('Memo G', 'lB82Lq8riepbo395UejquBnbu40syd', false, '2017-10-04');
INSERT INTO memo (title, description, done, updated) VALUES ('Memo H', 'ptye0qPrig7tyZh59Sut74Lqnwk4j3', true, '2017-10-05');
INSERT INTO memo (title, description, done, updated) VALUES ('Memo I', '9d7sien1mgyxu37gI6nqHfhFmx95iV', true, '2017-10-06');
INSERT INTO memo (title, description, done, updated) VALUES ('Memo J', 'zPwyxLEp50am18quX0d7bnajeo1k2j', true, '2017-10-06');

COMMIT;

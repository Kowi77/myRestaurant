DELETE FROM dishes;
DELETE FROM users;
DELETE FROM restaurants;

INSERT INTO restaurants (name) VALUES
  ('Агава'),
  ('Бурбон'),
  ('Скоморохи');

INSERT INTO dishes (description, price, restId) VALUES
  ('Пиво', '100', '1'),
  ('Салат Цезарь', '999', '1'),
  ('Лапша', '140', '2'),
  ('Шашлык', '160', '2'),
  ('Плов', '80', '2'),
  ('Уха', '40', '2'),
  ('Вино', '60', '3'),
  ('Манка', '1', '3'),
  ('Гречка', '99', '3');

INSERT INTO users (name, restId) VALUES
  ('Андрей', '1'),
  ('Юля', '2'),
  ('Златан', '3');

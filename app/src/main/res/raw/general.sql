CREATE TABLE IF NOT EXISTS product (
  id integer primary key autoincrement,
  nameProduct text,
  price float,
  quantity integer
);

CREATE TABLE IF NOT EXISTS inventory (
  id integer primary key autoincrement,
  dateInventory long,


);
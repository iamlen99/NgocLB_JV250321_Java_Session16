use md4_ss16_db;
# drop table orders;
create table orders
(
    id           int primary key auto_increment,
    customer_id  int,
    total_amount decimal,
    product_id   int,
    quantity     int,
    constraint op_product_id_fk
        foreign key (product_id)
            references products (id)
            on delete cascade
);

create table products
(
    id           int primary key auto_increment,
    product_name varchar(50),
    stock        int
);


DELIMITER $$
create procedure place_order(
    customer_id_in int,
    total_amount_in decimal,
    product_id_in int,
    quantity_in int
)
begin
    insert orders (customer_id, total_amount, product_id, quantity)
        values (customer_id_in, total_amount_in, product_id_in, quantity_in);
end $$
DELIMITER ;

DELIMITER $$
create procedure get_amount(
    id_in int
)
begin
    select stock from products
        where id = id_in;
end $$
DELIMITER ;

DELIMITER $$
create procedure update_stock(
    id_in int,
    quantity_order_in int
)
begin
    update products
        set stock = stock = quantity_order_in
    where id = id_in;
end $$
DELIMITER ;

DELIMITER $$
create procedure is_exist_product_id(
    id_in int,
    count_id int
)
begin
    select count(product_name) into count_id from products
        where id = id_in;
end $$
DELIMITER ;


create schema book_management;
use book_management;

create table book_type
(
    type_id          int auto_increment primary key,
    type_name        varchar(100) not null unique,
    type_description text         not null,
    type_status      bit default 1
);

create table book
(
    book_id     char(5) primary key,
    book_name   varchar(100) not null unique,
    book_title  varchar(200) not null,
    book_pages  int          not null check (book_pages > 0 ),
    book_author varchar(200) not null,
    book_price  float        not null,
    check ( book_price > 0 ),
    type_id     int          not null,
    book_status bit default 1,
    constraint b_bt_type_id_fk
        foreign key (type_id)
            references book_type (type_id)
            on delete cascade
);

# Tao procedure bang book_type
DELIMITER $$
create procedure get_all_book_types()
begin
    select * from book_type;
end $$
DELIMITER ;

DELIMITER $$
create procedure is_exist_book_type_name(
    in_type_name varchar(100),
    out count_book_type int
)
begin
    set count_book_type = (select count(type_id) from book_type bt where bt.type_name like in_type_name);
end $$
DELIMITER ;

DELIMITER $$
create procedure add_book_type(
    in_type_name varchar(100),
    in_type_description text
)
begin
    insert into book_type (type_name, type_description)
    values (in_type_name, in_type_description);
end $$
DELIMITER ;

DELIMITER $$
create procedure search_book_type_by_type_id(
    in_type_id int
)
begin
    select * from book_type where type_id = in_type_id;
end $$
DELIMITER ;

DELIMITER $$
create procedure update_book_type(
    in_type_id int,
    in_type_name varchar(100),
    in_type_description text,
    in_type_status bit
)
begin
    update book_type
    set type_name        = in_type_name,
        type_description = in_type_description,
        type_status      = in_type_status
    where type_id = in_type_id;
end $$
DELIMITER ;

DELIMITER $$
create procedure delete_book_type(
    in_type_id int,
    in_type_status bit
)
begin
    declare count_book_id int default 0;
    select count(b.book_id)
    into count_book_id
    from book b
    where b.type_id = in_type_id;

    if (count_book_id > 0) then
        update book_type
        set type_status = in_type_status
        where type_id = in_type_id;
    else
        delete from book_type where type_id = in_type_id;
    end if;
end $$
DELIMITER ;

# Tao procedure bang Book
DELIMITER $$
create procedure get_all_book_by_priceASC()
begin
    select *, bt.type_name
    from book b
             inner join book_type bt
                        on b.type_id = bt.type_id
    order by b.book_price;
end $$
DELIMITER ;

DELIMITER $$
create procedure is_exist_book_name(
    in_book_name varchar(100),
    out count_book int
)
begin
    set count_book = (select count(book_id) from book b where b.book_name like in_book_name);
end $$
DELIMITER ;

DELIMITER $$
create procedure add_book(
    in_book_name varchar(100),
    in_book_title varchar(200),
    in_book_pages int,
    in_book_author varchar(200),
    in_book_price float,
    in_type_id int
)
begin
    insert into book (book_name, book_title, book_pages, book_author, book_price, type_id)
    values (in_book_name, in_book_title, in_book_pages
           , in_book_author, in_book_price, in_type_id);
end $$
DELIMITER ;

DELIMITER $$
create procedure find_book_by_book_id(
    in_book_id char(5)
)
begin
    select * from book where book_id = in_book_id;
end $$
DELIMITER ;

DELIMITER $$
create procedure update_book(
    in_book_id char(5),
    in_book_name varchar(100),
    in_book_title varchar(200),
    in_book_pages int,
    in_book_author varchar(200),
    in_book_price float,
    in_type_id int
)
begin
    update book
    set book_name = in_book_name,
        book_title = in_book_title,
        book_pages = in_book_pages,
        book_author = in_book_author,
        book_price = in_book_price,
        type_id = in_type_id
    where book_id = in_book_id;
end $$
DELIMITER ;

DELIMITER $$
create procedure delete_book(
    in_book_id char(5)
)
begin
    delete from book where book_id = in_book_id;
end $$
DELIMITER ;

DELIMITER $$
create procedure find_book_by_book_name(
    in_book_name varchar(100)
)
begin
    declare search_value varchar(100) default '%'+ in_book_name +'%';
    select * from book where book_name like search_value;
end $$
DELIMITER ;

DELIMITER $$
create procedure statistics_book_by_author_name()
begin
    select b.book_author, count(b.book_id) from book b;
end $$
DELIMITER ;
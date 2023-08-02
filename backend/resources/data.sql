
-- drop table product_to_category IF EXISTS;
-- drop table reviews IF EXISTS;
-- drop table categories IF EXISTS;
-- drop table products IF EXISTS;


#####################################################################################################################
#                                          Insert Categories
######################################################################################################################

insert into microserv.categories(cid, cname) values (1, "Animals");
insert into microserv.categories(cid, cname) values (2, "Fish");
insert into microserv.categories(cid, cname) values (3, "Mammals");
insert into microserv.categories(cid, cname) values (4, "Lizards");
insert into microserv.categories(cid, cname) values (5, "Birds");

###########################################################################################################################
#                                          Insert products and productsToCategories                                       #
###########################################################################################################################

insert into microserv.products(pid, pname, pdesc, price, qty, vis) values (1, "Goldfish", "A happy lil' guy", 1.25, 236, 1);
insert into microserv.products(pid, pname, pdesc, price, qty, vis) values (2, "Beta", "An angry lil' sociopath", 10.00, 21, 1);
insert into microserv.products(pid, pname, pdesc, price, qty, vis) values (3, "Tuna", "A tastey large catch", 200.00, 3, 1);
insert into microserv.products(pid, pname, pdesc, price, qty, vis) values (4, "Eel", "A scary bite-y fisy", 52.25, 10, 1);

insert into microserv.products(pid, pname, pdesc, price, qty, vis) values (5, "Dog", "A funny lil' puppy", 100.00, 14, 1);
insert into microserv.products(pid, pname, pdesc, price, qty, vis) values (6, "Cat", "A sassy lil' kitty", 100.00, 12, 1);
insert into microserv.products(pid, pname, pdesc, price, qty, vis) values (7, "Bunny", "A hoppy lil' bun", 75.00, 2547, 1);
insert into microserv.products(pid, pname, pdesc, price, qty, vis) values (8, "Skunk", "A stinky lil' buddy", 125.00, 4, 1);

insert into microserv.products(pid, pname, pdesc, price, qty, vis) values (9, "Bearded Dragon", "A chill lil' fella", 75.75, 5, 1);
insert into microserv.products(pid, pname, pdesc, price, qty, vis) values (10, "Python", "A hugg-y long pal", 350.00, 6, 1);
insert into microserv.products(pid, pname, pdesc, price, qty, vis) values (11, "Iguana", "A whip-y tailed jerk", 10000.00, 1, 1);
insert into microserv.products(pid, pname, pdesc, price, qty, vis) values (12, "Komodo Dragon", "A large bite-y guy", 99.99, 2, 1);

insert into microserv.products(pid, pname, pdesc, price, qty, vis) values (13, "Crow", "A smart flappy friend", 94.99, 7, 1);
insert into microserv.products(pid, pname, pdesc, price, qty, vis) values (14, "Cardinal", "A red flappy bird", 74.99, 5, 1);
insert into microserv.products(pid, pname, pdesc, price, qty, vis) values (15, "Duck", "A quacky lil' champ", 67.89, 77, 1);
insert into microserv.products(pid, pname, pdesc, price, qty, vis) values (16, "Goose", "A hissy lil' demon", 123.45, 66, 1);

###########################################################################################################################
#                                          Insert products and productsToCategories                                       #
###########################################################################################################################

insert into microserv.product_to_category(id, cid, pid) values(1, 1, 1);
insert into microserv.product_to_category(id, cid, pid) values(2, 2, 1);
insert into microserv.product_to_category(id, cid, pid) values(3, 1, 2);
insert into microserv.product_to_category(id, cid, pid) values(4, 2, 2);
insert into microserv.product_to_category(id, cid, pid) values(5, 1, 3);
insert into microserv.product_to_category(id, cid, pid) values(6, 2, 3);
insert into microserv.product_to_category(id, cid, pid) values(7, 1, 4);
insert into microserv.product_to_category(id, cid, pid) values(8, 2, 4);
insert into microserv.product_to_category(id, cid, pid) values(9, 1, 5);
insert into microserv.product_to_category(id, cid, pid) values(10, 3, 5);
insert into microserv.product_to_category(id, cid, pid) values(11, 1, 6);
insert into microserv.product_to_category(id, cid, pid) values(12, 3, 6);
insert into microserv.product_to_category(id, cid, pid) values(13, 1, 7);
insert into microserv.product_to_category(id, cid, pid) values(14, 3, 7);
insert into microserv.product_to_category(id, cid, pid) values(15, 1, 8);
insert into microserv.product_to_category(id, cid, pid) values(16, 3, 8);
insert into microserv.product_to_category(id, cid, pid) values(17, 1, 9);
insert into microserv.product_to_category(id, cid, pid) values(18, 4, 9);
insert into microserv.product_to_category(id, cid, pid) values(19, 1, 10);
insert into microserv.product_to_category(id, cid, pid) values(20, 4, 10);
insert into microserv.product_to_category(id, cid, pid) values(21, 1, 11);
insert into microserv.product_to_category(id, cid, pid) values(22, 4, 11);
insert into microserv.product_to_category(id, cid, pid) values(23, 1, 12);
insert into microserv.product_to_category(id, cid, pid) values(24, 4, 12);
insert into microserv.product_to_category(id, cid, pid) values(25, 1, 13);
insert into microserv.product_to_category(id, cid, pid) values(26, 5, 13);
insert into microserv.product_to_category(id, cid, pid) values(27, 1, 14);
insert into microserv.product_to_category(id, cid, pid) values(28, 5, 14);
insert into microserv.product_to_category(id, cid, pid) values(29, 1, 15);
insert into microserv.product_to_category(id, cid, pid) values(30, 5, 15);
insert into microserv.product_to_category(id, cid, pid) values(31, 1, 16);
insert into microserv.product_to_category(id, cid, pid) values(32, 5, 16);

###########################################################################################################################
#                                          Select statement to show correlation                                           #
###########################################################################################################################

select p.pid as"PId:", c.cname as "Category:", p.pname as "Name:", p.pdesc as "Description:", p.price as "Price:", p.qty as "Quantity"
from products p
         inner join  product_to_category ptc
                     on ptc.pid = p.pid
         inner join categories c
                    on c.cid = ptc.cid
order by p.pid;

###########################################################################################################################
#                                          Drop tables for cleanup                                                        #
###########################################################################################################################

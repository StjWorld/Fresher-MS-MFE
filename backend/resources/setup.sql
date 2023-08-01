###########################################################################################################################
#                                          Insert Categories                                                              #
###########################################################################################################################

insert into microserv.categories(cname) values ("Animals");
insert into microserv.categories(cname) values ("Fish");
insert into microserv.categories(cname) values ("Mammals");
insert into microserv.categories(cname) values ("Lizards");
insert into microserv.categories(cname) values ("Birds");

###########################################################################################################################
#                                          Insert products and productsToCategories                                       #
###########################################################################################################################

insert into microserv.products(pname, pdesc, price, qty, vis) values ("Goldfish", "A happy lil' guy", 1.25, 236, 1);
insert into microserv.product_to_category(cid, pid) values(1,1);
insert into microserv.product_to_category(cid, pid) values(2,1);
insert into microserv.products(pname, pdesc, price, qty, vis) values ("Beta", "An angry lil' sociopath", 10.00, 21, 1);
insert into microserv.product_to_category(cid, pid) values(1,2);
insert into microserv.product_to_category(cid, pid) values(2,2);
insert into microserv.products(pname, pdesc, price, qty, vis) values ("Tuna", "A tastey large catch", 200.00, 3, 1);
insert into microserv.product_to_category(cid, pid) values(1,3);
insert into microserv.product_to_category(cid, pid) values(2,3);
insert into microserv.products(pname, pdesc, price, qty, vis) values ("Eel", "A scary bite-y fisy", 52.25, 10, 1);
insert into microserv.product_to_category(cid, pid) values(1,4);
insert into microserv.product_to_category(cid, pid) values(2,4);

insert into microserv.products(pname, pdesc, price, qty, vis) values ("Dog", "A funny lil' puppy", 100.00, 14, 1);
insert into microserv.product_to_category(cid, pid) values(1,5);
insert into microserv.product_to_category(cid, pid) values(3,5);
insert into microserv.products(pname, pdesc, price, qty, vis) values ("Cat", "A sassy lil' kitty", 100.00, 12, 1);
insert into microserv.product_to_category(cid, pid) values(1,6);
insert into microserv.product_to_category(cid, pid) values(3,6);
insert into microserv.products(pname, pdesc, price, qty, vis) values ("Bunny", "A hoppy lil' bun", 75.00, 2547, 1);
insert into microserv.product_to_category(cid, pid) values(1,7);
insert into microserv.product_to_category(cid, pid) values(3,7);
insert into microserv.products(pname, pdesc, price, qty, vis) values ("Skunk", "A stinky lil' buddy", 125.00, 4, 1);
insert into microserv.product_to_category(cid, pid) values(1,8);
insert into microserv.product_to_category(cid, pid) values(3,8);

insert into microserv.products(pname, pdesc, price, qty, vis) values ("Bearded Dragon", "A chill lil' fella", 75.75, 5, 1);
insert into microserv.product_to_category(cid, pid) values(1,9);
insert into microserv.product_to_category(cid, pid) values(4,9);
insert into microserv.products(pname, pdesc, price, qty, vis) values ("Python", "A hugg-y long pal", 350.00, 6, 1);
insert into microserv.product_to_category(cid, pid) values(1,10);
insert into microserv.product_to_category(cid, pid) values(4,10);
insert into microserv.products(pname, pdesc, price, qty, vis) values ("Iguana", "A whip-y tailed jerk", 10000.00, 1, 1);
insert into microserv.product_to_category(cid, pid) values(1,11);
insert into microserv.product_to_category(cid, pid) values(4,11);
insert into microserv.products(pname, pdesc, price, qty, vis) values ("Komodo Dragon", "A large bite-y guy", 99.99, 2, 1);
insert into microserv.product_to_category(cid, pid) values(1,12);
insert into microserv.product_to_category(cid, pid) values(4,12);

insert into microserv.products(pname, pdesc, price, qty, vis) values ("Crow", "A smart flappy friend", 94.99, 7, 1);
insert into microserv.product_to_category(cid, pid) values(1,13);
insert into microserv.product_to_category(cid, pid) values(5,13);
insert into microserv.products(pname, pdesc, price, qty, vis) values ("Cardinal", "A red flappy bird", 74.99, 5, 1);
insert into microserv.product_to_category(cid, pid) values(1,14);
insert into microserv.product_to_category(cid, pid) values(5,14);
insert into microserv.products(pname, pdesc, price, qty, vis) values ("Duck", "A quacky lil' champ", 67.89, 77, 1);
insert into microserv.product_to_category(cid, pid) values(1,15);
insert into microserv.product_to_category(cid, pid) values(5,15);
insert into microserv.products(pname, pdesc, price, qty, vis) values ("Goose", "A hissy lil' demon", 123.45, 66, 1);
insert into microserv.product_to_category(cid, pid) values(1,16);
insert into microserv.product_to_category(cid, pid) values(5,16);

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

#drop table product_to_category;
#drop table reviews;
#drop table categories;
#drop table products;










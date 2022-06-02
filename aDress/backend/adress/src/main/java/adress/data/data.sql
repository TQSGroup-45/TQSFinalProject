-- Loading Initial Data
--- Clients
insert into clients (name,dob,snum,sname,pc1,pc2,city) values ("Andreia","2001-02-21","2","Sesamee",1234,5678,"Narnia");
insert into clients (name,dob,snum,sname,pc1,pc2,city) values ("Maria","2000-02-02","3","Rua",1234,5678,"Aveiro");

--- Products
insert into products (name,price,color,gender,type) values ("Brown Pants",19.99,"Brown","Male","Pants");
insert into products (name,price,color,gender,type) values ("Blue Tshirt",9.99,"Blue","Male","Tshirt");

--- Orders
insert into orders (client, prods, date, total) values (0,[0,1],"2021-02-02",28.89);
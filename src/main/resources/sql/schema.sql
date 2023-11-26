create table user(
                     username varchar(12) primary key ,
                     password varchar(12) not null,
                     first_name varchar(20),
                     last_name varchar(20),
                     possition varchar(20)
);

create table customer(
                         cust_id varchar(10) primary key ,
                         name varchar(20) not null,
                         address varchar(40),
                         tel int(12)
);
desc customer;

create table vehicle(
                        vehicle_no varchar(10) primary key ,
                        cust_id varchar(10),
                        type varchar(10),
                        fuel_type varchar(10),
                        constraint foreign key (cust_id) references customer(cust_id) on delete cascade on update cascade

);
desc vehicle;

create table shedule(
                        shedule_id varchar(10) primary key ,
                        date date,
                        avaliability varchar(12),
                        description varchar(30)
);

create table package(
                        id varchar(10) primary key ,
                        type varchar(10),
                        amount double(10,2)
);

desc package;

create table payment(
                        payment_id varchar(10) primary key ,
                        date date,
                        method varchar(20),
                        amount double(10,2)
);

create table appointment(
                            appoint_id varchar(10)primary key ,
                            cust_id varchar(10),
                            shedule_id varchar(10),
                            package_id varchar(10),
                            payment_id varchar(10),
                            status varchar(10),

                            constraint foreign key (cust_id) references customer(cust_id) on delete cascade on update cascade,
                            constraint foreign key (shedule_id) references shedule(shedule_id) on delete cascade on update cascade,
                            constraint foreign key (package_id) references package(id) on delete cascade on update cascade,
                            constraint foreign key (payment_id) references payment(payment_id) on delete cascade on update cascade
);

create table vehicle_datails(
                                appoint_id varchar(10),
                                vehicle_no varchar(10),
                                constraint foreign key (appoint_id) references appointment(appoint_id) on delete cascade on update cascade,
                                constraint foreign key (vehicle_no) references vehicle(vehicle_no) on delete cascade on update cascade

);

create table item(
                     item_id varchar(10) primary key ,
                     name varchar(20),
                     description varchar(30),
                     unit_price double(10,2),
                     qty_on_hand int(10)
);

create table service_detail(
                               appoint_id varchar(10),
                               item_id varchar(10),
                               qty int(10),
                               total double(10,2),
                               constraint foreign key (appoint_id) references appointment(appoint_id) on delete cascade on update cascade,
                               constraint foreign key (item_id) references item(item_id) on delete cascade on update cascade

);

create table supplier(
                         sup_id varchar(10)primary key ,
                         name varchar(20),
                         tel int(12)
);

create table item_detail(
                            item_id varchar(10),
                            sup_id varchar(10),
                            unit_price double(10,2),
                            qty int(10),
                            constraint foreign key (item_id) references item(item_id) on delete cascade on update cascade,
                            constraint foreign key (sup_id) references supplier(sup_id) on delete cascade on update cascade

);

show tables;


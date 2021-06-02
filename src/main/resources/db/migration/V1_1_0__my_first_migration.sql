

CREATE TABLE bus (
                         id bigserial primary key,
                         bus_number varchar(50),
                         registry_number varchar(50),
                         amount_of_seats int,
                         price_per_kilometre float,
                         route_length int
);

CREATE TABLE passenger (
                           id bigserial primary key,
                           id_code varchar (50),
                           free_money float
);

CREATE TABLE box_office (
                            id bigserial primary key,
                            passenger_id bigint REFERENCES passenger(id) not null,
                            bus_id bigint REFERENCES bus(id) not null,
                            purchase_amount float,
                            purchase_date timestamp
);

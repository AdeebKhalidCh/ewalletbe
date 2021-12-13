create table wallets(
	id SERIAL PRIMARY KEY,
	name varchar_ignorecase(50) not null,
	amount numeric(20,8) default 0
);

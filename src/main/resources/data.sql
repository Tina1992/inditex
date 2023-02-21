CREATE TABLE IF NOT EXISTS brand(
    BRAND_ID INT PRIMARY KEY,
    BRAND_NAME VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS prices(
    PRICE_LIST INT PRIMARY KEY,
    PRODUCT_ID BIGINT NOT NULL,
    PRIORITY INT DEFAULT(-10),
    PRICE DECFLOAT NOT NULL,
    CURRENCY VARCHAR(3) NOT NULL,
    START_DATE TIMESTAMP,
    END_DATE TIMESTAMP,
    BRAND_ID INT NOT NULL,
    foreign key (BRAND_ID) references BRAND(BRAND_ID)
);

INSERT INTO brand (brand_id, brand_name) VALUES (1, 'ZARA');

INSERT INTO prices (price_list, product_id, priority, price, currency, start_date, end_date, brand_id) VALUES
    (1, 35455, 0, 35.50, 'EUR', PARSEDATETIME('2020-06-14-00.00.00', 'yyyy-MM-dd-HH.mm.ss'), PARSEDATETIME('2020-12-31-23.59.59', 'yyyy-MM-dd-HH.mm.ss'), 1);
INSERT INTO prices (price_list, product_id, priority, price, currency, start_date, end_date, brand_id) VALUES
    (2, 35455, 1, 25.45, 'EUR', PARSEDATETIME('2020-06-14-15.00.00', 'yyyy-MM-dd-HH.mm.ss'), PARSEDATETIME('2020-06-14-18.30.00', 'yyyy-MM-dd-HH.mm.ss'), 1);
INSERT INTO prices (price_list, product_id, priority, price, currency, start_date, end_date, brand_id) VALUES
    (3, 35455, 1, 30.50, 'EUR', PARSEDATETIME('2020-06-15-00.00.00', 'yyyy-MM-dd-HH.mm.ss'), PARSEDATETIME('2020-06-15-11.00.00', 'yyyy-MM-dd-HH.mm.ss'), 1);
INSERT INTO prices (price_list, product_id, priority, price, currency, start_date, end_date, brand_id) VALUES
    (4, 35455, 1, 38.95, 'EUR', PARSEDATETIME('2020-06-15-16.00.00', 'yyyy-MM-dd-HH.mm.ss'), PARSEDATETIME('2020-12-31-23.59.59', 'yyyy-MM-dd-HH.mm.ss'), 1);



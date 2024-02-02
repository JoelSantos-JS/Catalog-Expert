CREATE TABLE category (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    created_At TIMESTAMP,
    updated_At TIMESTAMP
);

INSERT INTO category (id, name, created_At, updated_At) VALUES
    (1, 'Exemplo1', '2022-01-31T12:00:00Z', '2022-01-31T14:30:00Z'),
    (2, 'Exemplo2', '2022-01-31T13:15:00Z', '2022-01-31T15:45:00Z'),
    (3, 'Exemplo3', '2022-01-31T14:30:00Z', '2022-01-31T16:00:00Z');


CREATE TABLE product (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    price DOUBLE NOT NULL,
    imgUrl VARCHAR(255)
);
CREATE TABLE tb_product_category (
    product_id BIGINT,
    category_id BIGINT,
    PRIMARY KEY (product_id, category_id),
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (category_id) REFERENCES category(id)
);


INSERT INTO product (id, name, description, price, imgUrl) VALUES
    (1, 'Laptop', 'Powerful laptop for work and gaming', 1299.99, 'https://example.com/laptop.jpg'),
    (2, 'T-shirt', 'Comfortable cotton T-shirt', 19.99, 'https://example.com/tshirt.jpg');

-- Inserção de dados na tabela de associação tb_product_category
INSERT INTO tb_product_category (product_id, category_id) VALUES
    (1, 1), -- Laptop belongs to Electronics category
    (2, 2); -- T-shirt belongs to Clothing category

INSERT INTO category (id, name, description, image_url)
VALUES (1,'Cartas Sueltas', 'Venta de cartas sueltas', '/images/796545.jpg'),
       (2,'Sobres', 'Venta de sobres', '/images/sobres.png'),
       (3,'Accesorios', 'Venta de Accesorios', '/images/accesorios.png');
INSERT INTO product (name, description, price, image_url, category_id)
VALUES ('Black Lotus', 'One of the most powerful cards in Magic, grants three mana of any color.', 25000.00,
        'https://i.ebayimg.com/00/s/MTYwMFgxMTU2/z/2pgAAOSwoaNePCCr/$_57.JPG?set_id=8800005007', 1),
       ('Lightning Bolt', 'Deals 3 damage to any target.', 0.50, '/images/797055.jpg', 1),
       ('Counterspell', 'Counters any spell.', 1.00, '', 1),
       ('Giant Growth', 'Target creature gets +3/+3 until end of turn.', 0.25,
        'https://example.com/images/giant_growth.jpg', 1),
       ('Wrath of God', 'Destroy all creatures. They cannot be regenerated.', 3.50,
        'https://example.com/images/wrath_of_god.jpg', 1),
       ('Shivan Dragon', 'Flying, gains +1/+0 for each red mana spent.', 5.00,
        'https://example.com/images/shivan_dragon.jpg', 1),
       ('Serra Angel', 'Flying, vigilance.', 1.50, '', 1),
       ('Dark Ritual', 'Adds three black mana to your mana pool.', 0.75, '',
        01),
       ('Llanowar Elves', 'Tap to add one green mana.', 0.30, '', 1),
       ('Sol Ring', 'Tap to add two colorless mana.', 2.00,'' , 1);


INSERT INTO rating (name, comentary, rating_number, image_url, date, product_id)
VALUES
    ('John Doe', 'Amazing product, exceeded expectations!', 4.5, 'https://example.com/image1.jpg', CURRENT_TIMESTAMP, 1),
    ('Jane Smith', 'Good quality, but a bit expensive.', 4.0, 'https://example.com/image2.jpg', CURRENT_TIMESTAMP, 1),
    ('Alice Brown', 'Battery life could be better.', 3.5, 'https://example.com/image3.jpg', CURRENT_TIMESTAMP, 2),
    ('Bob White', 'Excellent value for the price!', 5.0, 'https://example.com/image4.jpg', CURRENT_TIMESTAMP, 3),
    ('Charlie Black', 'Not as described, disappointed.', 2.0, 'https://example.com/image5.jpg', CURRENT_TIMESTAMP, 1);


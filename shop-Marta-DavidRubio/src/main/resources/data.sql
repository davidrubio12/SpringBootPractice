INSERT INTO category (name, description, image_url)
VALUES ('Cartas Sueltas', 'Venta de cartas sueltas', '/images/796545.jpg'),
       ('Sobres', 'Venta de sobres', '/images/sobres.png'),
       ('Accesorios', 'Venta de Accesorios', '/images/accesorios.png');
INSERT INTO product (name, description, price, imageurl, category_id,stockquantity)
VALUES ('Black Lotus', 'One of the most powerful cards in Magic, grants three mana of any color.', 25000.00,
        'https://i.ebayimg.com/00/s/MTYwMFgxMTU2/z/2pgAAOSwoaNePCCr/$_57.JPG?set_id=8800005007', 1,3),
       ('Lightning Bolt', 'Deals 3 damage to any target.', 0.50, '/images/lightning-bolt.jpg', 1,10),
       ('Shivan Dragon', 'Flying, gains +1/+0 for each red mana spent.', 5.00,
        '/images/shivan-dragon.png', 1,2),
       ('Giant Growth', 'Target creature gets +3/+3 until end of turn.', 0.25,
        null, 1,3),
       ('Wrath of God', 'Destroy all creatures. They cannot be regenerated.', 3.50,
        '/images/wrath.jpg', 1,20),
       ('Counterspell', 'Counters any spell.', 1.00, '/images/counterSpell.jpg', 1,90),
       ('Serra Angel', 'Flying, vigilance.', 1.50, '/images/serra-angel.png', 1,4),
       ('Dark Ritual', 'Adds three black mana to your mana pool.', 0.75, '/images/dark-ritual.png',
        1,14),
       ('Llanowar Elves', 'Tap to add one green mana.', 0.30, '/images/llanowar-elves.jpg', 1,5),
       ('Sol Ring', 'Tap to add two colorless mana.', 2.00, '/images/solring.jpg', 1,50),
       ('Alpha', 'Primera edición de Magic: The Gathering.', 1000.00, '/images/Sobre_de_Alpha.webp', 2,16),
       ('Cimientos', 'Colección llena de orcos.', 4.00, '/images/cimientos.png', 2,71),
       ('Unlimited', 'Primera edición con bordes blancos.', 25.00, '/images/unlimitedwebp.webp', 2,7),
       ('Revised', 'Reimpresión con cambios en la maquetación.', 1.00, '/images/revised.webp', 2,43),
       ('Caja', 'Caja cartas magic.', 10.00, '/images/cajita1webp.webp', 3,2),
       ('Caja Masters', 'Mazo preconstruido Masters', 30.00, '/images/caja2.webp',3,11),
       ('Dados', 'Dados para vidas.', 5.00, '/images/dados1.webp', 3,32)
;


INSERT INTO rating (name, comentary, ratingnumber, imageurl, date, product_id)
VALUES ('John Doe', 'Amazing product, exceeded expectations!', 4.5, 'https://example.com/image1.jpg',
        '2023-01-23 10:00:00', 1),
       ('Jane Smith', 'Good quality, but a bit expensive.', 4.0, 'https://example.com/image2.jpg', CURRENT_TIMESTAMP,
        1),
       ('Alice Brown', 'Battery life could be better.', 3.5, 'https://example.com/image3.jpg', CURRENT_TIMESTAMP, 2),
       ('Bob White', 'Excellent value for the price!', 5.0, 'https://example.com/image4.jpg', CURRENT_TIMESTAMP, 3),
       ('Charlie Black', 'Not as described, disappointed.', 2.0, 'https://example.com/image5.jpg',
        '2025-07-01 20:54:15', 1);


INSERT INTO app_users (first_name, last_name, email, password)
VALUES ('John', 'Doe', 'testuser@example.com', 'testpassword');

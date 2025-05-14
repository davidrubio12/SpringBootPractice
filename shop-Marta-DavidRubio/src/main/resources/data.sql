-- # INSERT INTO category (name, description, image_url)
-- # VALUES ('Cartas Sueltas', 'Venta de cartas sueltas', '/images/796545.jpg'),
-- #        ('Sobres', 'Venta de sobres', '/images/sobres.png'),
-- #        ('Accesorios', 'Venta de Accesorios', '/images/accesorios.png');
-- # INSERT INTO product (name, description, price, image_url, category_id,stock_quantity)
-- # VALUES ('Black Lotus', 'One of the most powerful cards in Magic, grants three mana of any color.', 25000.00,
-- #         'https://i.ebayimg.com/00/s/MTYwMFgxMTU2/z/2pgAAOSwoaNePCCr/$_57.JPG?set_id=8800005007', 1,3),
-- #        ('Lightning Bolt', 'Deals 3 damage to any target.', 0.50, '/images/lightning-bolt.jpg', 1,10),
-- #        ('Shivan Dragon', 'Flying, gains +1/+0 for each red mana spent.', 5.00,
-- #         '/images/shivan-dragon.png', 1,2),
-- #        ('Giant Growth', 'Target creature gets +3/+3 until end of turn.', 0.25,
-- #         null, 1,3),
-- #        ('Wrath of God', 'Destroy all creatures. They cannot be regenerated.', 3.50,
-- #         '/images/wrath.jpg', 1,20),
-- #        ('Counterspell', 'Counters any spell.', 1.00, '/images/counterSpell.jpg', 1,90),
-- #        ('Serra Angel', 'Flying, vigilance.', 1.50, '/images/serra-angel.png', 1,4),
-- #        ('Dark Ritual', 'Adds three black mana to your mana pool.', 0.75, '/images/dark-ritual.png',
-- #         1,14),
-- #        ('Llanowar Elves', 'Tap to add one green mana.', 0.30, '/images/llanowar-elves.jpg', 1,5),
-- #        ('Sol Ring', 'Tap to add two colorless mana.', 2.00, '/images/solring.jpg', 1,50),
-- #        ('Alpha', 'Primera edición de Magic: The Gathering.', 1000.00, '/images/Sobre_de_Alpha.webp', 2,16),
-- #        ('Cimientos', 'Colección llena de orcos.', 4.00, '/images/cimientos.png', 2,71),
-- #        ('Unlimited', 'Primera edición con bordes blancos.', 25.00, '/images/unlimitedwebp.webp', 2,7),
-- #        ('Revised', 'Reimpresión con cambios en la maquetación.', 1.00, '/images/revised.webp', 2,43),
-- #        ('Caja', 'Caja cartas magic.', 10.00, '/images/cajita1webp.webp', 3,2),
-- #        ('Caja Masters', 'Mazo preconstruido Masters', 30.00, '/images/caja2.webp',3,11),
-- #        ('Dados', 'Dados para vidas.', 5.00, '/images/dados1.webp', 3,32)
-- # ;
-- #
-- #
-- # INSERT INTO rating (name, comentary, rating_number, image_url, date, product_id)
-- # VALUES ('John Doe', 'Amazing product, exceeded expectations!', 4.5, 'https://example.com/image1.jpg',
-- #         '2023-01-23 10:00:00', 1),
-- #        ('Jane Smith', 'Good quality, but a bit expensive.', 4.0, 'https://example.com/image2.jpg', CURRENT_TIMESTAMP,
-- #         1),
-- #        ('Alice Brown', 'Battery life could be better.', 3.5, 'https://example.com/image3.jpg', CURRENT_TIMESTAMP, 2),
-- #        ('Bob White', 'Excellent value for the price!', 5.0, 'https://example.com/image4.jpg', CURRENT_TIMESTAMP, 3),
-- #        ('Charlie Black', 'Not as described, disappointed.', 2.0, 'https://example.com/image5.jpg',
-- #         '2025-07-01 20:54:15', 1);
-- #
-- #
-- # INSERT INTO app_users (first_name, last_name, email, password)
-- # VALUES ('John', 'Doe', 'testuser@example.com', 'testpassword');


UPDATE product
SET image_url = 'https://i.ebayimg.com/00/s/MTYwMFgxMTU2/z/2pgAAOSwoaNePCCr/$_57.JPG'
WHERE name = 'Black Lotus';

UPDATE product
SET image_url = 'https://cards.scryfall.io/large/front/0/7/074d2360-3ded-412d-90b5-2b8ab209fe47.jpg?1733230404'
WHERE name = 'Lightning Bolt';

UPDATE product
SET image_url = 'https://cards.scryfall.io/large/front/0/b/0bda24b2-a9ab-4aca-8bbf-1bfb0990f68f.jpg?1719002666'
WHERE name = 'Shivan Dragon';



UPDATE product
SET image_url = 'https://cards.scryfall.io/large/front/6/5/65a168b8-285b-4725-a982-671557d4f0bc.jpg?1733501468'
WHERE name = 'Giant Growth';

UPDATE product
SET image_url = 'https://cards.scryfall.io/large/front/c/a/caff117f-844b-4d11-a104-930d7f238114.jpg?1681768966'
WHERE name = 'Wrath of God';

UPDATE product
SET image_url = 'https://cards.scryfall.io/large/front/f/2/f2a7042f-a6f0-4e77-86a2-5eb0d2587363.jpg?1745923488'
WHERE name = 'Counterspell';

UPDATE product
SET image_url = 'https://cards.scryfall.io/large/front/8/e/8ea9612c-db1a-4858-816a-0f88d3080b4c.jpg?1697313292'
WHERE name = 'Serra Angel';

UPDATE product
SET image_url = 'https://cards.scryfall.io/large/front/6/f/6fc57076-cac4-4f5e-956b-e3d77bd258b2.jpg?1623779929'
WHERE name = 'Dark Ritual';

UPDATE product
SET image_url = 'https://cards.scryfall.io/large/front/7/4/74109c41-d1e7-4108-bed1-3d9110d5e7d2.jpg?1745844348'
WHERE name = 'Sol Ring';

UPDATE product
SET image_url = 'https://www.cardtrader.com/uploads/blueprints/image/39226/alpha-booster-limited-edition-alpha.jpg'
WHERE name = 'Alpha';

UPDATE product
SET image_url = 'https://comicstores.es/imagenes/5010996/501099624561.JPG'
WHERE name = 'Cimientos';

UPDATE product
SET image_url = 'https://www.elnucli.es/cdn/shop/files/MTGWOE_SP_Bstr_Drft_01_02_800x.png?v=1690904858'
WHERE name = 'Revised';

UPDATE product
SET image_url = 'https://www.elnucli.es/cdn/shop/products/MTGLTR_SP_Bstr_Set_01_02_800x.png?v=1680168442'
WHERE name = 'Unlimited';

UPDATE product
SET image_url = 'https://m.media-amazon.com/images/I/81nwcpnKODL._AC_UF894,1000_QL80_.jpg'
WHERE name = 'Caja';

UPDATE product
SET image_url = 'https://tcgplayer-cdn.tcgplayer.com/product/484933_in_600x600.jpg'
WHERE name = 'Caja Masters';

UPDATE product
SET image_url = 'https://www.shopultrapro.eu/cdn/shop/files/deluxe-d6-loyalty-dice-set-4ct-with-7-12-for-magic-the-gathering-205241.png?v=1737073651&width=900'
WHERE name = 'Dados';


INSERT INTO category (id, name, description, image_url)
VALUES (1,'Cartas Sueltas', 'Venta de cartas sueltas', '/images/796545.jpg'),
       (2,'Sobres', 'Venta de sobres', '/images/sobres.png'),
       (3,'Accesorios', 'Venta de Accesorios', '/images/accesorios.png');
INSERT INTO product (name, description, price, image_url, category_id)
VALUES ('Black Lotus', 'One of the most powerful cards in Magic, grants three mana of any color.', 25000.00,
        'https://i.ebayimg.com/00/s/MTYwMFgxMTU2/z/2pgAAOSwoaNePCCr/$_57.JPG?set_id=8800005007', 1),
       ('Lightning Bolt', 'Deals 3 damage to any target.', 0.50, '/images/797055.jpg', 1),
       ('Counterspell', 'Counters any spell.', 1.00, 'https://example.com/images/counterspell.jpg', 1),
       ('Giant Growth', 'Target creature gets +3/+3 until end of turn.', 0.25,
        'https://example.com/images/giant_growth.jpg', 1),
       ('Wrath of God', 'Destroy all creatures. They cannot be regenerated.', 3.50,
        'https://example.com/images/wrath_of_god.jpg', 1),
       ('Shivan Dragon', 'Flying, gains +1/+0 for each red mana spent.', 5.00,
        'https://example.com/images/shivan_dragon.jpg', 1),
       ('Serra Angel', 'Flying, vigilance.', 1.50, 'https://example.com/images/serra_angel.jpg', 1),
       ('Dark Ritual', 'Adds three black mana to your mana pool.', 0.75, 'https://example.com/images/dark_ritual.jpg',
        1),
       ('Llanowar Elves', 'Tap to add one green mana.', 0.30, 'https://example.com/images/llanowar_elves.jpg', 1),
       ('Sol Ring', 'Tap to add two colorless mana.', 2.00, 'https://example.com/images/sol_ring.jpg', 1);





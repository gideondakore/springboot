INSERT INTO authors (id, age, name) VALUES
(1, 23, 'Kwame Nkrumah'),
(2, 56, 'Odartey Lamptey'),
(3, 89, 'Eden Yeygan'),
(4, 28, 'Kaison Jijisu'),
(5, 90, 'Karen Welson')
ON CONFLICT DO NOTHING;

INSERT INTO books (isbn, title, author_id) VALUES
('978-0-306-40615-1', 'Widget A', 2),
('978-0-306-40615-2', 'Widget B', 5),
('978-0-306-40615-3', 'Widget C', 2),
('978-0-306-40615-4', 'Widget D', 1),
('978-0-306-40615-5', 'Widget E', 4)
ON CONFLICT DO NOTHING;

SELECT setval('author_id_seq', 5);
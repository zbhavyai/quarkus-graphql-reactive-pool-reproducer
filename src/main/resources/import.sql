INSERT INTO film
(   id,
    director,
    episodeid,
    releasedate,
    title
)
VALUES
(   nextval('hibernate_sequence'),
    'George Lucas',
    4,
    '1977-05-25',
    'A New Hope'
),
(   nextval('hibernate_sequence'),
    'George Lucas',
    5,
    '1980-05-21',
    'The Empire Strikes Back'
),
(   nextval('hibernate_sequence'),
    'George Lucas',
    6,
    '1983-05-25',
    'Return Of The Jedi'
);

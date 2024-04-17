CREATE TABLE IF NOT EXISTS "user"
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL CHECK (length(trim(name)) >= 2),
    role VARCHAR(2000)
);

CREATE TABLE IF NOT EXISTS metric
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(200)  NOT NULL CHECK (length(trim(name)) >= 3),
    value   VARCHAR(2000) NOT NULL CHECK (length(trim(value)) >= 3),
    user_id SERIAL        NOT NULL REFERENCES "user" (id) on delete SET NULL on UPDATE SET NULL
);

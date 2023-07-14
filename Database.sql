-- Створення таблиці "User"
CREATE TABLE "User" (
    id SERIAL PRIMARY KEY,
    username VARCHAR UNIQUE,
    password VARCHAR
);

-- Створення таблиці "Contact"
CREATE TABLE "Contact" (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES "User" (id),
    name VARCHAR UNIQUE
);

-- Створення таблиці "Email"
CREATE TABLE "Email" (
    id SERIAL PRIMARY KEY,
    contact_id INTEGER REFERENCES "Contact" (id),
    email VARCHAR UNIQUE
);

-- Створення таблиці "PhoneNumber"
CREATE TABLE "PhoneNumber" (
    id SERIAL PRIMARY KEY,
    contact_id INTEGER REFERENCES "Contact" (id),
    phone_number VARCHAR UNIQUE
);

-- Створення таблиці "Image"
CREATE TABLE "Image" (
    id SERIAL PRIMARY KEY,
    contact_id INTEGER REFERENCES "Contact" (id),
    image_path VARCHAR
);

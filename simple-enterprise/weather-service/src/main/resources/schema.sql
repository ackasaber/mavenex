CREATE TABLE cities
   (city_id INTEGER,
    city VARCHAR(50) NOT NULL,
    country VARCHAR(50) NOT NULL,
    -- they rename cities and countries all the time
    CONSTRAINT cities_pk PRIMARY KEY (city_id),
    CONSTRAINT cities_unique UNIQUE (city, country));

CREATE TABLE weather
   (city_id INTEGER,
    updated_at DATETIME,
    summary VARCHAR(100) NOT NULL,
    temperature DECIMAL(5, 3) NOT NULL,
    feels_like DECIMAL(5, 3) NOT NULL,
    city_min DECIMAL(5, 3) NOT NULL,
    city_max DECIMAL(5, 3) NOT NULL,
    precipitation VARCHAR(20) NOT NULL,
    wind_direction VARCHAR(20) NOT NULL,
    wind_speed DECIMAL(5, 2) NOT NULL,
    humidity INTEGER NOT NULL,
    pressure INTEGER NOT NULL,
    clouds VARCHAR(20) NOT NULL,
    visibility INTEGER,
    sunrise DATETIME NOT NULL,
    sunset DATETIME NOT NULL,
    CONSTRAINT weather_pk PRIMARY KEY (city_id, updated_at),
    CONSTRAINT weather_fk FOREIGN KEY (city_id)
               REFERENCES cities (city_id));
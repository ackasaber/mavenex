INSERT INTO cities (city_id, city, country)
VALUES (625144, 'Minsk', 'BY');

INSERT INTO weather (city_id, updated_at, summary,
                     temperature, feels_like, city_min, city_max,
                     precipitation, wind_direction, wind_speed,
                     humidity, pressure, clouds, visibility,
                     sunrise, sunset)
VALUES (625144, '2021-10-17 19:41:42', 'broken clouds',
        7.86, 4.96, 7.63, 7.86,
        'no', 'West-southwest', 4.9,
        78, 1016, 'broken clouds', 10000,
        '2021-10-17 04:40:57', '2021-10-17 15:08:52');

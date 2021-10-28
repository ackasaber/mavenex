<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>Current weather at ${location}</title>
</head>
<body>
    <h1>Current weather at ${city.city}, ${city.countryCode}.</h1>
    <ul>
        <li>Summary: ${weather.summary}</li>
        <li>Temperature:
            <ul>
                <li>current ${weather.temperature.current} °C,</li>
                <li>feels like ${weather.temperature.feelsLike} °C,</li>
                <li>city min ${weather.temperature.cityMin} °C,</li>
                <li>city max ${weather.temperature.cityMax} °C</li>
            </ul>
        </li>
        <li>Precipitation: ${weather.precipitation}</li>
        <li>Wind: ${weather.wind.direction} ${weather.wind.speed} m/s</li>
        <li>Humidity: ${weather.atmosphere.humidity} %</li>
        <li>Pressure: ${weather.atmosphere.pressure} hPa</li>
        <li>Clouds: ${weather.atmosphere.clouds}</li>
        <li>Visibility: ${weather.atmosphere.visibility} m</li>
        <li>Sunrise at ${weather.sun.sunrise} UTC</li>
        <li>Sunset at ${weather.sun.sunset} UTC</li>
    </ul>
    <p>Last updated at ${updatedAt} UTC.</p>
</body>
</html>
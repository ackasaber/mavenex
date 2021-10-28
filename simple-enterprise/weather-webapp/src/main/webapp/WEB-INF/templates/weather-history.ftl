<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>History of weather requests at ${location}</title>
</head>
<body>
    <#list reports as report>
        <h1>${report.city.city}, ${report.city.countryCode} at ${report.updatedAt}</h1>
        <table>
            <tr>
                <td>Summary</td>
                <td>${report.weather.summary}</td>
            </tr>
            <tr>
                <td>Temperature: current</td>
                <td>${report.weather.temperature.current} °C</td>
            </tr>
            <tr>
                <td>Temperature: feels like</td>
                <td>${report.weather.temperature.feelsLike} °C</td>
            </tr>
            <tr>
                <td>Temperature: city min</td>
                <td>${report.weather.temperature.cityMin} °C</td>
            </tr>
            <tr>
                <td>Temperature: city max</td>
                <td>${report.weather.temperature.cityMax} °C</td>
            </tr>
            <tr>
                <td>Precipitation</td>
                <td>${report.weather.precipitation}</td>
            </tr>
            <tr>
                <td>Wind</td>
                <td>${report.weather.wind.direction} ${report.weather.wind.speed} m/s</td>
            </tr>
            <tr>
                <td>Humidity</td>
                <td>${report.weather.atmosphere.humidity} %</td>
            </tr>
            <tr>
                <td>Pressure</td>
                <td>${report.weather.atmosphere.pressure} hPa</td>
            </tr>
            <tr>
                <td>Clouds</td>
                <td>${report.weather.atmosphere.clouds}</td>
            </tr>
            <tr>
                <td>Visibility</td>
                <td>${report.weather.atmosphere.visibility} m</td>
            <tr>
            <tr>
                <td>Sunrise</td>
                <td>${report.weather.sun.sunrise} UTC</td>
            </tr>
            <tr>
                <td>Sunset</td>
                <td>${report.weather.sun.sunset} UTC</td>
            </tr>
        </table>
    </#list>
</body>
</html>
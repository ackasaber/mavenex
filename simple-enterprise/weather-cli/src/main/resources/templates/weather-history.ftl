********************************************************************************
History of weather requests at ${location}
<#list reports as report>
********************************************************************************
${report.city.city}, ${report.city.countryCode} at ${report.updatedAt}

Summary                 ${report.weather.summary}
Temperature: current    ${report.weather.temperature.current} °C
Temperature: feels like ${report.weather.temperature.feelsLike} °C
Temperature: city min   ${report.weather.temperature.cityMin} °C
Temperature: city max   ${report.weather.temperature.cityMax} °C
Precipitation           ${report.weather.precipitation}
Wind                    ${report.weather.wind.direction} ${report.weather.wind.speed} m/s
Humidity                ${report.weather.atmosphere.humidity}%
Pressure                ${report.weather.atmosphere.pressure} hPa
Clouds                  ${report.weather.atmosphere.clouds}
Visibility              ${report.weather.atmosphere.visibility} m
Sunrise                 ${report.weather.sun.sunrise} UTC
Sunset                  ${report.weather.sun.sunset} UTC
</#list>
********************************************************************************

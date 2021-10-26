package mavenbook.enterprise.test;

import java.time.LocalDateTime;
import org.springframework.core.convert.converter.Converter;

/**
 * Datetime converter.
 * 
 * Spring context doesn't support LocalDateTime as property values.
 * Registering this converter fixes this problem.
 */
public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        return LocalDateTime.parse(source);
    }
}

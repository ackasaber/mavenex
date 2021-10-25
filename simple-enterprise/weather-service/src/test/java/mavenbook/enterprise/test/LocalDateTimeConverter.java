package mavenbook.enterprise.test;

import java.time.LocalDateTime;
import org.springframework.core.convert.converter.Converter;

public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        return LocalDateTime.parse(source);
    }
}

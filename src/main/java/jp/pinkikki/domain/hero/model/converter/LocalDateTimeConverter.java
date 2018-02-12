package jp.pinkikki.domain.hero.model.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * {@link org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters}をスキャンしているの不要ではあるが、
 * とりあえず作成したので残しておく。（{@link Converter}はコメントアウト）
 */
//@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime dateTime) {
        return dateTime == null ? null : Timestamp.valueOf(dateTime);

    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp value) {
        return value == null ? null : value.toLocalDateTime();
    }
}

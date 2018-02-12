package jp.pinkikki.domain.hero.model.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Time;
import java.time.LocalTime;

/**
 * {@link org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters}をスキャンしているの不要ではあるが、
 * とりあえず作成したので残しておく。（{@link Converter}はコメントアウト）
 */
//@Converter(autoApply = true)
public class LocalTimeConverter implements AttributeConverter<LocalTime, Time> {

    @Override
    public Time convertToDatabaseColumn(LocalTime time) {
        return time == null ? null : Time.valueOf(time);

    }

    @Override
    public LocalTime convertToEntityAttribute(Time value) {
        return value == null ? null : value.toLocalTime();
    }
}

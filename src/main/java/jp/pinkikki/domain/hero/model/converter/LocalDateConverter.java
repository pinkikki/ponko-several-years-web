package jp.pinkikki.domain.hero.model.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

/**
 * {@link org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters}をスキャンしているの不要ではあるが、
 * とりあえず作成したので残しておく。（{@link Converter}はコメントアウト）
 */
//@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate date) {
        return date == null ? null : Date.valueOf(date);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date value) {
        return value == null ? null : value.toLocalDate();
    }
}

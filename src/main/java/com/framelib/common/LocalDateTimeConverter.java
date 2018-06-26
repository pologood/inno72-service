package com.framelib.common;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime entityValue) {

		return entityValue == null ? null : Timestamp.valueOf(entityValue);

	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp databaseValue) {

		return databaseValue == null ? null : databaseValue.toLocalDateTime();

	}
}

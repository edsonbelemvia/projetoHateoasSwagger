package br.com.via.utils;

import java.io.IOException;
import java.time.LocalDateTime;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class JsonDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

	@Override
	public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationcontext)
			throws IOException, JsonProcessingException {
		String value = jsonParser.getText();
		try {
			return LocalDateTime.parse(value, JsonDateTimeSerializer.DATE_FORMATTER);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e);
		}

	}

}

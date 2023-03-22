package br.com.via.utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonDateTimeSerializer extends JsonSerializer<LocalDateTime> {

	static String padrao = "yyyy-MM-dd HH:mm:ss";
	public static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(padrao);

	@Override
	public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		try {
			String formattedDate = DATE_FORMATTER.format(value);
			gen.writeString(formattedDate);
		} catch (Exception ex) {
			System.err.println(ex);
			gen.writeString("");
		}
	}

}
package br.com.via.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomDateSerializer extends StdSerializer<Date> {

	private static final long serialVersionUID = 1L;
	private transient SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public CustomDateSerializer(String dateFormat) {
		super(CustomDateSerializer.class, true);
		sdf = new SimpleDateFormat(dateFormat);
		sdf.setTimeZone(new SimpleTimeZone(0, "UTC"));
	}

	public CustomDateSerializer() {
		this("yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		synchronized (sdf) {
			gen.writeString(sdf.format(value));
		}
	}

}

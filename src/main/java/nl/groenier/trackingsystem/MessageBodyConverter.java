package nl.groenier.trackingsystem;

import com.google.gson.Gson;

public class MessageBodyConverter {

	static Gson gson = new Gson();

	public static <E> String serialize(E object) {
		return gson.toJson(object);
	}

	public static <T> T deserialize(String messageBody, Class<T> toClass) {

		T anObject = gson.fromJson(messageBody, toClass);
		return anObject;
	}

}

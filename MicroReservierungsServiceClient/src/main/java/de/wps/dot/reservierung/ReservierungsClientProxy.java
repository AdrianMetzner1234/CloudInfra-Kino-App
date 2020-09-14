package de.wps.dot.reservierung;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import de.wps.dot.reservierung.dto.DtoConverter;
import de.wps.dot.reservierung.dto.SitzplatzbelegungDto;

public class ReservierungsClientProxy {
	private static final Gson gson = new Gson();

	private final URI serviceUri;

	public ReservierungsClientProxy() {
		String reservierungHostname = System.getenv("RESERVIERUNGSSERVICE_HOSTNAME") != null
				? System.getenv("RESERVIERUNGSSERVICE_HOSTNAME")
				: System.getProperty("reservierungsservice.hostname") != null
					? System.getProperty("reservierungsservice.hostname")
					: "localhost:45000";
		serviceUri = URI.create("http://"+ reservierungHostname);
	}

	public List<Saal> getSäle() throws UnirestException {

		// wirft ggf. NamingException
		URI uri = URI.create(serviceUri.toString() + "/säle");

		List<Saal> säle;

		HttpResponse<String> response = Unirest.get(uri.toString()).asString();
		Type saalListType = new TypeToken<List<Saal>>() {}.getType();
		säle = gson.fromJson(response.getBody(), saalListType);

		return säle;
	}

	public Saal getSaal(String saalId) throws UnirestException {
		// wirft ggf. NamingException
		URI uri = URI.create(serviceUri.toString() + "/säle/" + saalId);

		HttpResponse<String> response = Unirest.get(uri.toString()).asString();
		Type saalType = new TypeToken<Saal>() {}.getType();
		Saal saal = gson.fromJson(response.getBody(), saalType);

		return saal;
	}

	public Sitzplatzbelegung getSitzplatzbelegung(String vorführungsid) throws UnirestException {
		URI uri = URI.create(serviceUri.toString() + "/sitzplatzbelegung/" + vorführungsid);

		HttpResponse<String> response = Unirest.get(uri.toString()).asString();
		System.out.println("Reservierung-Service: " + response.getStatus());
		System.out.println("Reservierung-Service-Body: " + response.getBody());
		SitzplatzbelegungDto sitzplatzbelegungDto = gson.fromJson(response.getBody(), SitzplatzbelegungDto.class);
		Sitzplatzbelegung sitzplatzbelegung = DtoConverter.vonDto(sitzplatzbelegungDto);
		return sitzplatzbelegung;
	}

	public Reservierung bucheSitze(String vorführungsid, String sitzNummernString, String contentType)
			throws UnirestException {
		URI uri = URI.create(serviceUri.toString() + "/booking/" + vorführungsid);

		HttpResponse<String> response = Unirest.post(uri.toString()).header("Content-Type", contentType)
				.body(sitzNummernString).asString();
		Reservierung reservierung = gson.fromJson(response.getBody(), Reservierung.class);

		return reservierung;
	}
}

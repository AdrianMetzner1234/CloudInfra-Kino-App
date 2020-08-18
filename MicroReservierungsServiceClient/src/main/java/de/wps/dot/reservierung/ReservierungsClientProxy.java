package de.wps.dot.reservierung;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ReservierungsClientProxy {
	private static Gson gson = new Gson();

	private URI serviceUri;

	public ReservierungsClientProxy() {
		String reservierungHostname = System.getenv("RESERVIERUNGS_SERVICE_HOSTNAME");
		serviceUri = URI.create("http://"+ reservierungHostname);
	}

	public List<Saal> getSäle() throws UnirestException {

		// wirft ggf. NamingException
		URI uri = URI.create(serviceUri.toString() + "/säle");

		List<Saal> säle = null;

		HttpResponse<String> response = Unirest.get(uri.toString()).asString();
		Type saalListType = new TypeToken<List<Saal>>() {
		}.getType();
		säle = gson.fromJson(response.getBody(), saalListType);

		return säle;
	}

	public Sitzplatzbelegung gibSitzplatzbelegung(String vorführungsid) throws UnirestException {
		URI uri = URI.create(serviceUri.toString() + "/sitzplatzbelegung/" + vorführungsid);

		HttpResponse<String> response = Unirest.get(uri.toString()).asString();
		System.out.println("Reservierung-Service: " + response.getStatus());
		System.out.println("Reservierung-Service-Body: " + response.getBody());
		Sitzplatzbelegung sitzplatzbelegung = gson.fromJson(response.getBody(), Sitzplatzbelegung.class);

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

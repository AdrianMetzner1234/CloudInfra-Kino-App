# Der ReservierungsService
Es handelt sich um einen Microservice für einen Kino dessen Verantwortlichkeit es ist Reservierungen für Kinovorstellungen anzunehmen.

## Code-Konventionen
Der Quellcode wird komplett auf deutsch geschrieben es war ursprünglichein Funfact und hat sich nun so durchgesetzt. 

Umlaute sind daher auch wo immer es möglich ist willkommen. 

Die **einzige Ausnahme** sind die Auszüge der BeanConvention, die halten wir ein, da es sonst die Arbeit mit einigen Frameworks insbesondere bei der Serialisierung  erschwert. Statt `gib` etwa nutzen wir `get` oder statt `setze` ziehen wir `set` vor.

## Schnittstellen
### Health-Entpoints (Gesundheitsendpunkte)
Der Health Status kann über folgende Endpunkte unter `gesundheit` abgefragt werden:
- http://localhost:45000/gesundheit/gesund gibt immer, wenn er erreichbar ist den HTTP-Code 200 (OK) zurück.
- http://localhost:45000/gesundheit/gesund-aber-langsam gibt immer, wenn er erreichbar ist den HTTP-Code 200 (OK) zurück. Ist dabei aber eine echte Schnecke und braucht 2 Minuten für die Antwort
- http://localhost:45000/gesundheit/häufig-scheiternd  scheitert sehr häufig aber nicht immer und gibt, sofern er erreichbar ist (in ca 70% der Anfragen) den HTTP-Code 503 (Service Unavailable) zurück, in den anderen ca. 30% gibt er den HTTP-Status 200 (OK) zurück.
- http://localhost:45000/gesundheit/scheiternd scheitert einfach immer und gibt, sofern er erreichbar ist den HTTP-Code 503 (Service Unavailable) zurück.

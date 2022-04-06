# README

### Starten

Starten der Main-methoden in at.fhv.lka2


## Verwendung
Wir empfehlen die Verwendung der Swagger-UI zum bedienen der Lösung, da keine andere Methode implementiert wurde.
Zu finden sind diese unter [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)
und [http://localhost:8082/swagger-ui/index.html](http://localhost:8082/swagger-ui/index.html).

Nachdem die Komponenten gestartet wurden, muss die [localhost:8082/subscribeHelper]("http://localhost:8082/subscribeHelper"), auf der Readside, aufgerufen werden.
Dadurch subscribed die Readside sich bei der Event-side.

Sollten bereits events im eventstore sein werden diese an die Readside gesendet.
Nachdem subscribed wurde erhält die Readside die Events. 

**Wichtig** zur Bedienung bei den Parametern *start* und *end* sind diese im Format
*yyyy-MM-dd* einzugeben. Diese Parameter sind bei den Endpoints *<readside>/availableRooms* *<readside>/bookings* und
*<write-side>/booking/room*

## Designanmerkungen
- TODO

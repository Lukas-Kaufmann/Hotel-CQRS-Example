# README

### Starten

Starten der Main-methoden in at.fhv.systemarchitectures.cqrs - Reihenfolge egal
Starten der Main-methoden über IDE empfohlen, kein executable jar generiert.

Das [Scenario](./src/test/java/scenario/WriteSideScenario.java) zeigt eine möglicher Ablauf von operationen auf dem System.
Wenn dass scenario nicht gestartet wird ist es nötig manuel den Enpoint [http://localhost:8082/subscribeHelper](http://localhost:8082/subscribeHelper) aufzurufen.


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

## System model
[see picture](./system_model.png)


## Designanmerkungen
- Readside kann sich zu jedem event subscriben und bei neuen subscribern werden vorherige events nach dem subscriben gesendet --> weil wir das ausprobieren wollten
- Entschieden für subclassing einer abstrakten event-Klasse -> subscriber können sich zu einzelnen konkreten Eventklassen 
subscriben -> dadurch folgt dass jedes event alle informationen zur verarbeitung beinhalten muss
- auf der Readside wurde, damit der vorteil von CQRS ausgenutzt wird ein stark verändertes model für die Query zur abfrage
der verfügbaren Räume eingesetzt. d.h. es wird direkt gespeichert wieviele Räume sind an jedem Tag noch verfügbar, dadurch kann die anzahl ohne viel verarbeitungsaufwand abgelesen werden.



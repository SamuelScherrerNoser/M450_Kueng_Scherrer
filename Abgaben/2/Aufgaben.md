# Teststrategie

## Aufgabe 1

### Abstrakte Testfälle

| ID | Bedingung                    | Erwartetes Ergebnis |
|----|------------------------------|---------------------|
| 1  | Kaufpreis < 15'000           | Kein Rabatt         |
| 2  | 15'000 < Kaufpreis <= 20'000 | 5% Rabatt           |
| 3  | 20'000 < Kaufpreis < 25'000  | 7% Rabatt           |
| 4  | Kaufpreis >= 25'000          | 8.5% Rabatt         |


### Konkrete Testfälle

| ID | Kaufpreis                    | Erwartetes Ergebnis |
|----|------------------------------|---------------------|
| 1  | 14'500                       | Kein Rabatt         |
| 2  | 16'000                       | 5% Rabatt           |
| 3  | 22'000                       | 7% Rabatt           |
| 4  | 25'500                       | 8.5% Rabatt         |

## Aufgabe 2

| ID | Beschreibung                       | Erwartetes Resultat                                          | Effektives Resultat | Status | Mogliche Ursache |
|----|------------------------------------|--------------------------------------------------------------|---------------------|--------|------------------|
| 1  | Website Läuft                      | Website ist über die Domain erreichbar                       |                     |        |                  |
| 2  | Angebote sind verfügbar            | Verfügbare Angebote werden angezeigt                         |                     |        |                  |
| 3  | Auto ist mietbar                   | Es kann ein Auto gemietet werden                             |                     |        |                  |
| 4  | Preis ist korrekt                  | Der korrekte Preis wird berechnet und anschliessend verbucht |                     |        |                  |
| 5  | Buchung wird im System verzeichnet | Im System ist die neue Buchung für den Betreiber einsehbar   |                     |        |                  |

## Aufgabe 3

### Mögliche Black-Box Testfälle

Man könnte verschiedene Eingaben testen und die Reaktionen prüfen. So könnten
auch Randfälle über die normale Eingabe geprüft werden.

### Testbare Methoden

#### Account.java

- deposit
- withdraw
- pseudoDeleteAccount

#### Bank.java

- createAccount
- addAccount
- deleteAccount
- getAccount
- getNumberOfAccounts

#### Counter.java

- convertCurrency
- getExchangeRate

### Mögliche Verbesserungen

Aktuell sind alle Methoden auf public, was nicht sehr vorteilhaft ist. Ausserdem
sollten die Methoden besser unterteilt werden um eine gründlichere Testung zu
ermöglichen. Auch die  Benutzerinteraktion sollte besser ausgelagert werden.


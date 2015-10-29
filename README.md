#BlackJack casino game

##RESTful webservice
Game is based on standard rules of casino BlackJack.
 
Technologies used: Spring, Hibernate Database: PostgreSQL, jUnit, log4j, Maven, JAX-RS, Jersey, Jetty, Mockito, json


                        
##Pre run configuration

1. Start PostgresDB Server -> Create DB and add tables (scripts in init_db.sql)
2. To add some (test) players and transactions to db -> vi.talii.AddPlayersToDB

*Run on web-services*
Maven -> Plugins -> jetty -> jetty:run

OR

mvn jetty:run - in terminal


--
##Links for game

http://localhost:8080/BlackJack/rest/blackjack/deal/{player_id}/{cash}  -  start new game
http://localhost:8080/BlackJack/rest/blackjack/deal/{game_id}/hit       -  hit'
http://localhost:8080/BlackJack/rest/blackjack/deal/{game_id}/stand     -  stand'

    ' - game_id returns to you in json when you start new game

--
##Links for game stats

http://localhost:8080/BlackJack/rest/players                            - list of all players with their transactions
http://localhost:8080/BlackJack/rest/players/{player_id}                - all information about player = {player_id}
http://localhost:8080/BlackJack/rest/players/{player_id}/transactions   - all transactions by player = {player_id}
http://localhost:8080/BlackJack/rest/players/transactions               - all transactions by all players
http://localhost:8080/BlackJack/rest/players/{layer_id}/{cash}          - add {cash} to player = {player_id}

--
##Testing

Test don't depend on DB, run with Mockito
Test start with run of jetty server

--
##Properties

DB properties are in            app.properties

Logger properties are in        log4j.properties


                            **developed by Vitalii Balytskyi, 2015**


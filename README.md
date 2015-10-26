# BlackJackWebService
Restful Web service that performs Black Jack game logic. 
                            
                             Quick Black Jack Guide 
* To get user by id - BlackJackWebService/users/{id}

* First of all you should to create account - BlackJackWebService/users/add/{name}/{balance}

* Then signin by id -  BlackJackWebService/users/signin/{id}

* Get your balance -   BlackJackWebService/users/balance

* To fill your balance - BlackJackWebService/users/edit_balance/{sum}

* To start game with your bet - BlackJackWebService/game/deal/{bet}

* Hit - BlackJackWebService/game/hit

* Stand - BlackJackWebService/game/stand

* To see result or status of game in any time - BlackJackWebService/game/deal/result

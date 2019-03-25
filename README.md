# CSE248ParkingGarage

# TODO

* ~~Create burndown list~~
* ~~Create burndown chart~~
* Learn Android Studio
* ~~UML~~
  * ~~Class diagram~~
  * ~~Use case diagram~~
* Create test cases
* Write the code
  * Create JavaDoc as code is written

##### After the app can be run locally on one device, add some of the following

* Transfer the data over to a database
  * Use SQL, mySQL, mongoDB or something else
* Host the database on a remote server
  * Have to learn how to create the backend or use firebase

![burndown-chart](https://github.com/Dennis12Hahn/CSE248ParkingGarage/blob/master/burndown-chart.svg "Burndown Chart")

# Use of Data Structures

A garage will contain multiple bags.

A level bag will contain each level object of the garage in an array. For now the array will have a size of 1 signifying the one and only level in the garage.

Each level will contain a priority queue of spaces. The priority queue will be based on the distance from the space to the exit in ascending order and the occupied status of the space. For example, the first space in the queue will be the closest space to the exit if it is not occupied.

The garage will contain a user bag which utilizes a hash map with the key corresponding to the unique username and the value corresponding to the user object itself. The hash map will allow for fast searching of accounts for login and account creation purposes.

The garage will contain a vehicle bag (similar to the user bag) utilizing a hash map with the key corresponding to the unique license plate of each vehicle and the value corresponding to the vehicle object itself. The hash map will allow fast searching for vehicles already registered in the garage and accounts for the uniqueness of license plates tied to vehicles.

Each attendant will also have a vehicle bag so they are able to have multiple vehicles tied to their account.

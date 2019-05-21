# Parking Garage

# Use of Data Structures

A Garage contains a Map and two bags. 

The Map is a HashMap which maps a String to a Stack. The String key represents the license plates of all vehicles that of been parked in the parking garage. The Stack value is a Stack of Document objects. Since the receipt contains all the information of a ticket, they are combined into one Document object. When a vehicle is parked, just the relevant ticket information is used and when a vehicle is retrieved the Document object is retrieved from the Stack by simply peeking the top and gets updated with the relevant receipt information. Whenever a vehicle is parked, a new Document object is created. If the license is found in the Map, that Document gets pushed onto the proper Stack. If the license is not found, a new Stack is created and the Document gets pushed onto it and is put onto the Map with the license as a new entry.

The first bag is a UserBag. The UserBag is a HashMap mapping String keys to User objects as values. The String key is the generated username of the User object and the User value is the User object with that username. There are two types of Users, Attendants and Managers. Both extend the User class and the Manager class extends the Attendant class. The HashMap is used to store all the Attendants and Managers at the garage and log them in quickly.

The second bag is a SpaceBag. The SpaceBag contains all the data associated with the spaces in the parking garage. It contains three PriorityQueues, one for each type of space (Motorcycle, Car, Truck). Each of the queues are sorted based first on the occupied status of the space and second on the distance to the exit. The spaces that are unoccupied and closest to the exit are closer to the head of the queue. When a Space is requested to be parked in, the vehicle is taken in and the smallest and closest open space is returned. The Java PriorityQueue will only reorder when an element is added or removed. So when a vehicle is being parked and one of the Space queues gets polled, the occupied status of that Space gets updated (true when parking, false when retrieving) and is added back to the queue. When a vehicle is being retrieved, the Space that the vehicle is parked in will be removed from the queue, updated, and added back to the queue so it finds its correct place in the queue.

![burndown-chart](https://github.com/Dennis12Hahn/CSE248ParkingGarage/blob/master/burndown-chart.svg "Burndown Chart")

* Learning and tying the Android UI to the project was compiled into one task. This is why task #25 is significantly larger than the rest.

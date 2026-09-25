# LICEO RIDE Semi-Final Laboratory Exam Answers

Name: Juhaner M. Mogawan
Section: BSIT 2-2

## Question 1
Why is the list in RideManager typed ArrayList<Ride> and not ArrayList<Jeepney>?

Using ArrayList<Ride> allows RideManager to store every vehicle type (Jeepney, Tricycle, and Taxi) together in a single list using upcasting. If it was typed as ArrayList<Jeepney>, it can only hold Jeepneys and it will reject all other rides so this design lets us call methods like fare() and printTicket() on any ride polymorphically.

## Question 2
In showStudentDiscounts(), why must you check instanceof before the cast?

We check instanceof because not all the Ride objects implement StudentDiscount specifically Taxi does not. The check makes sure we only downcast objects that actually have a student discounts. If we try to cast a Taxi to StudentDiscount without checking, Java will throw a ClassCastException and crash.

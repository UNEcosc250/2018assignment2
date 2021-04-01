# Assignment 2: Mixing functional and imperative code

Previously, we've talked about how we can describe a changing system
as a series of immutable states. We're going to do this to implement
a simulation.

This is going to cause you to write:

* Functional code for how you produce the next state from the current 
  state
* A small number of mutable structures using imperative code for remembering the states 
  and controlling the simulation. (And painting it.)

This year, the simulation is *boids*.

## Boids

Boids ("bird-oids") is a simulation of flocking introduced in a paper by
Craig Reynolds in 1987. You can see an online version of how these boids
behave on the Processing site, here:

https://processing.org/examples/flocking.html

Each boid has a position and a velocity. At each step, the boid will 
move according to its velocity

    newPosition = position + velocity

And change velocity based on a calculated acceleration

    newVelocity = velocity + acceleration
    
Note that there's no measure of how long the timestep is. 

The acceleration is generally calculated as a mix of three forces for each
boid:

* Separation - if other boids are too close, it will try to move away from them
* Alignment - it will try to match the velocity and direction of other boids within 50 pixels
* Cohesion - it will try to steer towards the middle of its neighbours (where "neighbours" is all boids within 50 pixels)

Boids have a maximum velocity, and a maximum acceleration they can apply when steering

In our assignment 2, you're going to be implementing an augmented boids
simulation using a mixture of functional and imperative programming.

## Mixing functional and imperative programming

Your aim is to keep your code as functional as you can, but you're going
to be led into having a few places where there are imperative concepts too.

For example, your simulation is going to keep an action replay memory.

This is going to require you to store past states of the simulation as 
you've played them -- something that works well with the *current state*
being an immutable `Seq` of immutable `Boids`. 

But the memory itself uses a mutable data structure
(I've picked `mutable.Queue`) so that it can fill over time, and reset
when you click a button

There are also various mutable properties that we're going to add to our
simulation:

* The wind
* The ability to "startle" the boids by applying a random impulse to
  each boid in the next frame
* The ability to tell the simulation that at the next frame, it should
  add a boid

## What's provided

I have provided most of the classes, but I've stripped out some of their
implementation.

Fully implemented, you have:

* `Vec2` -- a class that can do vector addition and multiplication. So
   that if you say `newPosition = position + velocity` it just works.
  
* `BoidsPanel` -- a panel that knows how to draw a `Seq[Boid]`

  
Partially implemented, you have: 
   
* `Boid` -- an immutable representation of a Boid. You will need to
   work functionally to produce new sequences of `Boid`
   
* `Simulation` -- a class for storing the simulation states. This
   contains a mutable queue for the simulation memory, and some 
   mutable variables that let you alter what happens on the next 
   simulation step

* `BoidsApp` -- the runnable program, that creates a window, and adds the
  `BoidsPanel` and some buttons. Note that the buttons' event handlers 
  have not all yet been implemented.
  
## What the components need to do

* The **Action Replay** button must take the simulation back to the start of 
  the memory buffer (typically one second) and let the simulation continue 
  again from there.  
  Hint: think about which end you want to insert the frame.
  
* The **wind** buttons should set the wind strength and direction. As the
  boids' velocity is normalised on the next tick, in practice this works as a
  fairly effective flock steering mechanism. If you set the wind blowing right, 
  you should see the flock sweep around to follow the wind.
  
* The **Startle boids** button should cause the simulation, at the next time
  step, to perturb each boid by a velocity of `startleStrength` in a random 
  direction (a different random direction for each boid)
  
* The **Regenesis** button should push a frame into the queue with `numBoids` boids heading from the
  centre in random direction. Note this shoud *not* clear the queue -- hitting
  **Action Replay** quickly after hitting **Regenesis** should jump back
  into one of the old states. 

* Clicking a point on the canvas should add a new boid into the simulation, 
  heading in a random direction with a velocity of 1

* There are also three labels at the foot of the simulation, which are intended to show some
  data about the flock. (These are drawn from `Simulation.flockDir`, `Simulation.flockSpeed`, and `Simulation.flockSep`
  which you'll need to implement.)

  - Flock direction: The average direction of the flock. (Sum the velocity vectors and take its theta). You should be
    able to see if this is working by seeing how it changes with the wind buttons

  - Flock speed: The average speed of the flock. (Take the average of the magnitude of the velocity vectors). This should
    stay more or less constant, because of how boids work

  - Flock separation: The variance of the position of the flock. This one's a little trickier. Find the centroid of the flock
    (average the position vectors). Then for each boid, calculate the square of its distance from the centroid. Return the 
    mean of that. To test this, hit the "explosion of boids" button. It should drop to nearly zero and then grow.

## Note on the provided code

I've provided this code by writing a solution and then cutting back from it.
But it's possible you might not like some parts of my code. You can delete
and change *any code you like* -- your task is to write the simulation, not
to retain my code.

## Marking

The marking is aimed to be able to be done quickly, with the written feedback being
more formative and open-ended.

Functionality: 

* The boids simulation works: 7
* Adding a boid by clicking the canvas works: 1
* Wind works: 1
* Startle works: 1
* Regenesis works: 1 
* Action replay works: 1
* Mean direction, separation, and velocity works: 3 (1 each)

Quality: 

* Overall quality judgment (functional, readable, tidy, concise): 5

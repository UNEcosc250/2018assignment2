package cosc250.boids

import scala.collection.mutable

object Simulation {

  /** Wrap width of the simulation. ie, for any Boid, 0 <= x < 640 */
  val width = 640

  /** Wrap height of the simulation. ie, for any Boid, 0 <= y < 480 */
  val height = 480

  /** How many frames of the simulation to hold */
  val frameMemory = 60

  /** How manby boids to start with in the simulation */
  val numBoids = 150

  /** When the wind is blowing, how strongly it blows */
  val windStrength = 0.03

  /** When the boids are startled, the strength of the vector that is applied to each of them */
  val startleStrength:Double = Boid.maxSpeed

  /** A function that will "startle" a boid */
  def startleFunction(b:Boid):Vec2 = ???

  /** A mutable queue containing the last `frameMemory frames` */
  val queue:mutable.Queue[Seq[Boid]] = mutable.Queue.empty[Seq[Boid]]

  /** The wind -- an optional acceleration vector */
  var wind:Option[Vec2] = None

  /**
    * Sets a wind blowing at windStrength, at this angle.
    * Note that a northerly wind blows **from** the north, so we multiply the vector by -1.
    */
  def setWindDirection(theta:Double):Unit = {
    ???
  }

  /** A container that can hold a boid to add on the next frame */
  var insertBoid:Option[Boid] = None

  /**
    * A function that will run for the next frame only over each boid in the system,
    * producing an acceleration vector to add to a Boid
    */
  var oneTimeFunction:Option[Boid => Vec2] = None

  /**
    * Resets the events that should occur one time only
    */
  def resetOneTimeEvents():Unit = {
    ???
  }

  /** The current frame */
  def current = ???

  /** Generates boids in the centre of the simulation, moving at v=1 in a random direction */
  def explosionOfBoids(i:Int):Seq[Boid] = 
    ???

  /** Pushes a state into the queue */
  def pushState(boids:Seq[Boid]):Seq[Boid] = {
    ???

    // Drops a frame from the queue if we've reached the maximum number of frames to remember
    if (queue.lengthCompare(frameMemory) > 0) queue.dequeue()
    boids
  }

  /** Called by a click to the canvas, to say that in the next frame, a boid should be inserted */
  def pushBoid(b:Boid):Unit = {
    insertBoid = Some(b)
  }

  /** Called by the Action Replay button to jump back in the memory buffer */
  def resetQueue():Seq[Boid] = {
    ???
  }

  /** Generate the next frame in the simulation */
  def update():Seq[Boid] = {
    ???
  }


  /** The current average direction of the flock. Add up all the boids' velocity vectors, and take the theta. */
  def flockDir:Double = 
    println("Warning, you haven't implemented flockDir!")
    0d

  /** The current average speed of the flock. Take the mean of all the boids' velocity magnitudes. */
  def flockSpeed:Double = 
    println("Warning, you haven't implemented flockSpeed!")
    0d

  /** 
   * The variance of the flock's positions, ignoring the fact we wrap around the screen. 
   * To get this one:
   *   * Calculate the centroid of the flock (Add all the position vectors, and divide by the number of boids)
   *   * Calculate the square of the distance of each boid from this centroid, and sum them. 
   *      i.e., sum Math.pow((b.position - centroid).magnitude, 2)
   *   * Divide this by the number of boids.
   * 
   * We'll probably eyeball the code for this one, given we're going to find it harder to eyeball whether the number
   * on the screen looks right!
   */ 
  def flockSep:Double = 
    println("Warning, you haven't implemented flockSep!")
    0d

}

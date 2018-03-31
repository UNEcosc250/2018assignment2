package cosc250.boids

/**
  * A boid (bird-oid). It has a position and a velocity.
  *
  *
  * https://processing.org/examples/flocking.html
  */
case class Boid(
  position:Vec2, velocity:Vec2
) {

  /**
    * Calculates an acceleration vector that will cause it to maintain a minimum
    * separation from its closest neighbours
    * This steer is limited to maxForce
    */
  def separate(others:Seq[Boid]):Vec2 = {
    ???
  }

  /**
    * Calculates an acceleration vector that will cause it align its direction and
    * velocity with other birds within Boid.neighbourDist
    * This alignment force is limited to maxForce
    */
  def align(others:Seq[Boid]):Vec2 = {
    ???
  }

  /**
    * Calculates an acceleration that will steer this boid towards the target.
    * The steer is limited to maxForce
    */
  def seek(targetPos:Vec2):Vec2 = {
    ???
  }


  /**
    * Calculates an acceleration that will keep it near its neighbours and maintain
    * the flock cohesion
    */
  def cohesion(others:Seq[Boid]):Vec2 = {
    ???
  }


  /**
    * Calculates a flocking acceleration that is a composite of its separation,
    * align, and cohesion acceleration vectors.
    */
  def flock(others:Seq[Boid]):Vec2 = {
    ???
  }

  /**
    * Produces a new Boid by adding the boid's velocity to its position, and adding
    * the acceleration vector to the boid's velocity. Note that there is no division
    * by timestep -- it's just p = p + v, and v = v + a
    *
    * Also note that we don't apply the limiting on maxForce in this function -- this is
    * so that the startle effect can dramatically perturb the birds in a way they would
    * not normally be perturbed in flight. Instead, limit maxForce in the flock function
    * (or the functions it calls)
    *
    * We do, however, limit a boid's velocity to maxSpeed in this function. But we do it
    * *before* we add the influence of the wind to the boid's velocity -- it's possible
    * to fly faster downwind than upwind.
    */
  def update(acceleration:Vec2, wind:Vec2):Boid = {
    ???
  }

  def wrapX(x:Double):Double = {
    if (x > Boid.maxX) x - Boid.maxX else if (x < 0) x + Boid.maxX else x
  }

  def wrapY(y:Double):Double = {
    if (y > Boid.maxY) y - Boid.maxY else if (y < 0) y + Boid.maxY else y
  }
}

object Boid {
  /** How far apart the boids want to be */
  val desiredSeparation = 25

  /** Maximum flying velocity of a boid */
  val maxSpeed = 2

  /** maximum accelaration of a boid */
  val maxForce = 0.03

  /** Other boids within this range are considered neighbours */
  val neighBourDist = 50

  /** Wrap width of the simulation. ie, for any Boid, 0 <= x < 640 */
  def maxX:Int = Simulation.width

  /** Wrap height of the simulation. ie, for any Boid, 0 <= y < 480 */
  def maxY:Int = Simulation.height
}



package cosc250.boids

import java.awt._
import javax.swing.JPanel

/**
  * A panel that knows how to paint a sequence of Boids
  *
  * Note, this is a JPanel and has methods such as addMouseListener
  */
class BoidsPanel extends JPanel {

  val backgroundColour:Color = Color.DARK_GRAY

  /**
    * Updates the sequence of boids that will be painted, and queues a repaint to happen on the next render cycle
    */
  def setBoids(bs:Seq[Boid]) = {
    boids = bs
    this.repaint()
  }

  /** Contains the boids we will be rendering on the next render cycle */
  private var boids:Seq[Boid] = Seq.empty

  override def getMinimumSize = new Dimension(Simulation.width, Simulation.height)
  override def getPreferredSize = new Dimension(Simulation.width, Simulation.height)
  override def getMaximumSize = new Dimension(Simulation.width, Simulation.height)

  /** Paints our boids */
  override def paintComponent(gAwt:Graphics): Unit = {
    val g = gAwt.asInstanceOf[Graphics2D]

    /** A boid is a triangle */
    def boidShape:Shape = {
      new Polygon(Array(-4, 4, -4), Array(-3, 0, 3), 3)
    }

    /** Paints a single boid */
    def paintBoid(b:Boid):Unit = {
      // Remember the old graphics transform so we can reset it
      val xform = g.getTransform

      // Set the transform to the boid's position, pointed in the direction of the
      // boid's velocity
      val Vec2(bx, by) = b.position
      g.translate(bx, by)
      g.rotate(b.velocity.theta)

      // draw a small triangle
      g.setColor(Color.WHITE)
      g.draw(boidShape)

      // Reset the graphics transform
      g.setTransform(xform)
    }

    // Clear the backgound
    g.setColor(backgroundColour)
    g.fillRect(0, 0, getWidth, getHeight)

    // Paint every boid
    boids.foreach(paintBoid)

  }

}

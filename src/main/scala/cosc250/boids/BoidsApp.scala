package cosc250.boids

import java.awt.event.{MouseAdapter, MouseEvent, MouseListener}
import java.awt.{BorderLayout, Dimension, FlowLayout, GridLayout}
import javax.swing._

import scala.collection.mutable
import scala.util.Random

object BoidsApp {

  /** The main window */
  val window = new JFrame()

  /** A panel to render our boids */
  val boidsPanel = new BoidsPanel

  /** Action replay button */
  val replay = new JButton("Action Replay")

  val nwWind = new JButton("<html>&#x2198;</html>")
  val nWind = new JButton("<html>&darr;</html>")
  val neWind = new JButton("<html>&#x2199;</html>")
  val wWind = new JButton("<html>&rarr;</html>")
  val stopWind = new JButton("<html>&times;</html>")
  val eWind = new JButton("<html>&larr;</html>")
  val swWind = new JButton("<html>&#x2197;</html>")
  val sWind = new JButton("<html>&uarr;</html>")
  val seWind = new JButton("<html>&#x2196;</html>")

  val startle = new JButton("Startle boids")

  val regenesis = new JButton("Regenesis")

  def main(args:Array[String]):Unit = {

    val container = new JPanel()
    container.setLayout(new BorderLayout())
    container.add(boidsPanel, BorderLayout.CENTER)

    val controlsContainer = Box.createVerticalBox()

    val windPanel = new JPanel()
    windPanel.setLayout(new GridLayout(3, 3))
    windPanel.add(nwWind)
    windPanel.add(nWind)
    windPanel.add(neWind)
    windPanel.add(wWind)
    windPanel.add(stopWind)
    windPanel.add(eWind)
    windPanel.add(swWind)
    windPanel.add(sWind)
    windPanel.add(seWind)
    windPanel.setAlignmentX(0)

    val windStrength = Simulation.windStrength

    nwWind.addActionListener((_) => Simulation.setWindDirection(Vec2.NW))
    nWind.addActionListener((_) => Simulation.setWindDirection(Vec2.N))
    neWind.addActionListener((_) => Simulation.setWindDirection(Vec2.NE))
    wWind.addActionListener((_) => Simulation.setWindDirection(Vec2.W))
    stopWind.addActionListener((_) => Simulation.wind=None)
    eWind.addActionListener((_) => Simulation.setWindDirection(Vec2.E))
    swWind.addActionListener((_) => Simulation.setWindDirection(Vec2.SW))
    sWind.addActionListener((_) => Simulation.setWindDirection(Vec2.S))
    seWind.addActionListener((_) => Simulation.setWindDirection(Vec2.SE))

    val wc = new JLabel("Wind controls")
    wc.setAlignmentX(0)
    controlsContainer.add(wc)
    controlsContainer.add(windPanel)


    controlsContainer.add(new JLabel("Actions"))
    controlsContainer.add(replay)
    controlsContainer.add(startle)
    controlsContainer.add(regenesis)
    controlsContainer.add(new JPanel())

    container.add(controlsContainer, BorderLayout.EAST)

    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    window.add(container)
    window.setSize(container.getPreferredSize)
    window.setVisible(true)

    Simulation.pushState(Simulation.explosionOfBoids(Simulation.numBoids))

    replay.addActionListener({ (evt) =>
      Simulation.resetQueue()
    })

    startle.addActionListener({ (evt) =>
      Simulation.oneTimeFunction = Some(Simulation.startleFunction)
    })

    regenesis.addActionListener({ (evt) =>
      Simulation.pushState(Simulation.explosionOfBoids(Simulation.numBoids))
    })

    boidsPanel.addMouseListener(new MouseAdapter {
      override def mouseClicked(e: MouseEvent):Unit = {
        Simulation.pushBoid(Boid(Vec2(e.getX, e.getY), Vec2.randomDir(1)))
      }
    })

    val timer = new Timer(16, (e) => {
      boidsPanel.setBoids(Simulation.update())
    })
    timer.start()

  }

}

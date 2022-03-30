package cosc250.boids

/**
  * This code is provided.
  */
class Vec2Suite extends munit.FunSuite {

  test("Vec2 should be able to add vectors") {
    assertEquals(Vec2(1, 2) + Vec2(3, 4), Vec2(4, 6))
  }

  test("it should be able to subtract vectors") {
    assertEquals(Vec2(8, 9) - Vec2(3, 4), Vec2(5, 5))
  }

  test("it should be able to multiply a vector by a number") {
    assertEquals(Vec2(8, 9) * 4, Vec2(32, 36))
  }

  test("it should be able to divide a vector by a number") {
    assertEquals(Vec2(8, 6) / 2, Vec2(4, 3))
  }

  test("it should limit the size of a vector") {
    assertEquals(Vec2(10, 0).limit(5), Vec2(5, 0))
  }

  test("it should calculcate vectors from a direction and an angle in radians") {
    val Vec2(x, y) = Vec2.fromRTheta(4, Math.PI)

    // There could be a rounding error -- these are doubles, so floor them as
    // we know where Math.PI should go
    assertEquals(Vec2(x.floor, y.floor), Vec2(-4, 0))
  }


}

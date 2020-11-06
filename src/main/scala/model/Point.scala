package model

case class Point(x: Int, y: Int, pointType: PointType.Value) {

//  def changePointType(type: String) = Point()
}

object PointType extends Enumeration {
  val WATER, HIT, SHOT, OCCUPIED, SINK = Value
}

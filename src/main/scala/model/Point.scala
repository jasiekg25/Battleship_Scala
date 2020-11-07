package model

case class Point(x: Char, y: Int, var pointType: PointType.Value) {

//  def changePointType(type: String) = Point()

}

object PointType extends Enumeration {
  val WATER, HIT, SHOT, OCCUPIED, SINK = Value
}

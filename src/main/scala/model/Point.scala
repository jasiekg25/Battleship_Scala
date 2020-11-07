package model

case class Point(x: Char, y: Int, pointType: PointType.Value) {

//  def changePointType(type: String) = Point()
  def coordinates = (x,y)


}

object PointType extends Enumeration {
  val WATER, HIT, SHOT, OCCUPIED, SINK = Value
}

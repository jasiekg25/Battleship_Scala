package model

case class Point(x: Int, y: Int, pointType: PointType.Value) {

}

object PointType extends Enumeration {
  val WATER, HIT, SHOT, OCCUPIED, SINK = Value
}

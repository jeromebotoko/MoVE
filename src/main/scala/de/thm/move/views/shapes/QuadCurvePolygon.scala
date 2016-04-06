package de.thm.move.views.shapes

import de.thm.move.models.CommonTypes.Point
import de.thm.move.util.{BindingUtils, GeometryUtils}
import de.thm.move.views.Anchor
import scala.collection.JavaConverters._

/**
 * A polygon with quadratic Bezier curves as edge points.
 */
class QuadCurvePolygon(points:List[Point]) extends AbstractQuadCurveShape(points, true) {
  override def toUncurvedShape: ResizableShape = ResizablePolygon(this)
}

object QuadCurvePolygon {
  def apply(points:List[Double]):QuadCurvePolygon = {
    val singlePoints= for(idx <- 0 until points.size by 2) yield ( points(idx),points(idx+1) )
    new QuadCurvePolygon(singlePoints.toList)
  }

  def apply(polygon:ResizablePolygon):QuadCurvePolygon = {
    val curvedPolygon = QuadCurvePolygon(polygon.getPoints.asScala.map(_.doubleValue).toList)
    curvedPolygon.copyColors(polygon)
    curvedPolygon.setX(polygon.getX)
    curvedPolygon.setY(polygon.getY)
    curvedPolygon
  }
}
package de.thm.move.views.shapes

import javafx.beans.property.SimpleDoubleProperty
import javafx.scene.paint.Color
import javafx.scene.shape.Polygon
import de.thm.move.util.BindingUtils
import de.thm.move.views.{MovableAnchor, Anchor}

import de.thm.move.controllers.implicits.FxHandlerImplicits._
import de.thm.move.models.CommonTypes.Point

class ResizablePolygon(points:List[Double]) extends Polygon(points:_*) with ResizableShape with ColorizableShape {

  //create drag-drop anchors
  private val observablePoints = getPoints
  val getAnchors: List[Anchor] =
    (for(i <- 0 until observablePoints.size by 2) yield {
      val xIdx = i
      val yIdx = i+1
      val xProperty = new SimpleDoubleProperty(observablePoints.get(xIdx))
      val yProperty = new SimpleDoubleProperty(observablePoints.get(yIdx))

      xProperty.addListener({ (_: Number, newX: Number) =>
        val _ = observablePoints.set(xIdx, newX.doubleValue())
      })
      yProperty.addListener({ (_: Number, newX: Number) =>
        val _ = observablePoints.set(yIdx, newX.doubleValue())
      })

      val anchor = new Anchor(xProperty.get(), yProperty.get()) with MovableAnchor
      xProperty.bind(anchor.centerXProperty())
      yProperty.bind(anchor.centerYProperty())
      anchor
    }).toList

  BindingUtils.bindAnchorsTranslationToShapesLayout(this)(getAnchors:_*)
}

object ResizablePolygon {
  def apply(points:List[Point]) = {
    val singlePoints= points.flatMap { case (x,y) => List(x,y) }
    new ResizablePolygon(singlePoints)
  }
}
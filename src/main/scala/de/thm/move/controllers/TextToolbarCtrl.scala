/**
 * Copyright (C) 2016 Nicola Justus <nicola.justus@mni.thm.de>
 */

package de.thm.move.controllers

import java.util.ResourceBundle
import java.net.URL
import javafx.event.ActionEvent
import javafx.scene.control._
import javafx.scene.text.Font
import javafx.collections.FXCollections
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import implicits.FxHandlerImplicits._

import scala.collection.JavaConverters._

class TextToolbarCtrl extends Initializable {

  @FXML var fontFamilyChooser:ChoiceBox[String] = _
  @FXML var fontSizeChooser:ChoiceBox[Int] = _

  @FXML var fontColorLbl:Label = _
  @FXML var fontColorChooser:ColorPicker = _

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    fontColorChooser.setOnAction { _:ActionEvent =>
      fontColorLbl.setTextFill(fontColorChooser.getValue)
    }

    val fontNames = Font.getFamilies().asScala.distinct.asJava
    fontFamilyChooser.setItems(FXCollections.observableArrayList(fontNames))
    fontFamilyChooser.setValue(Font.font("Arial").getFamily)

    val sizesList = (8 until 72 by 2).asJava
    fontSizeChooser.setItems(FXCollections.observableArrayList(sizesList))
    fontSizeChooser.setValue(12)
  }
}
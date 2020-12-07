import java.io.{ File, FileWriter, PrintWriter }

import org.json4s.Xml.toJson
import org.json4s._
import org.json4s.native.JsonMethods.{ compact, render, _ }

import scala.Console.println
import scala.io.Source
import scala.xml.NodeSeq
import scala.xml.XML.{ loadFile, save }

object UsingAirBaseLVXML_1 extends App {
  val p = <p>Air base test</p>
  println(p)

  val airBase = loadFile("./src/resources/AirBaseLV.xml")

  val allStationLV = airBase \ "country" \ "network" \ "station"

  save("c:/airBase/airBaseLv_All_station.xml", <root>
    {allStationLV}
  </root>, xmlDecl = true)

  val groupAllStation = allStationLV.map(i => (i \\ "station"))
  val withFilter = allStationLV.filter {_ \\ "station" exists (_.text == "Id")}

  val stationRucava = allStationLV.filter {_ \\ "@Id" exists (_.text == "LV0010R:Rucava")}
  val stationRucavaAllMeasurement = stationRucava \\ "measurement_configuration"
  val stationRucavaAllMeasurement_info = stationRucava \\ "measurement_info"
  //println(stationRucavaAllMeasurement_info)

  val stationZoseni = allStationLV.filter {_ \\ "@Id" exists (_.text == "LV0016R:Zoseni")}
  val stationZoseniAllMeasurement = stationZoseni \\ "measurement_configuration"

  val stationDaugavpils = allStationLV.filter {_ \\ "@Id" exists (_.text == "LV0006A:Daugavpils")}
  val stationDaugavpilsAllMeasurement = stationZoseni \\ "measurement_configuration"

  val stationJurmala = allStationLV.filter {_ \\ "@Id" exists (_.text == "LV0011A:Jurmala")}
  val stationJurmalaAllMeasurement = stationJurmala\\ "measurement_configuration"

  val stationLiepaja = allStationLV.filter {_ \\ "@Id" exists (_.text == "LV0012A:Liepaja")}
  val stationLiepajaAllMeasurement = stationLiepaja \\ "measurement_configuration"

  val stationNigrande = allStationLV.filter {_ \\ "@Id" exists (_.text == "LV0013A:Nigrande")}
  val stationNigrandeAllMeasurement = stationNigrande \\ "measurement_configuration"

  val stationOlaine = allStationLV.filter {_ \\ "@Id" exists (_.text == "LV0009A:Olaine")}
  val stationOlaineAllMeasurement = stationOlaine \\ "measurement_configuration"

  val stationRezekne = allStationLV.filter {_ \\ "@Id" exists (_.text == "LV0008A:Rezekne")}
  val stationRezekneAllMeasurement = stationRezekne \\ "measurement_configuration"

  val stationRigaCentrs = allStationLV.filter {_ \\ "@Id" exists (_.text == "LV0010A:Riga Centrs")}
  val stationRigaCentrsAllMeasurement = stationRigaCentrs \\ "measurement_configuration"

  val stationRigaImanta = allStationLV.filter {_ \\ "@Id" exists (_.text == "LV0004A:Riga Imanta")}
  val stationRigaImantaAllMeasurement = stationRigaImanta \\ "measurement_configuration"

  val stationRigaKengarags = allStationLV.filter {_ \\ "@Id" exists (_.text == "LV0003A:Riga Kengarags-2")}
  val stationRigaKengaragsAllMeasurement = stationRigaKengarags \\ "measurement_configuration"

  val stationRigaMilgravis = allStationLV.filter {_ \\ "@Id" exists (_.text == "LV0017A:Riga Milgravis")}
  val stationRigaMilgravisAllMeasurement = stationRigaMilgravis \\ "measurement_configuration"

  val stationRigaPark = allStationLV.filter {_ \\ "@Id" exists (_.text == "LV0019A:Riga Park")}
  val stationRigaParkAllMeasurement = stationRigaPark \\ "measurement_configuration"

  val stationRigaBrivibas = allStationLV.filter {_ \\ "@Id" exists (_.text == "LV0018A:Riga-Brivibas street")}
  val stationRigaBrivibasAllMeasurement = stationRigaBrivibas \\ "measurement_configuration"

  val stationRigaValdemara = allStationLV.filter {_ \\ "@Id" exists (_.text == "LV0020A:Riga-Valdemara street")}
  val stationRigaValdemaraAllMeasurement = stationRigaValdemara \\ "measurement_configuration"

  val stationValmiera = allStationLV.filter {_ \\ "@Id" exists (_.text == "LV0005A:Valmiera")}
  val stationValmieraAllMeasurement = stationValmiera \\ "measurement_configuration"

  val stationVentspils = allStationLV.filter {_ \\ "@Id" exists (_.text == "LV0007A:Ventspils")}
  val stationVentspilsAllMeasurement = stationVentspils \\ "measurement_configuration"

  val stationVentspilsPort = allStationLV.filter {_ \\ "@Id" exists (_.text == "LV0021A:Ventspils Port")}
  val stationVentspilsPortAllMeasurement = stationVentspilsPort \\ "measurement_configuration"

  //Stations
  val pw = new PrintWriter(new File("./src/resources/stations_latvia_meta/Rucava_LV0010R.json"))
  val pw1 = new PrintWriter(new File("./src/resources/stations_latvia_meta/Zoseni_LV0016R.json"))
  val pw2 = new PrintWriter(new File("./src/resources/stations_latvia_meta/Daugavpils_LV0006A.json"))
  val pw3 = new PrintWriter(new File("./src/resources/stations_latvia_meta/Jurmala_LV0011A.json"))
  val pw4 = new PrintWriter(new File("./src/resources/stations_latvia_meta/Liepaja_LV0012A.json"))
  val pw5 = new PrintWriter(new File("./src/resources/stations_latvia_meta/Nigrande_LV0013A.json"))
  val pw6 = new PrintWriter(new File("./src/resources/stations_latvia_meta/Olaine_LV0009A.json"))
  val pw7 = new PrintWriter(new File("./src/resources/stations_latvia_meta/Rezekne_LV0008A.json"))
  val pw8 = new PrintWriter(new File("./src/resources/stations_latvia_meta/Riga_Centrs_LV0010A.json"))
  val pw9 = new PrintWriter(new File("./src/resources/stations_latvia_meta/Riga_Imanta_LV0004A.json"))
  val pw10 = new PrintWriter(new File("./src/resources/stations_latvia_meta/Riga_Kengarags_LV0003A.json"))
  val pw11 = new PrintWriter(new File("./src/resources/stations_latvia_meta/Riga_Milgravis_LV0017A.json"))
  val pw12 = new PrintWriter(new File("./src/resources/stations_latvia_meta/Riga_Park_LV0019A.json"))
  val pw13 = new PrintWriter(new File("./src/resources/stations_latvia_meta/Riga_Brivibas_street_LV0018A.json"))
  val pw14 = new PrintWriter(new File("./src/resources/stations_latvia_meta/Riga_Valdemara_street_LV0020A.json"))
  val pw15 = new PrintWriter(new File("./src/resources/stations_latvia_meta/Valmiera_LV0005A.json"))
  val pw16 = new PrintWriter(new File("./src/resources/stations_latvia_meta/Ventspils_LV0007A.json"))
  val pw17 = new PrintWriter(new File("./src/resources/stations_latvia_meta/Ventspils_Port_LV0021A.json"))



  val stRucavaAllMeasurementJson = toJson(stationRucavaAllMeasurement)
  println(stRucavaAllMeasurementJson)
  println(stRucavaAllMeasurementJson.values)

  val stZoseniAllMeasurementJson = toJson(stationZoseniAllMeasurement)
  pw1.write(pretty(render(stZoseniAllMeasurementJson)))
  pw1.close()

  val stDaugavpilsiAllMeasurementJson = toJson(stationDaugavpilsAllMeasurement)
  pw2.write(pretty(render(stDaugavpilsiAllMeasurementJson)))
  pw2.close()

  val stJurmalaAllMeasurementJson = toJson(stationJurmalaAllMeasurement)
  pw3.write(pretty(render(stJurmalaAllMeasurementJson)))
  pw3.close()

  val stLiepajaAllMeasurementJson = toJson(stationLiepajaAllMeasurement)
  pw4.write(pretty(render(stLiepajaAllMeasurementJson)))
  pw4.close()

  val stNigrandeAllMeasurementJson = toJson(stationNigrandeAllMeasurement)
  pw5.write(pretty(render(stNigrandeAllMeasurementJson)))
  pw5.close()

  val stOlaineAllMeasurementJson = toJson(stationOlaineAllMeasurement)
  pw6.write(pretty(render(stOlaineAllMeasurementJson)))
  pw6.close()

  val stRezekneAllMeasurementJson = toJson(stationRezekneAllMeasurement)
  pw7.write(pretty(render(stRezekneAllMeasurementJson)))
  pw7.close()

  val stRigaCentrsAllMeasurementJson = toJson(stationRigaCentrsAllMeasurement)
  pw8.write(pretty(render(stRigaCentrsAllMeasurementJson)))
  pw8.close()

  val stRigaImantaAllMeasurementJson = toJson(stationRigaImantaAllMeasurement)
  pw9.write(pretty(render(stRigaImantaAllMeasurementJson)))
  pw9.close()

  val stRigaKengaragsAllMeasurementJson = toJson(stationRigaKengaragsAllMeasurement)
  pw10.write(pretty(render(stRigaKengaragsAllMeasurementJson)))
  pw10.close()

  val stRigaMilgravisAllMeasurementJson = toJson(stationRigaMilgravisAllMeasurement)
  pw11.write(pretty(render(stRigaMilgravisAllMeasurementJson)))
  pw11.close()

  val stRigaParkAllMeasurementJson = toJson(stationRigaParkAllMeasurement)
  pw12.write(pretty(render(stRigaParkAllMeasurementJson)))
  pw12.close()

  val stRigaBrivibasAllMeasurementJson = toJson(stationRigaBrivibasAllMeasurement)
  pw13.write(pretty(render(stRigaBrivibasAllMeasurementJson)))
  pw13.close()

  val stRigaValdemaraAllMeasurementJson = toJson(stationRigaValdemaraAllMeasurement)
  pw14.write(pretty(render(stRigaValdemaraAllMeasurementJson)))
  pw14.close()

  val stValmieraAllMeasurementJson = toJson(stationValmieraAllMeasurement)
  pw15.write(pretty(render(stValmieraAllMeasurementJson)))
  pw15.close()

  val stVentspilsAllMeasurementJson = toJson(stationVentspilsAllMeasurement)
  pw16.write(pretty(render(stVentspilsAllMeasurementJson)))
  pw16.close()

  val stVentspilsPortAllMeasurementJson = toJson(stationVentspilsPortAllMeasurement)
  pw17.write(pretty(render(stVentspilsPortAllMeasurementJson)))
  pw17.close()



  //val grouping = stRucavaAllMeasurementJson.values.toString.toList.grouped(2).map { case List(k, v) => k -> v }.toMap
  //println(grouping) //doesn't work
  println(compact(render(stRucavaAllMeasurementJson)))
  val renderStRucava = compact(render(stRucavaAllMeasurementJson))
  pw.write(pretty(render(stRucavaAllMeasurementJson)))
  pw.close()



  val splitRenderStRucava = renderStRucava.split("}{5},")
  println("*********************************************")

  implicit val formats = DefaultFormats
  for (line <- splitRenderStRucava) {
    println(line)
  }


  val fileContents = Source.fromFile("./src/resources/stations_latvia_meta/Rucava_LV0010R.json").getLines.mkString //so this leaves file open
  //println(fileContents)
  val todoJSON = Source.fromFile("./src/resources/stations_latvia_meta/Rucava_LV0010R.json").getLines.mkString
  //val rawData = read[Seq[ujson.Value]](todoJSON)
  //println(rawData)//expected sequence got dictionary at index 0




  val AllMeasurementInfoJson = toJson(stationRucavaAllMeasurement_info)
  //println(AllMeasurementInfoJson )

  val MeasurementInfo = compact(render(AllMeasurementInfoJson )).split("}{1},")
 // for (i <- Measurement_info)
    //println(i)


  val Statistic_result = {stationRucava \\ "statistics"}
  //println(Statistic_result)

  val destName = "./src/resources/JsonAirRucava.json"

  def saveSeq(destName: String, mySeq: Seq[Any]): Unit = {
    println(s"saving Json Rucava $destName")
    mySeq.foreach(println)
    val fw = new FileWriter(destName)
    mySeq.map(_.toString).foreach(fw.write)
    fw.close()
  }
  //saveSeq(destName, stationRucavaAllMeasurement) //doesn't work correctly

  val uuu = pretty(render(stRucavaAllMeasurementJson))
//  val rawSeq = read[Seq[ujson.Value]](stationRucavaAllMeasurement.toString) //expected json value got < (line 1, column 1) at index 0
//  rawSeq.foreach(println)

//  implicit val formats = DefaultFormats
//  case class PersonWithAddresses(component_name: Any, measurement_unit: Any)
//  println(stRucavaAllMeasurementJson.extract[PersonWithAddresses])
// val jjjj = stRucavaAllMeasurementJson findField {
//    case JField("component_name", _) => true
//    case JField("component_caption", _) => true
//    case JField("measurement_unit", _) => true
//    case JField("measurement_unit", _) => true
//    case _ => false
//  }



  //npm i metadata-json
  //val mdjson = require("metadata-json")

  //  mdjson.loadFromFile("test.mdj")
  //  mdjson.render("template.ejs", "out.html", mdjson.getRoot)
  val statistics = stRucavaAllMeasurementJson \\ "statistics"


//  val extrRucava = renderStRucava.extract[Airbase]
//  println(extrRucava)

  //println(pretty(render(statis))) //without component
// def comAll (string: String): = {
//    new Airbase {
//      override val component_name =
//      override val component_caption: String = _
//      override val measurement_unit: String = _
//      override val measurement_technique_principle: String = _
//    }
//  }


  def componentInfo(el: NodeSeq): Unit = {
    val comName = (el \ "measurement_info" \ "component_name").text
    val caption = (el \"measurement_info" \ "component_caption").text
    val unit = (el \"measurement_info"\ "measurement_unit").text
    val principle = (el \"measurement_info" \ "measurement_technique_principle").text
    val year = (el \\"@Year").toList
    val stName = (el \"statistics" \ "statistics_average_group" \ "statistic_set"\ "statistic_result" \ "statistic_name").toList.map(_.text)
    val stShortname = (el \"statistics" \ "statistics_average_group" \ "statistic_set"\ "statistic_result" \ "statistic_shortname").toList.map(_.text)
    val stValue = (el \"statistics" \ "statistics_average_group" \ "statistic_set"\ "statistic_result" \ "statistic_value").toList.map(_.text) //todo check if will need only one result with P50

    def toString2 = {
      s"\nComponent name: $comName\nComponent caption: $caption\nMeasurement unit: $unit\n" +
        s"Measurement technique principle: $principle\nYear: $year\nStatistic name: $stName\nStatistic shortname: $stShortname\n"+
        s"Statistic value: $stValue"
    }

    println(toString2.toSeq)
  }

  val ArsenicMeasurementRucava: Unit = componentInfo(stationRucavaAllMeasurement(0))
  println(ArsenicMeasurementRucava)
  val BenzeneMeasurement: Unit = componentInfo(stationRucavaAllMeasurement(1))

}

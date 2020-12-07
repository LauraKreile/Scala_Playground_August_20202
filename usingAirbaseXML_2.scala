import java.io.{File, PrintWriter}

import scala.Console.println
import scala.language.postfixOps
import scala.xml.NodeSeq
import scala.xml.XML.{loadFile, save}

object usingAirbaseXML_2 extends App {
  val p = <p>Air base test</p>
  println(p)

  val airBase = loadFile("./src/resources/AirBaseLV.xml")
  //println(airBase)

  //  val pw = new PrintWriter(new File("mydata.json" ))
  //  pw.write(write(myThings,indent = 2))

  //val pw = new PrintWriter(new File("./src/resources/airBaseLv_Result.txt"))
  //val pw = new PrintWriter(new File("./src/resources/airBaseLv_Result.tsv"))
  val pw = new PrintWriter(new File("c:/airBase/airBaseLv_Result.txt"))
  //val pw = new PrintWriter(new File("./src/resources/airBaseLv_Result.xml"))  //in there were reported errors (Unexpected tokens,Top level element is not completed,Valid XML document must have a root tag)
  //pw.write("<?xml version='1.0' encoding='ISO-8859-1'?>")
  //println("\n\nMy results about the country Latvian air base")
  pw.write("My results of Latvian air bases with air benzene measurements.")

  println("*******************************************************")

  val allStationLV = airBase \ "country" \ "network" \ "station" \ "station_info" \ "station_name"
  List(println(allStationLV.map(_.text)))
  for (i <- allStationLV) {
    //println(i)
  }
  println("*******************************************************")

  pw.write("\n\n1. Airbase stations in Latvia:")
  println("Airbase stations in Latvia:")
  //pw.write("\n")
  for (i <- allStationLV.indices) {
    pw.write(s"\n   Station Nr.: ${i + 1} and station name: ${allStationLV(i).text}")
    println(s"    Station Nr.: ${i + 1} and station name: ${allStationLV(i).text}")
  }
  println("*******************************************************")

  val allComponentNameLV = airBase \ "country" \ "network" \ "station" \ "measurement_configuration" \ "measurement_info" \ "component_name"
  //println(allComponentNameLV.text) //there are all component names
  val groupByStation = allComponentNameLV.groupBy(_.attribute("station"))
  //println(groupByStation) //doesn't work
  //println(for (i <- allComponentNameLV) yield i \\ "component_name")
  //println("*******************************************************")
  for (i <- allComponentNameLV) {
    //println(i.text)
  }

  val allComponent = airBase \\ "@component"
  //println(allComponent)

  for (i <- allComponent) {
    //println(i)
  }

  //println("*******************************************************")

  val componentName = airBase \\ "component_name"
  // println((componentNameBenzene.text.filter{(_. contains ("Benzene (air)")}))//doesn't work
  //println(componentName.text)
  //println("*******************************************************")

  val componentNameBenzene = componentName.filter {
    _ \\ "component_name" exists (_.text == "Benzene (air)")
  }.map(i => {
    airBase \\ "station_name"
  }.text)
  //println(componentNameBenzene) //doesn't work
  for (i <- componentNameBenzene) {
    //println(i) //doesn't work
  }
  //println("*******************************************************")
  println(allComponentNameLV.filter(s => (s \ "component_name").exists(t => t.text == "Benzene (air)")).sortBy(_ => (airBase \\ "station_name").text))
  //doesn't work
  //map(n => (n \ "@station").text))//doesn't work
  //filter { _ \\ "component_name" exists (_.text == "Benzene (air)")})
  //println(allComponentNameLV.filter{_ \\ "station" exists (_.text == "Station_name" )})
  println("*******************************************************")
  //val stationComponentName = for (station <- allComponentNameLV) yield station.text
  //println(stationComponentName) //the same
  //println("*******************************************************")
  val onlyComponentBenzene = allComponentNameLV.filter {
    _ \\ "component_name" exists (_.text == "Benzene (air)")
  } //.map(i => {airBase \\ "station_name"}.text)
  //println(onlyComponentBenzene.text)
  println("There are only four airbase stations with component Benzene air: ")
  for (i <- onlyComponentBenzene) {
    println(i.text)
  }
  println("*******************************************************")
  allComponentNameLV.filter {
    _ \\ "station" exists (_.text == onlyComponentBenzene)
  }.foreach(println) //doesn't work
  //  List(println(allComponentNameLV.text,println(allStationLV.foreach(_ => allComponentNameLV))))
  //val ppp = allStationLV.text.foreach(println(allComponentNameLV.map(_ => allComponentNameLV.text))

  //println("*******************************************************")
  val airBenzeneMeasurement_info = airBase \\ "measurement_info" filter {
    _ \\ "component_name" exists (_.text == "Benzene (air)")
  }
  //println(airBenzeneMeasurement_info.text) //there are measurement info with Benzene air
  //println("*******************************************************")

  val stationWithBenzene = airBase \\ "station"
  val finallyStationBenzene = stationWithBenzene.filter {
    _ \\ "component_name" exists (_.text equals "Benzene (air)")
  }.map(i => (i \\ "@Id")) //the station which have benzene air
  println("These are stations which have benzene air measurements.")
  println(finallyStationBenzene)
  println("*******************************************************")
  println(s"There are 4 stations which have benzene air measurements:")
  pw.write(s"\n\n2. There are 4 stations which have benzene air measurements:")
  for (i <- finallyStationBenzene.indices) {
    println(s"  ${i + 1}. station Id: ${finallyStationBenzene(i).text}")
    pw.write(s"\n   ${i + 1}. station Id: ${finallyStationBenzene(i).text}")
  }

  println("*******************************************************")

  val airBenzeneMeasurement_configuration = airBase \\ "measurement_configuration" filter {
    _ \\ "component_name" exists (_.text == "Benzene (air)")
  }
  //println(airBenzeneMeasurement_configuration(0).text) //the first data with benzene air (Rucava)
  //val pp = for (i <- airBenzeneMeasurement_configuration) yield i.text
  //println(pp) //This not works

  //println(airBenzeneMeasurement_configuration(2)) //the third station with benzene air (Riga)
  //println("*******************************************************")
  val airBenzeneStatistic_result = for (i <- airBenzeneMeasurement_configuration) yield i \\ "statistic_result"
  //airBenzeneStatistic_result.foreach(println) //there are all statistic results with benzene air


  val airBenzeneMeasurement_info1 = for (i <- airBenzeneMeasurement_configuration) yield i \\ "measurement_info"
  //airBenzeneMeasurement_info1.foreach(println) //measurement info with benzene air

  //println("*******************************************************")
  val stationWithBenzene2 = for (j <- finallyStationBenzene) yield j.text
  //stationWithBenzene2.foreach(println) //station with benzene air
  println("*******************************************************")


  //def getStringLength(text:String):Int = {
  def measurementInfo(el: NodeSeq): Unit = {
    val name = (el \ "component_name").text
    val caption = (el \ "component_caption").text
    val code = (el \ "component_code").text.toInt
    val unit = (el \ "measurement_unit").text
    val startDate = (el \ "measurement_start_date").text
    val latestDate = (el \ "measurement_latest_date_available_in_AIRBASE").text
    val europeanCode = (el \ "measurement_european_code").text
    val technique = (el \ "measurement_technique_principle").text
    val equipment = (el \ "measurement_equipment").text
    val calibration = (el \ "calibration_unit").text
    val point = (el \ "height_sampling_point").text.toInt
    val line = (el \ "length_sampling_line").text.toInt
    val samplingPoint = (el \ "location_sampling_point").text
    val time = (el \ "sampling_time").text.toInt
    val frequency = (el \ "calibration_frequency").text.toInt
    val method = (el \ "calibration_method").text

    def toString = {
      s"  Component_name: $name\n     Component caption: $caption\n     Component code: $code\n" +
        s"     Measurement unit: $unit\n     Measurement start date: $startDate\n     Measurement latest date: $latestDate\n" +
        s"     Measurement european code: $europeanCode\n     Measurement technique principle: $technique\n" +
        s"     Measurement equipment: $equipment\n     Calibration unit: $calibration\n     Height sampling point: $point\n" +
        s"     Length sampling line: $line\n     Location sampling point: $samplingPoint\n     Sampling time: $time\n" +
        s"     Calibration frequency: $frequency\n     Calibration method: $method\n"
    }

    println(toString)
    pw.write(s"\n   $toString")
  }

  println("Stations with benzene (air) component measurements information.")
  pw.write("\n\n3. Stations with benzene (air) component measurements information.")
  //println(finallyStationBenzene.map(_.text))
  //for (i <- finallyStationBenzene) yield print(i.map(_.text))
  //pw.write(s"\n     ${finallyStationBenzene.toString()}")
  println("Station Id: LV0010R:Rucava")
  pw.write("\n   Station Id: LV0010R:Rucava")
  val measurements = measurementInfo(airBenzeneMeasurement_info1(0)).toString
  println("Station Id: LV0016R:Zoseni")
  pw.write("\n   Station Id: LV0016R:Zoseni")
  val measurements2 = measurementInfo(airBenzeneMeasurement_info1(1)).toString
  println("Station Id: LV0020A:Riga-Valdemara street")
  pw.write("\n   Station Id: LV0020A:Riga-Valdemara street")
  val measurements3 = measurementInfo(airBenzeneMeasurement_info1(2)).toString
  println("Station Id: LV0021A:Ventspils Port")
  pw.write("\n   Station Id: LV0021A:Ventspils Port")
  val measurements4 = measurementInfo(airBenzeneMeasurement_info1(3)).toString



  val Years = for (i <- airBenzeneMeasurement_configuration) yield i \\ "@Year"
  //println(Years)//list with years

  println("*******************************************************")
  //println("Years in which were done measurements.")
  val Years2 = airBenzeneMeasurement_configuration \\ "@Year"
  //Years2.foreach(println) //there are all years with statistic

  val stationNameWithBenzeneAir = stationWithBenzene.filter {
    _ \\ "component_name" exists (_.text equals "Benzene (air)")
  }
  val stationNameWithBenzene = stationNameWithBenzeneAir \\ "station_name"
  //stationNameWithBenzeneAir.foreach(println)

  println("*******************************************************")
  println("Stations with the respective years in which were done the measurement of benzene air.")
  //pw.write("\n\n4. Stations with the respective years in which were done the measurement of benzene air.")
  for ((x, y) <- stationNameWithBenzene.zip(Years)) {
    println(s"    Station name:${x.text} and statistics Years: $y")
    //pw.write(s"\n   Station name:${x.text} and statistics Years: $y")
  }

  println("*******************************************************")
  val Years3 = for (i <- airBenzeneMeasurement_configuration) yield i \\ "statistics"
  //Years3.foreach(println) //this is with year,but in station Riga the years are together

  //println("*******************************************************")
  val Years4 = for (i <- airBenzeneMeasurement_configuration) yield i \\ "@Year"
  //for ((x, y) <- Years.zip(airBenzeneStatistic_result))
  //println(x, y) // there are statistic results with years
  //println(Years4(2))//there are only all years or station Riga

  val stationRiga_ValdemaraStreet = airBenzeneMeasurement_configuration(2)
  //println(stationRiga_ValdemaraStreet) //this is only Riga station
  //println("*******************************************************")
  val stationRiga_Years = (stationRiga_ValdemaraStreet).filter(s => (s \\ "statistics").exists(t => t.text == "Year"))
  //println(stationRiga_Years) //there are only all years in station Riga (2004,2005,2006)//doesn't work

  println("*******************************************************")
  val airBenzeneMeasurement_configuration2 = airBase \\ "measurement_configuration" filter {
    _ \\ "component_name" exists (_.text == "Benzene (air)")
  }
  val mappingStationWithYears =
    (airBenzeneMeasurement_configuration2 \\ "statistics").
      //}.filter { _ \\ "station_info" exists(_.text == "station_name")}
      map(_.attribute("Year"))
  println("The year in which was done the statistic results.")
  mappingStationWithYears.foreach(println)
  //println(airBenzeneMeasurement_configuration2)
  //  (hhh,stationNameWithBenzene). zipped foreach { (v1, v2) =>
  //    println(s" $v1, $v2")}
  println("*******************************************************")
  val station = List("Rucava", "Zoseni", "Riga-Valdemara street", "Riga-Valdemara street", "Riga-Valdemara street", "Ventspils Port")
  //println(station)
  println("Station with year in which was done the statistic results.")
  pw.write("\n\n4. Station with year in which was done the statistic results.")
  for ((x, y) <- station.zip(mappingStationWithYears)) {
    println(s"    Station name:${x} and statistics Year: $y")
    pw.write(s"\n     Station name:${x} and statistics Year: $y")
  }
  println("*******************************************************")
  println("*******************************************************")
  val yearsWithBenzeneAir = airBenzeneMeasurement_configuration \\ "statistics"
  //yearsWithBenzeneAir.foreach(println) //this is good, final
  save("c:/airBase/airBaseLv_Result3.xml", <kook>{yearsWithBenzeneAir}</kook>,xmlDecl = true)

  // println(finallyStationBenzene.map(_.text))
  def statisticInfo(el: NodeSeq): Unit = {
    val year = (el \ "@Year").text
    val valid = (el \"statistics_average_group" \ "statistic_set"\"statistics_percentage_valid").toList.map(_.text)
    val numberValid = (el \"statistics_average_group" \ "statistic_set" \ "statistics_number_valid").toList.map(_.text.toDouble)
    val stName = (el \"statistics_average_group" \ "statistic_set" \ "statistic_result" \ "statistic_name").toList.map(_.text)
    val stShortname = (el \"statistics_average_group" \"statistic_set" \ "statistic_result" \ "statistic_shortname").toList.map(_.text)
    val value = (el \"statistics_average_group" \"statistic_set" \ "statistic_result" \ "statistic_value").toList.map(_.text)

    def toString2 = {
      s"Year: $year\n   Statistics percentage valid: $valid\n   Statistics number valid: $numberValid\n " +
        s"  Statistic name: $stName\n   Statistic shortname: $stShortname\n   Statistic value: $value\n"
    }

    println(toString2)
    pw.write(s"\n   $toString2")
  }
  println("Statistic results of benzene air in each year.")
  pw.write("\n\n\n5. Statistic results of benzene air in each year.")
  println("Station Id: LV0010R:Rucava")
  pw.write("\n   Station Id: LV0010R:Rucava")
  val statisticRucava = statisticInfo(yearsWithBenzeneAir(0))
  println("Station Id: LV0016R:Zoseni")
  pw.write("\n   Station Id: LV0016R:Zoseni")
  val statisticZoseni = statisticInfo(yearsWithBenzeneAir(1))
  println("Station Id: LV0020A:Riga-Valdemara street")
  pw.write("\n   Station Id: LV0020A:Riga-Valdemara street")
  val statisticRiga = statisticInfo(yearsWithBenzeneAir(2))
  println("Station Id: LV0020A:Riga-Valdemara street")
  pw.write("\n   Station Id: LV0020A:Riga-Valdemara street")
  val statisticRiga1 = statisticInfo(yearsWithBenzeneAir(3))
  println("Station Id: LV0020A:Riga-Valdemara street")
  pw.write("\n   Station Id: LV0020A:Riga-Valdemara street")
  val statisticRiga2 = statisticInfo(yearsWithBenzeneAir(4))
  println("Station Id: LV0021A:Ventspils Port")
  pw.write("\n   Station Id: LV0021A:Ventspils Port")
  val statisticVentspils = statisticInfo(yearsWithBenzeneAir(5))
  println("*******************************************************")
  println("Stations with benzene (air) component measurement information (with text content):")
  pw.write("\n\n6. Stations with benzene (air) component measurement information (with text content):")
  for ((x, y) <- stationWithBenzene2.zip(airBenzeneMeasurement_info1)) {
    println(s"  Station ID and station name: $x\n  Measurement info of component Benzene (air): $y")
    pw.write(s"\n     Station ID and station name: $x\n     Measurement info of component Benzene (air): ${y.text}")
  }
  println("*******************************************************")
  println("Station with statistic results of benzene air in each year.")
  pw.write("\n\n7. Station with statistic results of benzene air in each year.")
  (station, yearsWithBenzeneAir).zipped foreach { (v1, v2) =>
    println(s"Station name: $v1\nStatistics Year: $v2, statistic with Benzene")
    pw.write(s"\n\n     Station name: $v1\n     Statistics Year: $v2, statistic with Benzene")
  }

  println("*******************************************************")
  println("*******************************************************")



  println("Stations with statistic results of benzene air measurements.")
  (stationWithBenzene2, Years, airBenzeneStatistic_result).zipped foreach { (v1, v2, v3) =>
    println(s"Station Id and station name: $v1\nStatistic Years: $v2\nStatistic result with Benzene: $v3")
    pw.close
  }
  val allStationLV2 = airBase \ "country" \ "network" \ "station"
  //println(allStationLV2)
   val ooo = for (i <- allStationLV2) yield i \\ "station_info"
//println(ooo)

  def stationInfo(el: NodeSeq): Unit = {
    val code = (el \ "station_european_code").text
    val localCode = (el \ "station_local_code").text
    val name = (el \ "station_name").text
    val descp = (el \ "station_description").text
//    val startDate = (el \ "measurement_start_date").text
//    val latestDate = (el \ "measurement_latest_date_available_in_AIRBASE").text
//    val europeanCode = (el \ "measurement_european_code").text
//    val technique = (el \ "measurement_technique_principle").text
//    val equipment = (el \ "measurement_equipment").text
//    val calibration = (el \ "calibration_unit").text
//    val point = (el \ "height_sampling_point").text.toInt
//    val line = (el \ "length_sampling_line").text.toInt
//    val samplingPoint = (el \ "location_sampling_point").text
//    val time = (el \ "sampling_time").text.toInt
//    val frequency = (el \ "calibration_frequency").text.toInt
//    val method = (el \ "calibration_method").text

    def toString = {
      s"  Component_name: $code\n     Component caption: $localCode\n     Component code: $name\n" +
        s"     Measurement unit: $descp\n "
    }

    println(toString)
    //pw.write(s"\n   $toString")
  }
  val pw2 = new PrintWriter(new File("c:/airBase/airBaseLv9999t.xml"))
pw2.write(statisticInfo(ooo(1)).toString)
println(statisticInfo(ooo(1)))
  //pw2.close()
  //val json = formattedooo.toJSON
//val yearsWithBenzeneAir2 = for (i <- airBenzeneMeasurement_configuration) yield i \\ "statistics"
//println(yearsWithBenzeneAir2)
  //airBenzeneMeasurement_info1 = for (i <- airBenzeneMeasurement_configuration) yield i \\ "measurement_info"
  //statisticInfo(yearsWithBenzeneAir)
  //println(measurements)
  //val pp= measurements.split(",").map(_ => toString)
  //pp.foreach(println)//.foreach(println)
  //(measurementInfo(airBenzeneMeasurement_info1(0))) println(i)
  //println(s"LV0010R:Rucava:\n${measurementInfo(airBenzeneMeasurement_info1(1))}")


//Doesn't work
//  println(s"LV0010R:Rucava:")
//  println(s"LV0016R:Zoseni: $measurements")
//  println(s"LV0020A:Riga-Valdemara street: $measurements2")
//  println(s"LV0021A:Ventspils Port: $measurements3")
//
//  pw.write(s"\n   LV0010R:Rucava: $measurements")
//  pw.write(s"\n   LV0016R:Zoseni: $measurements1")
//  pw.write(s"\n   LV0020A:Riga-Valdemara street: $measurements2")
//  pw.write(s"\n   LV0021A:Ventspils Port: $measurements3")
//  println(finallyStationBenzene)
//  //measurements.foreach(el => println(el))


}

  //println(measurements.mkString("\n"))
  //measurements.split(",").foreach(println)
//measurements.foreach(println)

  //for (i <- (measurements) )
//
  //for ((x, y) <- station.zip(mappingStationWithYears))








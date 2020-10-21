import java.io.File

object File_info_lu extends App {
 // println(System.getProperty("user.dir"))

  def getListOfFiles(dir: String): List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(_.isFile).toList
    } else {
      List[File]()
    }
  }

  def getLineCount(fileName: String): Int = { //to get total amount with lines
    var count = 0
    val bufferedSource = io.Source.fromFile(fileName)
    for (line <- bufferedSource.getLines) {
      count += 1
    }
    bufferedSource.close
    count
  }

  def getRowSplitSize(fileName: String): Int = { //to get total amount with rows
    var count = 0
    val bufferedSource = io.Source.fromFile(fileName)
    for (line <- bufferedSource.getLines) {
      val splitLine = line.split(",")
      count += splitLine.size
    }
    bufferedSource.close
    count
  }

  def getParsedLines(fileName: String) = { //to make elements to List
    var myListBuf = scala.collection.mutable.ListBuffer[Seq[String]]()
    val bufferedSource = io.Source.fromFile(fileName)
    for (line <- bufferedSource.getLines) {
      val splitLine = line.split(",")

      myListBuf += splitLine
    }
    bufferedSource.close
    myListBuf.toSeq //return how many lines are there
  }

  def getDataSeq(splitLineSeq: Seq[Seq[String]]): Seq[for_luminor] = {
    splitLineSeq.map(t => for_luminor(t(0).toInt, t(1).toInt, t(2), t(3), t(4), t(5), t(6), t(7), t(8), t(9),
      t(10).toInt, t(11).toInt, t(12).toInt, t(13), t(14)))
  }

  val filePath = ".\\src\\resources\\Data_Luminor"

  val lineCount = getLineCount(filePath)
  println(s"we got a file with $lineCount")
  val rowSplitCount = getRowSplitSize(filePath)
  println(s"We got a file with $rowSplitCount rows")
  val rawSplit = getParsedLines(filePath)
  println(rawSplit.size)
  println(rawSplit(0))
  println(rawSplit(4))
  val filteredResults = rawSplit.filter(line => line.size == 15)
  println(filteredResults.size)


  //val fw = new FileWriter(destName, true)
  import java.io._
  val pw = new PrintWriter(new File( "c:\\poem\\myLuminorResults2.txt"))
  pw.write("My results are:\n\n")



  val ourData = getDataSeq(filteredResults.slice(1, filteredResults.size))
  println(ourData(0))
  println(ourData(1).BirthDate) //to check, it is correct
  println(ourData(4269))
  val customerGenderSize = ourData.count(_.CustomerGender.length >= 2) //is correct
  val customerGenderSize2 = ourData.filter(_.CustomerGender.length >= 2) //is correct
  println(customerGenderSize)
  val nd2 = ourData.count(_.CustomerGender.contains("N/D")) //is correct

 pw.write(s"1. There are $nd2 persons of $lineCount total in data without marked gender, it is marked N/D.\n\n")
  val birthDate = ourData.count(_.BirthDate.contains("1900"))
  val kkk = ourData.filter(_.BirthDate.contains("1900")).filter(_.IsCustomerActive_hasValidAgreement == "Y").size
  println(kkk)

 val yyy = ourData.count(_.IsCustomerALegalPerson == "N")
val NoCode = ourData.filter(_.IsResident == "N").filter(_.ResidenceCountryCode == "LV").filter(_.IsCustomerALegalPerson == "N")
val noCode3 = ourData.filter(_.IsResident == "N").filter(_.ResidenceCountryCode == "LV").filter(_.IsCustomerALegalPerson == "N").size
  println(noCode3)
  ourData.filter(_.IsCustomerALegalPerson == "N").filter(_.NACECode.length >= 2).foreach(println)
  pw.write(s"2. There are $birthDate persons of $lineCount total in data who are 120 years old and are marked as active customers.\n\n")
  pw.write(s"3. There are $noCode3 persons of $lineCount total in data who are not residents and the residence country codes are LV, but all are marked Nacecode.\n")
  pw.write(s"   The list of customers:\n")
  //foreach(pw.write(ooo2)
  // pw.write(ooo2.toList)
  val rrrpPPP=  NoCode.map(_ + "\n").foreach(pw.write)

  //pw.write(s"   Except one person: \n     : ${ourData.filter(_.CustomerID_countryLevel == 2529930)}" +
    //s"\n   This customer is active, but he isn't resident. The residence country is LV, but he is from country EE.\n\n")

val ooo = ourData.count(_.ResidenceCountryCode.contains("N/"))
val ooo2 = ourData.filter(_.ResidenceCountryCode.contains("N/"))
  //ooo2.foreach(println)
  pw.write("\n")
  pw.write(s"4. There are $ooo persons of $lineCount total in data which almost isn't completed data, such as:\n" +
  s"      : specified gender,\n      : legal, resident,\n      : residence county code,\n      : MAKEcode.\n " +
  s"  All customers are active and have valid agreements. In fact, all from them have incorrect year of birth. Many of them have the birth date is on 01.01.1900.\n\n")

  pw.write(s"  The list of customers:\n")
    //foreach(pw.write(ooo2)
    // pw.write(ooo2.toList)
 val rrrtttTTT =  ooo2.map(_ + "\n").foreach(pw.write)
  pw.close()
}

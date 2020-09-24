import java.io.FileNotFoundException

object final_work_1 extends App {

  val srcName = "c:/poem/final.work.txt"
  val destName = "c:/poem/final.work.results.txt"

  var myText = Seq[String]()
  try {
    val filePointer = scala.io.Source.fromFile(srcName)
    val myLines = filePointer.getLines
    myText = myLines.toSeq
    filePointer.close()
  } catch {
    case ex: FileNotFoundException =>
      println(s"file not found $ex")

  }
  myText.foreach(println)


  val longestLine = myText.sortBy(_.size).reverse(0) //the longest line in text
  println(s"In the text the longest line is $longestLine")

  val longestLineLength = longestLine.length
  println(s"In the text the longest line amount is $longestLineLength") //the longest line amount of symbol

  val splitLine = longestLine.split(" ") //to split the words(symbols as well) length
  val longestWord = splitLine.map(word => word ->  word.length)
  longestWord.foreach(println)
  println(longestWord.max, longestWord.min) //īsti nav pareizs!!!

}

package code.challenge

import scala.io.Source

trait ParseIt
{
  def parseFile(filename: String): scala.collection.mutable.Map[String, String] =
  {
    val result = scala.collection.mutable.Map.empty[String,String]
    for (line <- Source.fromFile(filename).getLines())
    {
      val splittedLine  = line.split("\t")
      if(splittedLine.length == 2)
      {
         result.put(splittedLine.last,splittedLine.head)
      }
      else
      {
        println("Unexpected line format!")
      }
    }
    result
  }

}

class ParserImpl extends ParseIt

object Main extends App
{
  val parser = new ParserImpl()
  val products = parser.parseFile("products.tab")
  val sales = parser.parseFile("sales.tab")

  // What is the minimum and maximum sales price for 'Canned Goods'?
  val resultCannedGoods = scala.collection.mutable.ListBuffer.empty[String]
  //filter out Canned Goods category
  for( (k,v) <- products if( k == "Canned Goods") ) resultBreakfast.+=(v)

  

  // How many sales were there in the 'Breakfast' category?
  val resultBreakfast = scala.collection.mutable.ListBuffer.empty[String]
  //filter out Breakfast category
  for( (k,v) <- products if( k == "Breakfast") ) resultBreakfast.+=(v)

  val lisOfValuesInSales = sales map {
    sale => sale._1
  }

  val intersectionBreakfast = resultBreakfast.toSet.intersect(lisOfValuesInSales.toSet)

  println("There are" +intersectionBreakfast.size+ " sales in Breakfast category")


}
package code.challenge

import scala.collection.mutable.ListBuffer
import scala.io.Source

trait ParseIt
{
  def parseFile(filename: String, flip: Boolean): scala.collection.mutable.Map[String, ListBuffer[String]] =
  {
    val result = scala.collection.mutable.Map.empty[String, ListBuffer[String]]

    for (line <- Source.fromFile(filename).getLines())
    {
      val splittedLine = line.split("\t")
      if (splittedLine.length < 2)
      {
        println("Unexpected line format!\n")
      }
      else
      {
        if (flip)
        {
          if (result.contains(splittedLine.head))
          {
            val temp = result(splittedLine.head)
            temp.+=(splittedLine.last)
            result(splittedLine.head) = temp
          }
          else
          {
            val temp = scala.collection.mutable.ListBuffer.empty[String]
            temp.+=(splittedLine.last)
            result(splittedLine.head) = temp
          }
        }
        else
        {
          if (result.contains(splittedLine.last))
          {
            val temp = result(splittedLine.last)
            temp.+=(splittedLine.head)
            result(splittedLine.last) = temp
          }
          else
          {
            val temp = scala.collection.mutable.ListBuffer.empty[String]
            temp.+=(splittedLine.head)
            result(splittedLine.last) = temp
          }
        }
      }
    }
    result
  }
}

class ParserImpl extends ParseIt

object Result extends App
{
  val parser = new ParserImpl()
  val products = parser.parseFile("products.tab", false)
  val sales = parser.parseFile("sales.tab", true)

  // What is the minimum and maximum sales price for 'Canned Goods'?

  //val resultCannedGoods = scala.collection.mutable.ListBuffer.empty[String]
  //filter out Canned Goods category
  val resultCannedGoods = products("Canned Goods")

  var salesCannedGoods = scala.collection.mutable.Set.empty[Double]
  for (each <- resultCannedGoods)
  {
    for( (k,v) <- sales if( k == each ) )
    {
      val tempSales = v map {sale => sale.toDouble}
      salesCannedGoods = salesCannedGoods ++ tempSales.toSet
    }
  }

  println("Minimum sales price for 'Canned Goods' is " + salesCannedGoods.min+ " Maximum sales price for 'Canned Goods' is " + salesCannedGoods.max + "\n")
  // How many sales were there in the 'Breakfast' category?

  //filter out Breakfast category
  val resultBreakfast = products("Breakfast")

  var salesBreakfast = scala.collection.mutable.Set.empty[String]
  for (each <- resultBreakfast)
  {
    for( (k,v) <- sales if( k == each ) )
    {
      salesBreakfast = salesBreakfast ++ v.toSet
    }
  }

  println("There are " +salesBreakfast.size+ " sales in Breakfast category")


}
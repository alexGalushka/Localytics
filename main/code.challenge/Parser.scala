package code.challenge

import scala.io.Source

trait Parser
{
  def getListOfProducts(filename: String): Unit =
  {
    for (line <- Source.fromFile(filename).getLines())
    {
      println(line)
    }
  }

  def getListofSales()
}
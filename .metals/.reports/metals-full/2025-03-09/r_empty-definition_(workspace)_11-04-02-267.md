error id: getLines.
file:///C:/Users/Simon/OneDrive%20-%20HESSO/Documents/MSE/MA_AdvProg/workspace/src/main/scala/org/spl/ShelterDogs.scala
empty definition using pc, found symbol in pc: getLines.
semanticdb not found
|empty definition using fallback
non-local guesses:
	 -file/getLines.
	 -file/getLines#
	 -file/getLines().
	 -scala/Predef.file.getLines.
	 -scala/Predef.file.getLines#
	 -scala/Predef.file.getLines().

Document text:

```scala
package org.spl

import java.util.Date
import scala.io.Source

trait Adoptable:
    val adoptable_from: Date

enum Sex:
    case Male, Female

class Animal(val ID: Int, val name: String, val age: Int, val sex: Sex, val breed: String, val color: String)

case class Dog(ID: Int, name: String, age: Int, sex: Sex, breed: String, date_found: Date, adoptable_from: Date, posted: Date, color: String, coat: String, size: String, neutred: Option[Boolean], house_broken: Option[Boolean], likes_people: Option[Boolean], likes_children: Option[Boolean], get_along_males: Option[Boolean], get_along_females: Option[Boolean], get_along_cats: Option[Boolean], keep_in: String) extends Animal(ID, name, age, sex, breed, color) with Adoptable

object Dog {
    def get_nb_days_in_shelter(dog: Dog): Int = {
        val now = new Date()
        val diff = now.getTime() - dog.date_found.getTime()
        (diff / (1000 * 60 * 60 * 24)).toInt
    }
}

def readCSV(csv_path: String): List[Array[String]] = 
    val bufferedSource = Source.fromFile(csv_path)
    val iter = bufferedSource.getLines().map(_.split(",")).toList
    // bufferedSource.close()
    // iter

@main def shelterDogs() = 
    val data = readCSV("./07-ShelterDogs.csv")
    data.foreach(line => println(line.mkString(",")))
```

#### Short summary: 

empty definition using pc, found symbol in pc: getLines.
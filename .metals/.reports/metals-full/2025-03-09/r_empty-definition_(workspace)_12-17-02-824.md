error id: org/spl/Dog.
file:///C:/Users/Simon/OneDrive%20-%20HESSO/Documents/MSE/MA_AdvProg/workspace/src/main/scala/org/spl/ShelterDogs.scala
empty definition using pc, found symbol in pc: 
found definition using semanticdb; symbol org/spl/Dog.
|empty definition using fallback
non-local guesses:
	 -

Document text:

```scala
package org.spl

import java.util.Date
import java.text.SimpleDateFormat
import scala.io.Source
import scala.util.matching.Regex
import java.io.FileReader
import org.apache.commons.csv._
import scala.collection.JavaConverters._

trait Adoptable:
    val adoptable_from: Date

enum Sex:
    case Male, Female

class Animal(val ID: Int, val name: String, val age: Float, val sex: Sex, val breed: String, val color: String)

case class Dog(override val ID: Int,
 override val name: String, 
 override val age: Float, 
 override val sex: Sex, 
 override val breed: String, 
 date_found: Date, 
 adoptable_from: Date, 
 posted: Date, 
 override val color: String,
 coat: String, 
 size: String, 
 neutred: Option[Boolean], 
 housebroken: Option[Boolean], 
 likes_people: Option[Boolean], 
 likes_children: Option[Boolean], 
 get_along_males: Option[Boolean], 
 get_along_females: Option[Boolean], 
 get_along_cats: Option[Boolean], 
 keep_in: Option[String]
) extends Animal(ID, name, age, sex, breed, color) with Adoptable

object Dog {
    def get_nb_days_in_shelter(dog: Dog): Int = {
        val now = new Date()
        val diff = now.getTime() - dog.date_found.getTime()
        (diff / (1000 * 60 * 60 * 24)).toInt
    }
}

def parseDate(dateStr: String): Date =
    val dateFormat = new SimpleDateFormat("yyyy-mm-dd")
    dateFormat.parse(dateStr)

def parse(cols: List[String], index: Int): Option[String] =
    if (cols.length > index) Some(cols(index)) else None

def parseBoolean(value: Option[String]): Option[Boolean] =
    if value.isEmpty then 
        None
    else
        value.get.toLowerCase match
            case "yes" => Some(true)
            case "no" => Some(false)
            case _ => None

val csvPattern: Regex = "\"([^\"]*)\"|([^,]*)".r

def splitCSVLine(line: String): List[String] =
    csvPattern.findAllMatchIn(line).map(m => 
        Option(m.group(1)).getOrElse(m.group(2)).trim
    ).toList

def loadDogsFromCSV(csvPath: String): List[Dog] =
    val reader = new FileReader(csvPath)
    val parser = CSVFormat.DEFAULT
        .withFirstRecordAsHeader()
        .withIgnoreSurroundingSpaces()
        .withTrim()
        .withQuote('"') // manage quoted strings
        .parse(reader)

    val dogs = parser.getRecords.asScala.flatMap { record =>
        // print record

        val cols = record.toMap

        Dog(
            ID = record.get("ID").toInt,
            name = record.get("name"),
            age = record.get("age").toFloat,
            sex = if (record.get("sex") == "male") Sex.Male else Sex.Female,
            breed = record.get("breed"),
            date_found = parseDate(record.get("date_found")),
            adoptable_from = parseDate(record.get("adoptable_from")),
            posted = parseDate(record.get("posted")),
            color = record.get("color"),
            coat = record.get("coat"),
            size = record.get("size"),
            neutred = parseBoolean(Some(record.get("neutred"))),
            housebroken = parseBoolean(Some(record.get("housebroken"))),
            likes_people = parseBoolean(Some(record.get("likes_people"))),
            likes_children = parseBoolean(Some(record.get("likes_children"))),
            get_along_males = parseBoolean(Some(record.get("get_along_males"))),
            get_along_females = parseBoolean(Some(record.get("get_along_females"))),
            get_along_cats = parseBoolean(Some(record.get("get_along_cats"))),
            keep_in = Some(record.get("keep_in"))
        )
    }.toList

    reader.close()
    dogs
@main def shelterDogs() = 
    val shelter: List[Dog] = loadDogsFromCSV("./07-ShelterDogs.csv")

    shelter.foreach(println)
```

#### Short summary: 

empty definition using pc, found symbol in pc: 
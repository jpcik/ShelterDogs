error id: withIgnoreSurroundingSpaces.
file:///C:/Users/Simon/OneDrive%20-%20HESSO/Documents/MSE/MA_AdvProg/workspace/src/main/scala/org/spl/ShelterDogs.scala
empty definition using pc, found symbol in pc: withIgnoreSurroundingSpaces.
empty definition using semanticdb
found definition using fallback; symbol withIgnoreSurroundingSpaces
Document text:

```scala
package org.spl

import java.util.Date
import java.text.SimpleDateFormat
import scala.io.Source
import scala.util.matching.Regex

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
        .withFirstRecordAsHeader() // Ignore l'en-tête
        .withIgnoreSurroundingSpaces() // Enlève les espaces inutiles
        .withTrim() // Supprime les espaces avant/après chaque champ
        .withQuote('"') // Gère les valeurs entre guillemets
        .parse(reader)

    lines.map { line =>
        val cols = splitCSVLine(line)
        println(line)
        Dog(
            ID = cols(0).toInt,
            name = cols(1),
            age = cols(2).toFloat,
            sex = if (cols(3) == "male") Sex.Male else Sex.Female,
            breed = cols(4),
            date_found = parseDate(cols(5)),
            adoptable_from = parseDate(cols(6)),
            posted = parseDate(parse(cols, 7).get),
            color = parse(cols, 8).getOrElse("unknown"),
            coat = parse(cols, 9).getOrElse("unknown"),
            size = parse(cols, 10).getOrElse("unknown"),
            neutred = parseBoolean(parse(cols, 11)),
            housebroken = parseBoolean(parse(cols, 12)),
            likes_people = parseBoolean(parse(cols, 13)),
            likes_children = parseBoolean(parse(cols, 14)),
            get_along_males = parseBoolean(parse(cols, 15)),
            get_along_females = parseBoolean(parse(cols, 16)),
            get_along_cats = parseBoolean(parse(cols, 17)),
            keep_in = parse(cols, 18)
        )
    }
@main def shelterDogs() = 
    val shelter: List[Dog] = loadDogsFromCSV("./07-ShelterDogs.csv")

    shelter.foreach(println)
```

#### Short summary: 

empty definition using pc, found symbol in pc: withIgnoreSurroundingSpaces.
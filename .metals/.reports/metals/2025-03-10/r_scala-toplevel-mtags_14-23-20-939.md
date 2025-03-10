error id: file:///C:/Users/Simon/OneDrive%20-%20HESSO/Documents/MSE/MA_AdvProg/workspace/src/main/scala/org/spl/Main.scala:[901..902) in Input.VirtualFile("file:///C:/Users/Simon/OneDrive%20-%20HESSO/Documents/MSE/MA_AdvProg/workspace/src/main/scala/org/spl/Main.scala", "package org.spl

import scala.io.Source
import java.util.Date
import java.text.SimpleDateFormat
import java.io.InputStreamReader
import org.apache.commons.csv.CSVFormat
import java.io.FileInputStream
import scala.collection.JavaConverters._


def parseDate(dateStr: String): Date =
    val dateFormat = new SimpleDateFormat("yyyy-mm-dd")
    dateFormat.parse(dateStr)

def parseBoolean(value: String): Option[Boolean] =
    value.toLowerCase match
        case "yes" => Some(true)
        case "no" => Some(false)
        case _ => None

def parseString(value: String): Option[String] =
    if value.isEmpty then None else Some(value)

def parseList(value: String): List[String] =
    value.split(",").toList

def parseAge(value: String): Int | Float =
    if value.contains(".") then value.toFloat else value.toInt

def parseEnum(value: String, enumm: Enumeration): enum.Value =
    enum.withName(value)

def loadDogsFromCSV(csvPath: String): List[Dog] =
    val reader = new InputStreamReader(new FileInputStream(csvPath), "UTF-8")
    val parser = CSVFormat.DEFAULT
        .withFirstRecordAsHeader()
        .withIgnoreSurroundingSpaces()
        .withTrim()
        .withQuote('"') // manage quoted strings
        .parse(reader)

    val dogs = parser.getRecords.asScala.flatMap { record =>
        
        Some(Dog(
            ID = record.get("ID").toInt,
            name = parseString(record.get("name")),
            age = record.get("age").toFloat,
            sex = if (record.get("sex") == "male") Sex.Male else Sex.Female,
            breed = parseList(record.get("breed")),
            date_found = parseDate(record.get("date_found")),
            adoptable_from = parseDate(record.get("adoptable_from")),
            posted = parseDate(record.get("posted")),
            color = parseString(record.get("color")),
            coat = parseString(record.get("coat")),
            size = parseString(record.get("size")),
            neutered = parseBoolean(record.get("neutered")),
            housebroken = parseBoolean(record.get("housebroken")),
            likes_people = parseBoolean(record.get("likes_people")),
            likes_children = parseBoolean(record.get("likes_children")),
            get_along_males = parseBoolean(record.get("get_along_males")),
            get_along_females = parseBoolean(record.get("get_along_females")),
            get_along_cats = parseBoolean(record.get("get_along_cats")),
            keep_in = parseString(record.get("keep_in"))
        ))
    }.toList

    reader.close()
    dogs
@main def shelterDogs() = 
    val shelter: List[Dog] = loadDogsFromCSV("./07-ShelterDogs.csv")

    shelter.foreach(println)

    println(Dog.get_nb_days_in_shelter(shelter.head))
")
file:///C:/Users/Simon/OneDrive%20-%20HESSO/Documents/MSE/MA_AdvProg/workspace/src/main/scala/org/spl/Main.scala:31: error: expected identifier; obtained dot
def parseEnum(value: String, enumm: Enumeration): enum.Value =
                                                      ^
#### Short summary: 

expected identifier; obtained dot
package org.spl

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
    // split string by comma and remove leading/trailing whitespaces
    value.split(",").map(_.trim).toList

def parseIntFloat(value: String): Int | Float =
    if value.contains(".") then value.toFloat else value.toInt

// Generic function to parse an enumeration generic type E restricted to only be an Enumeration
def parseEnum[E <: Enum[E]](value: String, enumClass: Class[E]): Option[E] =
    if value.isEmpty then
        None
    else 
        enumClass.getEnumConstants.find(_.toString.equalsIgnoreCase(value))

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
            age = parseIntFloat(record.get("age")),
            sex = parseEnum(record.get("sex"), classOf[Sex]).get, // sex is always set in data 
            breed = parseList(record.get("breed")),
            date_found = parseDate(record.get("date_found")),
            adoptable_from = parseDate(record.get("adoptable_from")),
            posted = parseDate(record.get("posted")),
            color = parseString(record.get("color")),
            coat = parseEnum(record.get("coat"), classOf[Coat]).get, // coat is always set in data 
            size = parseEnum(record.get("size"), classOf[Size]).get, // size is always set in data 
            neutered = parseBoolean(record.get("neutered")),
            housebroken = parseBoolean(record.get("housebroken")),
            likes_people = parseBoolean(record.get("likes_people")),
            likes_children = parseBoolean(record.get("likes_children")),
            get_along_males = parseBoolean(record.get("get_along_males")),
            get_along_females = parseBoolean(record.get("get_along_females")),
            get_along_cats = parseBoolean(record.get("get_along_cats")),
            keep_in = parseEnum(record.get("keep_in"), classOf[KeepIn])
        ))
    }.toList

    reader.close()
    dogs
@main def shelterDogs() = 
    val shelter: List[Animal[Dog]] = loadDogsFromCSV("./07-ShelterDogs.csv") // works because Covariant of Animal

    shelter.foreach(println)

    println(Dog.get_nb_days_in_shelter(shelter.head.asInstanceOf[Dog]))

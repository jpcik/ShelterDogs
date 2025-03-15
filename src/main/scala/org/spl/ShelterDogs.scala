package org.spl

import java.util.Date

trait Adoptable{
    val adoptable_from: Date
}

enum Sex extends Enum[Sex] { // has to herit from Enum to be used in parseEnum function
    case female, male
}

enum Size extends Enum[Size] {
    case small, medium, large
}

enum Coat extends Enum[Coat] {
    case long, medium, short, wirehaired
}

enum KeepIn extends Enum[KeepIn] {
    case flat, garden, both_flat_and_garden
}

type Age = Int | Float // Union type

trait Animal:
    val ID: Int
    val name: Option[String]
    val age: Age // type alias
    val sex: Sex
    val breed: List[String] 
    val color: Option[String]

case class Dog(
    ID: Int,
    name: Option[String],
    age: Age, // type alias
    sex: Sex, 
    breed: List[String], 
    date_found: Date, 
    adoptable_from: Date, 
    posted: Date, 
    color: Option[String],
    coat: Coat,
    size: Size,
    neutered: Option[Boolean], 
    housebroken: Option[Boolean], 
    likes_people: Option[Boolean], 
    likes_children: Option[Boolean], 
    get_along_males: Option[Boolean], 
    get_along_females: Option[Boolean], 
    get_along_cats: Option[Boolean], 
    keep_in: Option[KeepIn]
) extends Animal with Adoptable

object Dog {
    def get_nb_days_in_shelter(dog: Dog): Int = {
        val now = new Date()
        val diff = now.getTime() - dog.date_found.getTime()
        (diff / (1000 * 60 * 60 * 24)).toInt
    }
}
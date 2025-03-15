package org.spl

import java.util.Date

trait Adoptable{
    val adoptable_from: Date
}

enum Sex extends Enum[Sex] {
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

// jpc: why abstract class? not better to use a trait?
// jpc: not clear why you need a type aprameter in this case. what is the meaning of T here?
abstract class Animal[+T] ( // Covariant type parameter
    val ID: Int, 
    val name: Option[String], 
    val age: Int | Float, // Union type
    val sex: Sex, 
    val breed: List[String], 
    val color: Option[String]
    )

case class Dog(override val ID: Int,
    override val name: Option[String],  //jpc: why override? not better to use a trait instead of abstract class?
    override val age: Int | Float, // Union type
    override val sex: Sex, 
    override val breed: List[String], 
    date_found: Date, 
    adoptable_from: Date, 
    posted: Date, 
    override val color: Option[String],
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
) extends Animal(ID, name, age, sex, breed, color) with Adoptable

object Dog {
    def get_nb_days_in_shelter(dog: Dog): Int = {
        val now = new Date()
        val diff = now.getTime() - dog.date_found.getTime()
        (diff / (1000 * 60 * 60 * 24)).toInt
    }
}
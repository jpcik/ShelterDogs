package org.spl

import java.util.Date
import org.spl.Sex.Sex
import org.spl.Size.Size
import org.spl.Coat.Coat
import org.spl.KeepIn.KeepIn

trait Adoptable:
    val adoptable_from: Date

object Sex extends Enumeration {
    type Sex = Value // type alias
    val female, male = Value
}

object Size extends Enumeration {
    type Size = Value // type alias
    val small, medium, large = Value
}

object Coat extends Enumeration {
    type Coat = Value // type alias
    val long, medium, short, wirehaired = Value
}

object KeepIn extends Enumeration {
    type KeepIn = Value // type alias
    val flat, garden, both_flat_and_garden = Value
}

abstract class Animal[+T] ( // Covariant type parameter
    val ID: Int, 
    val name: Option[String], 
    val age: Int | Float, // Union type
    val sex: Sex, 
    val breed: List[String], 
    val color: Option[String]
    )

case class Dog(override val ID: Int,
    override val name: Option[String], 
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
package org.spl

import java.util.Date

trait Adoptable:
    val adoptable_from: Date

enum Sex:
    case Male, Female

class Animal(
    val ID: Int, 
    val name: Option[String], 
    val age: Float, 
    val sex: Sex, 
    val breed: Option[String], 
    val color: Option[String]
    )

case class Dog(override val ID: Int,
    override val name: Option[String], 
    override val age: Float, 
    override val sex: Sex, 
    override val breed: Option[String], 
    date_found: Date, 
    adoptable_from: Date, 
    posted: Date, 
    override val color: Option[String],
    coat: Option[String], 
    size: Option[String], 
    neutered: Option[Boolean], 
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
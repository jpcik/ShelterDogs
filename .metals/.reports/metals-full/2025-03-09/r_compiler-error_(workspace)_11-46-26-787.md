file:///C:/Users/Simon/OneDrive%20-%20HESSO/Documents/MSE/MA_AdvProg/workspace/src/main/scala/org/spl/ShelterDogs.scala
### java.nio.file.InvalidPathException: Illegal char <:> at index 3: jar:file:///C:/Users/Simon/AppData/Local/Coursier/cache/arc/https/github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.14%25252B7/OpenJDK17U-jdk_x64_windows_hotspot_17.0.14_7.zip/jdk-17.0.14+7/lib/src.zip!/java.base/java/lang/String.java

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 1456
uri: file:///C:/Users/Simon/OneDrive%20-%20HESSO/Documents/MSE/MA_AdvProg/workspace/src/main/scala/org/spl/ShelterDogs.scala
text:
```scala
package org.spl

import java.util.Date
import java.text.SimpleDateFormat
import scala.io.Source

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
 keep_in: String
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

def parseOption(cols: Array[Any], index: Int): Option[Any] =
    if (cols.length > index) Some(cols(index)) else None

def parseBoolean(value: String): Option[Boolean] =
    value.toLowerCase m@@
        case "yes" => Some(true)
        case "no" => Some(false)
        case _ => None

def loadDogsFromCSV(csvPath: String): List[Dog] =
    val source = Source.fromFile(csvPath, "UTF-8")
    val lines = source.getLines().drop(1).toList // skip header
    source.close()

    lines.map { line =>
        val cols = line.split(",").map(_.trim)
        println(cols.length)
        Dog(
            ID = cols(0).toInt,
            name = cols(1),
            age = cols(2).toFloat,
            sex = if (cols(3) == "male") Sex.Male else Sex.Female,
            breed = cols(4),
            date_found = parseDate(cols(5)),
            adoptable_from = parseDate(cols(6)),
            posted = parseDate(cols(7)),
            color = cols(8),
            coat = cols(9),
            size = cols(10),
            neutred = parseBoolean(cols(11)),
            housebroken = parseBoolean(cols(12)),
            likes_people = parseBoolean(cols(13)),
            likes_children = parseBoolean(cols(14)),
            get_along_males = parseBoolean(cols(15)),
            get_along_females = parseBoolean(cols(16)),
            get_along_cats = parseBoolean(cols(17)),
            keep_in = cols(18)
        )
    }
@main def shelterDogs() = 
    val shelter: List[Dog] = loadDogsFromCSV("./07-ShelterDogs.csv")

    shelter.foreach(println)
```



#### Error stacktrace:

```
java.base/sun.nio.fs.WindowsPathParser.normalize(WindowsPathParser.java:182)
	java.base/sun.nio.fs.WindowsPathParser.parse(WindowsPathParser.java:153)
	java.base/sun.nio.fs.WindowsPathParser.parse(WindowsPathParser.java:77)
	java.base/sun.nio.fs.WindowsPath.parse(WindowsPath.java:92)
	java.base/sun.nio.fs.WindowsFileSystem.getPath(WindowsFileSystem.java:232)
	java.base/java.nio.file.Path.of(Path.java:147)
	java.base/java.nio.file.Paths.get(Paths.java:69)
	scala.meta.io.AbsolutePath$.apply(AbsolutePath.scala:58)
	scala.meta.internal.metals.MetalsSymbolSearch.$anonfun$definitionSourceToplevels$2(MetalsSymbolSearch.scala:70)
	scala.Option.map(Option.scala:242)
	scala.meta.internal.metals.MetalsSymbolSearch.definitionSourceToplevels(MetalsSymbolSearch.scala:69)
	dotty.tools.pc.completions.CaseKeywordCompletion$.dotty$tools$pc$completions$CaseKeywordCompletion$$$sortSubclasses(MatchCaseCompletions.scala:342)
	dotty.tools.pc.completions.CaseKeywordCompletion$.matchContribute(MatchCaseCompletions.scala:292)
	dotty.tools.pc.completions.Completions.advancedCompletions(Completions.scala:350)
	dotty.tools.pc.completions.Completions.completions(Completions.scala:120)
	dotty.tools.pc.completions.CompletionProvider.completions(CompletionProvider.scala:90)
	dotty.tools.pc.ScalaPresentationCompiler.complete$$anonfun$1(ScalaPresentationCompiler.scala:146)
```
#### Short summary: 

java.nio.file.InvalidPathException: Illegal char <:> at index 3: jar:file:///C:/Users/Simon/AppData/Local/Coursier/cache/arc/https/github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.14%25252B7/OpenJDK17U-jdk_x64_windows_hotspot_17.0.14_7.zip/jdk-17.0.14+7/lib/src.zip!/java.base/java/lang/String.java
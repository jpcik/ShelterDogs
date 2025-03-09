error id: 
file:///C:/Users/Simon/OneDrive%20-%20HESSO/Documents/MSE/MA_AdvProg/workspace/src/main/scala/org/spl/Exec.scala.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
|empty definition using fallback
non-local guesses:
	 -

Document text:

```scala
package org.spl

import scala.io.Source

val something = "Hello"
val value = 42

def readCSV(csv_path: String): Iterator[Array[String]] = 
    val src = Source.fromFile(csv_path)
    val iter = src.getLines().drop(1).map(_.split(","))
    src.close()
    return iter

@main def main() = 
    // val iter = readCSV("07-ShelterDogs.csv")
    iter.foreach(println)
```

#### Short summary: 

empty definition using pc, found symbol in pc: 
error id: org/spl/
file:///C:/Users/Simon/OneDrive%20-%20HESSO/Documents/MSE/MA_AdvProg/workspace/src/main/scala/org/spl/Exec.scala.scala
empty definition using pc, found symbol in pc: org/spl/
semanticdb not found
|empty definition using fallback
non-local guesses:
	 -

Document text:

```scala
package org.spl

import scala.io.Source

def readCSV(csv_path: String): List[Array[String]] = 
    val bufferedSource = Source.fromFile(csv_path)
    val iter = bufferedSource.getLines().map(_.split(",")).toList
    bufferedSource.close()
    iter

@main def main() = 
    val data = readCSV("./07-ShelterDogs.csv")
    data.foreach(line => println(line.mkString(",")))

```

#### Short summary: 

empty definition using pc, found symbol in pc: org/spl/
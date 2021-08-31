import eci.edu.co.Transaction

import scala.collection.parallel.CollectionConverters.ImmutableSeqIsParallelizable

val transaction1 = Transaction(value = 16.4, currency = Transaction.USD, `type` = Transaction.Income)
val transaction2 = Transaction(value = 16.4, currency = Transaction.COP, `type` = Transaction.Income)
val transaction3 = Transaction(value = 54, currency = Transaction.COP, `type` = Transaction.Income)


transaction1 == transaction2

val list = List(6, 3, 7, 1, 0, 5, 4, 9)

list.filter(n => n > 5)

list.map(n => n > 5)
list.map(n => n * 2)

val someNumber: Option[Int] = Some(6)
val noNumber: Option[Int] = None

val listToFlatten = List(List(6, 3), List(7), List(1), List(0, 5, 4, 9))
listToFlatten.flatten

val listOptionsToFlatten = List(Some(10), None, None, Some(8), None, Some(6))
listOptionsToFlatten.flatten

val listFlatMap = List(5, 1, 0, 4, 2, 3)
val optionsList = listFlatMap.map(n => if (n * 2 > 5) Some(n * 2) else None)
optionsList.flatten

listFlatMap.flatMap(n => if (n * 2 > 5) Some(n * 2) else None)

list.head
list.headOption
list.tail


list.last
list.lastOption
list.init

val emptyList = List.empty

//emptyList.head
emptyList.headOption

//emptyList.last
emptyList.lastOption


list.fold(0)(_ + _)
list.par.fold(0)(_ + _)

// r -> result, c -> current
list.fold(0)((r, c) => r + c)

list.fold(2)(_ + _)
list.par.fold(2)(_ + _)

val listNotUniques = List(2, 1, 3, 4, 1, 5, 1, 2)
listNotUniques.distinct

val list2 = List(6, null, 7, 1)
list2.flatMap(i => if(i != null) Some(i) else None)

val list4 =List(transaction1, transaction2, transaction3)

val listT = list4.flatMap(t =>if(t.`type` == Transaction.Income && t.currency == Transaction.COP) Some(t.value) else None)
listT.foldLeft(0.0)(_ + _)
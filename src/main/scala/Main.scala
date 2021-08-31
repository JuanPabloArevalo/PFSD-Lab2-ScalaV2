package eci.edu.co

object Main extends App {

  val transaction1 = Transaction(value = 16.4, currency = Transaction.USD, `type` = Transaction.Income)
  val randomTransaction = Transaction.randomTransaction()

  transaction1.printTransaction
  randomTransaction.printTransaction
}

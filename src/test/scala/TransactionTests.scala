package eci.edu.co

import Transaction.{toEurTransaction, toUsdTransaction}

import org.scalatest.FunSuite

class TransactionTests extends FunSuite {

  val t1 = new Transaction(1.0, Transaction.USD, Transaction.Income)
  val t2 = new Transaction(26.0, Transaction.USD, Transaction.Income)
  val t3 = new Transaction(5.0, Transaction.EUR, Transaction.Expense)
  val t4 = new Transaction(300000, Transaction.COP, Transaction.Expense)
  val t5 = new Transaction(300000, Transaction.COP, Transaction.Income)
  val t6 = new Transaction(14, Transaction.GBP, Transaction.Income)
  val t7 = new Transaction(356, Transaction.JPY, Transaction.Income)

  val transactionList = List(t1, t2, t3, t4, t5, t6, t7)

  test("distinctBy transactions by value") {
    val uniqueTransactionsExpected = List(t1, t2, t3, t4, t6, t7)
    val uniqueTransactions: List[Transaction] = Transaction.getTransactionUniqueValue(transactionList)
    assert(uniqueTransactions == uniqueTransactionsExpected)
  }

  test("convert all transactions to USD") {
    val usdTransactionsExpected = List(
      t1,
      t2,
      Transaction(5.8483987603305785, Transaction.USD, Transaction.Expense),
      Transaction(77.47933884297521, Transaction.USD, Transaction.Expense),
      Transaction(77.47933884297521, Transaction.USD, Transaction.Income),
      Transaction(19.072830578512395, Transaction.USD, Transaction.Income),
      Transaction(3.241880165289256, Transaction.USD, Transaction.Income),
    )

    val usdTransactions = Transaction.getAllTransactionInUSD(transactionList)
    assert(usdTransactions == usdTransactionsExpected)
  }

  test("convert all transactions to EUR") {
    val eurTransactionsExpected = List(
      Transaction(22.228306469419298, Transaction.EUR, Transaction.Income),
      Transaction(66.23978803267829, Transaction.EUR, Transaction.Expense),
      Transaction(66.23978803267829, Transaction.EUR, Transaction.Income),
      Transaction(16.306027820710973, Transaction.EUR, Transaction.Income),
    )

    val eurTransactions = Transaction.getAllTransactionInEURMoreThanX(transactionList, 15)
    assert(eurTransactions == eurTransactionsExpected)
  }

  test("total Income transactions in COP") {
    val expectedTotalIncomeInCop = 490946.56
    val totalIncomeInCop = Transaction.getTotalValueInCOPTransactions(transactionList)
    assert(totalIncomeInCop == expectedTotalIncomeInCop)
  }
}

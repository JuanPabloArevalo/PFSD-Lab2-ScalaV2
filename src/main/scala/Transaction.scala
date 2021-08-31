package eci.edu.co

trait FinanceOperation {
  def income

  def expense(`type`: String): Unit = {
    if (`type` == Transaction.Expense)
      println("Expense operation")
  }
}

case class Transaction(value: Double, currency: String, `type`: String = "") extends FinanceOperation {

  import Transaction._

  override def income: Unit =
    if (`type` == Transaction.Income)
      println("Income operation")

  def this(currency: String, `type`: String) {
    this(0, currency, `type`)
  }

  def valueInCop = calculateValueInCop(currency, value)

  def valueInCopT = calculateValueInCop(this)

  def printTransaction: Unit = println(toString)

  override def toString: String = s"Value: $value, Currency: $currency, Type: ${`type`}"

}

object Transaction {

  import scala.util.Random

  val COP = "COP"
  val USD = "USD"
  val EUR = "EUR"
  val GBP = "GBP"
  val JPY = "JPY"
  val Currencies = List(COP, USD, EUR, GBP, JPY)

  val USD_COP = 3872
  val EUR_COP = 4529
  val GBP_COP = 5275
  val JPY_COP = 35.26

  val Income = "Income"
  val Expense = "Expense"
  val Types = List(Income, Expense)

  val random = new Random()

  def calculateValueInCop(currency: String, value: Double): Double = {
    currency match {
      case USD => value * USD_COP
      case EUR => value * EUR_COP
      case GBP => value * GBP_COP
      case JPY => value * JPY_COP
      case COP => value
      case _ => 0
    }
  }

  def calculateValueInCop(t: Transaction): Double = {
    t match {
      case Transaction(v, USD, _) => v * USD_COP
      case Transaction(v, EUR, _) => v * EUR_COP
      case Transaction(v, JPY, _) => v * JPY_COP
      case Transaction(v, GBP, _) => v * GBP_COP
      case Transaction(v, COP, _) => v
      case _ => 0
    }
  }

  def copToUsdValue(t: Transaction): Double = {
    t match {
      case Transaction(_, USD, _) => t.value
      case _ => t.valueInCopT / USD_COP
    }
  }

  def toUsdTransaction(t: Transaction): Transaction = Transaction(Transaction.copToUsdValue(t), Transaction.USD, t.`type`)

  def copToEurValue(t: Transaction): Double = {
    t match {
      case Transaction(_, EUR, _) => t.value
      case _ => t.valueInCopT / EUR_COP
    }
  }

  def toEurTransaction(t: Transaction): Transaction = {
    t match {
      case Transaction(_, EUR, _) => t
      case _ => Transaction(Transaction.copToEurValue(t), Transaction.EUR, t.`type`)
    }
  }

  def getTransactionUniqueValue(transactionList: List[Transaction]): List[Transaction] ={
    transactionList.distinctBy(_.value)
  }

  def randomTransaction() = {
    val currency = randomCurrency
    new Transaction(currency = currency, value = randomValue(currency), `type` = randomType)
  }

  def randomCurrency: String = Currencies(random.nextInt(Currencies.length))

  def randomType: String = Types(random.nextInt(Types.length))

  def randomValue(currency: String): Double = currency match {
    case USD => random.nextInt(15)
    case EUR => random.nextInt(10)
    case COP => random.nextInt(50000)
    case _ => 0
  }

  def getAllTransactionInUSD(transactionList: List[Transaction]): List[Transaction] =
    transactionList.map(t => toUsdTransaction(t))

  def getAllTransactionInEURMoreThanX(transactionList: List[Transaction], moreThanXValue: Int): List[Transaction] =
    transactionList.flatMap(t => {
      val eurTransaction = Transaction.toEurTransaction(t)
      if(eurTransaction.value > moreThanXValue) Some(eurTransaction) else None
    })

  def getTotalValueInCOPTransactions(transactionList: List[Transaction]): Double =
    transactionList.flatMap(t =>if(t.`type` == Income) Some(Transaction.calculateValueInCop(t)) else None)
      .foldLeft(0.0)(_ + _ )
}

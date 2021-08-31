# SessionAugust21-2021
1. Obtener una lista sin transacciones repetidas con base exclusivamente en su valor.
    + Hint: distinctBy
2. Adicionar el pattern matching para GBP y JPY en la función calculateValueInCop
    + GBP = British pound sterling, 1GBP = 5275 COP
    + JPY = Japanese yen, 1 JPY = 35.26 COP
3. Convertir todas las transacciones de una lista a transacciones en USD.
    + Hint: Implemente la función copToUsdValue y toUsdTransaction
4. Obtener todas las transacciones de una lista en transacciones en EUR y que superen los 15 EUR.
    + Hint: Implemente la función copToEurValue y toEurTransaction, use flatMap
5. Obtener el valor de todas las transacciones tipo Income en COP.
    + Hint: Use flatMap y alguna operación de foldLeft
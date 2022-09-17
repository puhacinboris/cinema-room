var currentIncome = 0
var ticketsSold = 0

// Showing created cinema room with inputted rows and seats
fun showCinemaRoom(rows: Int, seats: Int, cinemaRoom: MutableList<MutableList<Char>>) {
    println()
    println("Cinema:")
    print(" ")
    for (i in 1..seats) print(" $i")
    println()
    for (i in 0 until rows) {
        print("${i + 1}")
        for (j in 0 until seats) {
            print(" " + cinemaRoom[i][j])
        }
        println()
    }
}

// Buying a seat and printing a ticket price
fun buyTicket(cinemaRoom: MutableList<MutableList<Char>>, numOfRows: Int, numOfSeats: Int) {
    try {
        println()
        println("Enter a row number:")
        val seatRow = readln().toInt()

        println("Enter a seat number in that row:")
        val seatSeat = readln().toInt()

        val firstHalf = numOfRows / 2 // Ticket is $10

        if (cinemaRoom[seatRow - 1][seatSeat - 1] == 'B') {
            println("\nThat ticket has already been purchased!")
            return buyTicket(cinemaRoom, numOfRows, numOfSeats)
        } else {
            ticketsSold++
            cinemaRoom[seatRow - 1][seatSeat - 1] = 'B'

            if (numOfRows * numOfSeats <= 60 || seatRow <= firstHalf) {
                currentIncome += 10
                println("\nTicket price: $10")
            } else {
                currentIncome += 8
                println("\nTicket price: $8")
            }
        }
    } catch (e: IndexOutOfBoundsException) {
        println("\nWrong input!")
        return buyTicket(cinemaRoom, numOfRows, numOfSeats)
    }
}

// Showing statistics with the:
// number of sold tickets,
// percentage of sold tickets
// money earned
// and maximum money that can be earned

fun statistics(numOfRows: Int, numOfSeats: Int) {
    // Total income
    val totalSeats = numOfRows * numOfSeats
    val firstHalf = numOfRows / 2
    val secondHalf = numOfRows - firstHalf

    val totalIncome = if (totalSeats <= 60) {
        totalSeats * 10
    } else {
        (firstHalf * numOfSeats * 10) + (secondHalf * numOfSeats * 8)
    }

    // Percentage
    val percentage = (ticketsSold.toDouble() / totalSeats.toDouble()) * 100.0
    val formatPercentage = "%.2f".format(percentage)

    println("\nNumber of purchased tickets: $ticketsSold")
    println("Percentage: ${formatPercentage}%") // 0.00
    println("Current income: $$currentIncome")
    println("Total income: $$totalIncome")
}

fun main() {
    // Inputting number of rows and seats to create cinema room
    println("Enter the number of rows:")
    val numOfRows = readln().toInt()

    println("Enter the number of seats in each row:")
    val numOfSeats = readln().toInt()

    // Populating created cinema room with letter S for seat
    val cinemaRoom = MutableList(numOfRows) { MutableList(numOfSeats) { 'S' } }

    // Choosing options
    var chosenOption: Int

    do {
        println()
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        chosenOption = readln().toInt()

        when (chosenOption) {
            1 -> showCinemaRoom(numOfRows, numOfSeats, cinemaRoom)
            2 -> buyTicket(cinemaRoom, numOfRows, numOfSeats)
            3 -> statistics(numOfRows, numOfSeats)
            0 -> return
            else -> println("Error wrong command!")
        }
    } while (chosenOption != 0)
}
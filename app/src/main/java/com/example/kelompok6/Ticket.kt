package com.example.kelompok6

import java.sql.Date

data class Ticket(
    val hotelName: String = "",
    val bookingDate: Date,
    val roomDetails: String = "",
    val paymentCode: String = ""
)

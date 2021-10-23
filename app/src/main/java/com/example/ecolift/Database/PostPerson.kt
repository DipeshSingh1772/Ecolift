package com.example.ecolift.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "post_person")
data class PostPerson (
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,

    @ColumnInfo(name = "Pickup")
    val Pickup:String,

    @ColumnInfo(name = "Destination")
    val Destination:String,

    @ColumnInfo(name = "Data")
    val Date:Long = 0,

    @ColumnInfo(name = "Time")
    val Time:Long = 0,

    @ColumnInfo(name = "Name")
    val Name:String,

    @ColumnInfo(name = "Seats")
    var Seats:Int,

    @ColumnInfo(name = "Mobile no.")
    val MobileNo:Int,

    @ColumnInfo(name = "Email")
    val Email:String,

    @ColumnInfo(name = "Amount")            //Fare Amount per person
    val Amount: Int,

    @ColumnInfo(name = "Car")
    val Car:String

)
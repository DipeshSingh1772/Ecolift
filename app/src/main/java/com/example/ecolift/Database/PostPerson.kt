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

    @ColumnInfo(name = "Name")
    val Name:String,

    @ColumnInfo(name = "Seats")
    var Seats:Int,

    @ColumnInfo(name = "Mobile no.")
    val Mobile:Int,

    @ColumnInfo(name = "Fare Amount per person")
    val Amount: Int,

    @ColumnInfo(name = "Car")
    val Car:String

)
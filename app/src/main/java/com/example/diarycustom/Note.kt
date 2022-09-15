package com.example.diarycustom

data class Note (
    var _id: Int,
    var address: String,
    var locationX: String,
    var locationY: String,
    var contents: String,
    // var mood: String,
    var picture: String,
    var createDateStr: String
)
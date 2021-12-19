package com.example.mvc.database

import java.time.LocalDateTime

data class Todo(
    var index:Int?=null,
    var title:String?=null,
    var description:String?=null,
    var schedule:LocalDateTime?=null,
    var createdAt: LocalDateTime?=null,
    var updateAt:LocalDateTime?=null
)

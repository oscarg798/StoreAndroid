package co.com.core

import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by oscarg798 on 1/15/18.
 */
data class Request(val uuid: String = UUID.randomUUID().toString(),
                   val ammount: String,
                   val user: String,
                   val location: String,
                   val deliverTime: String?,
                   val aditionalInfo: String?,
                   val status: String,
                   val requestProducts: ArrayList<RequestProduct>)


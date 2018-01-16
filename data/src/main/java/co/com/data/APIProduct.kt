package co.com.data

/**
 * Created by oscarg798 on 10/11/17.
 */
data class APIProduct(val uuid: String, val name: String,
                      val description: String,
                      val images: List<String>?,
                      val price: String,
                      val category: String)
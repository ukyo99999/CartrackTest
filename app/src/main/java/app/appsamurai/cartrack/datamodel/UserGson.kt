package app.appsamurai.cartrack.datamodel

import java.io.Serializable

/**
 * Created by Ukyo on 2019-06-25.
 *
 */
class UserGson : Serializable {

    var id: Int? = null
    var name: String? = null
    var username: String? = null
    var email: String? = null
    var address: Address? = null
    var phone: String? = null
    var website: String? = null
    var company: Company? = null

    inner class Address : Serializable {
        var street: String? = null
        var suite: String? = null
        var city: String? = null
        var zipcode: String? = null
        var geo: Geo? = null
    }

    inner class Geo : Serializable {
        var lat: String? = null
        var lng: String? = null
    }

    inner class Company : Serializable {
        var name: String? = null
        var catchPhrase: String? = null
        var bs: String? = null
    }

}
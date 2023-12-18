package com.example.iykyk_project

data class SlamBookData(
    var UID: String? = null,
    var q1: String? = null,
    var q2: String? = null,
    var q3: String? = null,
    var q4: String? = null,
    var q5: String? = null,
    var q6: String? = null,
    var q7: String? = null,
    var q8: String? = null,
    var q9: String? = null
)   { constructor(q1: String?) : this() {
    this.q1 = q1
}
}


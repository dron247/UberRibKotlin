package com.dgse.my_rib_app.utility.view_state

class RibState(val id: String, val isDefault: Boolean = false, private val block: () -> Unit) {
    companion object {
        private const val KEY_EMPTY = "empty"
        val Empty = RibState(KEY_EMPTY) {}
    }

    fun apply() {
        block()
    }

    val isEmpty: Boolean
        get() {
            return KEY_EMPTY == id
        }
}
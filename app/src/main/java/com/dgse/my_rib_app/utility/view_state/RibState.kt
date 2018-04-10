package com.dgse.my_rib_app.utility.view_state

class RibState(
        val id: String,
        val isDefault: Boolean = false,
        private val block: () -> Unit) : Comparable<RibState> {

    companion object {
        private const val KEY_EMPTY = "empty"
        val Empty = RibState(KEY_EMPTY) {}
    }

    /**
     * Applies block of the state pn caller side
     */
    fun apply() {
        block()
    }

    /**
     * Indicates if current state us useful or it is just empty
     */
    val isEmpty: Boolean
        get() {
            return KEY_EMPTY == id
        }

    //region Comparable
    override fun compareTo(other: RibState) = id.compareTo(other.id)
    //endregion
}
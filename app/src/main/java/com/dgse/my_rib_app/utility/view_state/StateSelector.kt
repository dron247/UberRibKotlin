package com.dgse.my_rib_app.utility.view_state

import com.uber.rib.core.Bundle

class StateSelector(
        private val uniqueId: String,
        private val useEmptyInsteadOfDefault: Boolean = false) {
    private val stateList = mutableSetOf<RibState>()

    private var state: RibState? = null
        set(value) {
            field = value
            value?.apply()
        }

    fun addState(ribState: RibState) {
        stateList.add(ribState)
    }

    fun setFrom(bundle: Bundle?): Boolean {
        val found = findIn(bundle)
        state = found
        return !found.isEmpty
    }

    fun setBy(identifier: String): Boolean {
        val found = findBy(identifier)
        state = found
        return !found.isEmpty
    }

    private fun findIn(bundle: Bundle?): RibState {
        if (bundle == null) {
            return findDefault() ?: RibState.Empty
        }

        val identifier = bundle.getString(uniqueId) ?: return RibState.Empty
        return findBy(identifier)
    }

    private fun findBy(identifier: String): RibState {
        for (state in stateList) {
            if (state.id == identifier) {
                return state
            }
        }
        return if (useEmptyInsteadOfDefault) RibState.Empty
        else findDefault() ?: RibState.Empty
    }

    private fun findDefault(): RibState? {
        for (state in stateList) {
            if (state.isDefault) {
                return state
            }
        }
        return null
    }

    fun saveTo(bundle: Bundle?): Bundle {
        val b = bundle ?: Bundle()
        if (state != null) {
            b.putString(uniqueId, state?.id)
        }
        return b
    }
}
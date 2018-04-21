package com.dgse.my_rib_app.root.logged_out.number_password_entrance

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dgse.my_rib_app.R
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Builder for the {@link NumberPasswordEntranceScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class NumberPasswordEntranceBuilder(
        dependency: ParentComponent
) : ViewBuilder<NumberPasswordEntranceView, NumberPasswordEntranceRouter, NumberPasswordEntranceBuilder.ParentComponent>(dependency) {

    /**
     * Builds a new [NumberPasswordEntranceRouter].
     *
     * @param parentViewGroup parent view group that this router's view will be added to.
     * @return a new [NumberPasswordEntranceRouter].
     */
    fun build(parentViewGroup: ViewGroup): NumberPasswordEntranceRouter {
        val view = createView(parentViewGroup)
        val interactor = NumberPasswordEntranceInteractor()
        val component = DaggerNumberPasswordEntranceBuilder_Component.builder()
                .parentComponent(dependency)
                .view(view)
                .interactor(interactor)
                .build()
        return component.numberPasswordEntranceRouter()
    }

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup) =
            inflater.inflate(
                    R.layout.entrance_number_password,
                    parentViewGroup,
                    false
            ) as NumberPasswordEntranceView

    interface ParentComponent {
        // TODO: Define dependencies required from your parent interactor here.
    }

    @dagger.Module
    abstract class Module {

        @NumberPasswordEntranceScope
        @Binds
        internal abstract fun presenter(view: NumberPasswordEntranceView): NumberPasswordEntranceInteractor.NumberPasswordEntrancePresenter

        @dagger.Module
        companion object {

            @NumberPasswordEntranceScope
            @Provides
            @JvmStatic
            internal fun router(
                    component: Component,
                    view: NumberPasswordEntranceView,
                    interactor: NumberPasswordEntranceInteractor): NumberPasswordEntranceRouter {
                return NumberPasswordEntranceRouter(view, interactor, component)
            }
        }

        // TODO: Create provider methods for dependencies created by this Rib. These should be static.
    }

    @NumberPasswordEntranceScope
    @dagger.Component(modules = [(Module::class)], dependencies = [(ParentComponent::class)])
    interface Component : InteractorBaseComponent<NumberPasswordEntranceInteractor>, BuilderComponent {

        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: NumberPasswordEntranceInteractor): Builder

            @BindsInstance
            fun view(view: NumberPasswordEntranceView): Builder

            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun numberPasswordEntranceRouter(): NumberPasswordEntranceRouter
    }

    @Scope
    @Retention(AnnotationRetention.BINARY)
    internal annotation class NumberPasswordEntranceScope

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    internal annotation class NumberPasswordEntranceInternal
}

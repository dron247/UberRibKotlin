package com.dgse.my_rib_app.root.logged_out

import com.dgse.my_rib_app.root.RootView
import com.dgse.my_rib_app.root.logged_out.number_password_entrance.NumberPasswordEntranceBuilder
import com.uber.rib.core.Builder
import com.uber.rib.core.EmptyPresenter
import com.uber.rib.core.InteractorBaseComponent
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Scope

class LoggedOutBuilder(
        dependency: ParentComponent
) : Builder<LoggedOutRouter, LoggedOutBuilder.ParentComponent>(dependency) {

    /**
     * Builds a new [LoggedOutRouter].
     *
     * @return a new [LoggedOutRouter].
     */
    fun build(): LoggedOutRouter {
        val interactor = LoggedOutInteractor()
        val component = DaggerLoggedOutBuilder_Component.builder()
                .parentComponent(dependency)
                .interactor(interactor)
                .build()

        return component.loggedOutRouter()
    }

    interface ParentComponent {
        fun rootView(): RootView
    }


    @dagger.Module
    abstract class Module {

        @dagger.Module
        companion object {

            @LoggedOutScope
            @Provides
            @JvmStatic
            internal fun presenter(): EmptyPresenter {
                return EmptyPresenter()
            }

            @LoggedOutScope
            @Provides
            @JvmStatic
            internal fun router(
                    component: Component,
                    interactor: LoggedOutInteractor,
                    rootView: RootView
            ): LoggedOutRouter {
                return LoggedOutRouter(
                        interactor,
                        component,
                        rootView,
                        NumberPasswordEntranceBuilder(component)
                )
            }

            // TODO: Create provider methods for dependencies created by this Rib. These methods should be static.
        }
    }


    @LoggedOutScope
    @dagger.Component(modules = [(Module::class)], dependencies = [(ParentComponent::class)])
    interface Component :
            InteractorBaseComponent<LoggedOutInteractor>,
            BuilderComponent,
            NumberPasswordEntranceBuilder.ParentComponent {

        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: LoggedOutInteractor): Builder

            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }

    }

    interface BuilderComponent {
        fun loggedOutRouter(): LoggedOutRouter
    }

    @Scope
    @Retention(AnnotationRetention.BINARY)
    internal annotation class LoggedOutScope


    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    internal annotation class LoggedOutInternal
}

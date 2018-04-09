package com.dgse.my_rib_app.root.intro

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dgse.my_rib_app.R
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.CLASS
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Builder for the {@link IntroScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class IntroBuilder(
        dependency: ParentComponent
) : ViewBuilder<IntroView, IntroRouter, IntroBuilder.ParentComponent>(dependency) {

    /**
     * Builds a new [IntroRouter].
     *
     * @param parentViewGroup parent view group that this router's view will be added to.
     * @return a new [IntroRouter].
     */
    fun build(parentViewGroup: ViewGroup): IntroRouter {
        val view = createView(parentViewGroup)
        val interactor = IntroInteractor()
        val component = DaggerIntroBuilder_Component.builder()
                .parentComponent(dependency)
                .view(view)
                .interactor(interactor)
                .build()
        return component.introRouter()
    }

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup) =
            inflater.inflate(R.layout.intro, parentViewGroup, false) as IntroView

    interface ParentComponent {
        fun listener(): IntroInteractor.Listener
    }

    @dagger.Module
    abstract class Module {

        @IntroScope
        @Binds
        internal abstract fun presenter(view: IntroView): IntroInteractor.IntroPresenter

        @dagger.Module
        companion object {

            @IntroScope
            @Provides
            @JvmStatic
            internal fun router(
                    component: Component,
                    view: IntroView,
                    interactor: IntroInteractor): IntroRouter {
                return IntroRouter(view, interactor, component)
            }
        }

        // TODO: Create provider methods for dependencies created by this Rib. These should be static.
    }

    @IntroScope
    @dagger.Component(modules = [(Module::class)], dependencies = [(ParentComponent::class)])
    interface Component : InteractorBaseComponent<IntroInteractor>, BuilderComponent {

        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: IntroInteractor): Builder

            @BindsInstance
            fun view(view: IntroView): Builder

            fun parentComponent(component: ParentComponent): Builder
            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun introRouter(): IntroRouter
    }

    @Scope
    @Retention(CLASS)
    internal annotation class IntroScope

    @Qualifier
    @Retention(CLASS)
    internal annotation class IntroInternal
}

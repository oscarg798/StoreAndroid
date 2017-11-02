package co.com.core.use_cases.categories

import co.com.core.Category
import co.com.data.repositories.RepositoryFactory
import com.core.usecases.SingleUseCase
import io.reactivex.Scheduler
import io.reactivex.Single

/**
 * Created by oscarg798 on 10/11/17.
 */
class GetCategoriesUseCase(mSubscribeOnScheduler: Scheduler,
                           mObserverOnScheduler: Scheduler) :
        SingleUseCase<List<Category>, Void?>(mSubscribeOnScheduler, mObserverOnScheduler) {


    override fun buildUseCase(params: Void?): Single<List<Category>> {
        return Single.fromObservable(RepositoryFactory.instance.mCategoriesRepository.getCategories())
                .map { apiCategories ->
                    apiCategories.map { apiCategory ->
                        Category(apiCategory.name, apiCategory.description, apiCategory.avatar, apiCategory.uuid)
                    }
                }
    }


}


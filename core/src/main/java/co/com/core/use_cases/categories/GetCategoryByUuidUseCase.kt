package co.com.core.use_cases.categories

import co.com.core.Category
import co.com.data.repositories.RepositoryFactory
import com.core.usecases.SingleUseCase
import io.reactivex.Scheduler
import io.reactivex.Single

/**
 * Created by oscarg798 on 10/18/17.
 */
class GetCategoryByUuidUseCase(mSubscribeOnScheduler: Scheduler,
                               mObserverOnScheduler: Scheduler) :
        SingleUseCase<Category, String>(mSubscribeOnScheduler, mObserverOnScheduler) {
    override fun buildUseCase(params: String): Single<Category> {
        return Single.create { emitter ->

            val dbCategory = RepositoryFactory.instance
                    .mCategoriesRepository.getCategory(params)

            emitter.onSuccess(Category(dbCategory.name, dbCategory.description,
                    dbCategory.avatar, dbCategory.uuid,dbCategory.store))


        }
    }
}
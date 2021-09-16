package com.datechnologies.androidtest.domain.interactor;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Basic
 *
 * @param <T> Object to be returned from the use case, in RxJava observable form
 * @param <Params> data sent from presentation layer to complete the use case
 */
public abstract class BaseUseCase<T, Params> {

    protected final CompositeDisposable mDisposables = new CompositeDisposable();

    protected void addDisposable(Disposable disposable) {
        if (disposable != null) {
            mDisposables.add(disposable);
        }
    }

    public void dispose(){
        if (!mDisposables.isDisposed()){
            mDisposables.dispose();
        }
    }

    /**
     *
     * @param params parameters required to make repository query/operation
     * @return RxJava single of the desired type
     */
    protected abstract Single<T> build(Params params);

    public void execute(DisposableSingleObserver<T> observer, Params params){
        final Single<T> single = this.build(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        addDisposable(single.subscribeWith(observer));
    }
}

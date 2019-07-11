package com.slb.frame.http2.rxjava;

import com.slb.frame.http2.exception.ExceptionEngine;

import rx.Observable;
import rx.functions.Func1;

/**
 * 描述：
 * Created by Lee
 * on 2017/1/24.
 */
public class HttpResponseFunc<T> implements Func1<Throwable, Observable<T>> {
    @Override
    public Observable<T> call(Throwable throwable) {
        return Observable.error(ExceptionEngine.analysisExcetpion(throwable));
    }
}

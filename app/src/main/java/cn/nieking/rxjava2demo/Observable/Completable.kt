package cn.nieking.rxjava2demo.Observable

import io.reactivex.Completable
import io.reactivex.CompletableEmitter
import io.reactivex.CompletableObserver
import io.reactivex.CompletableOnSubscribe
import io.reactivex.disposables.Disposable

/**
 * 如果不需要处理 onNext 可以使用 Completable ，它只有 onComplete 和 onError 两个事件
 */
class Completable {

    init {
        Completable.create(object : CompletableOnSubscribe {
            override fun subscribe(emitter: CompletableEmitter) {
                emitter.onComplete() // 单一处理
            }
        }).subscribe(object : CompletableObserver {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onComplete() {

            }

            override fun onError(e: Throwable) {

            }
        })
    }
}
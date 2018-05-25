package cn.nieking.rxjava2demo.Observable

import io.reactivex.Maybe
import io.reactivex.MaybeEmitter
import io.reactivex.MaybeObserver
import io.reactivex.MaybeOnSubscribe
import io.reactivex.disposables.Disposable

/**
 * 如果可能发送一个数据或者不会发送任何数据，这时可以使用 Maybe ，类似 Single 和 Completabel 的混合体
 */
class Maybe {

    init {
        Maybe.create(object : MaybeOnSubscribe<String> {
            override fun subscribe(emitter: MaybeEmitter<String>) {
                emitter.onSuccess("test") // 发送一个数据时或者 onError 时，不需要在调用 onComplete（调用也不会触发器 onComplete 回调
                emitter.onComplete() // 不需要发送数据时
            }
        }).subscribe(object : MaybeObserver<String> {
            override fun onSuccess(t: String) {
                // 发送一个数据时，不会触发 onComplete
            }

            override fun onComplete() {
                // 无数据时，调用 onComplete
            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {

            }

        })
    }
}
package cn.nieking.rxjava2demo.Observable

import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.SingleObserver
import io.reactivex.SingleOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiConsumer

/**
 * 如果只有一个 onNext 事件，接着就触发 onComplete 或者 onError ，可使用 Single
 * Single 只发送一次消息（所以不存在背压问题）
 */
class Single {

    init {
        val single = Single.create(object : SingleOnSubscribe<String> {
            override fun subscribe(emitter: SingleEmitter<String>) {
                emitter.onSuccess("test")
                emitter.onSuccess("test again") // 重复调用不会处理
            }
        })

        // 订阅观察者 SingleObserver
        single.subscribe(object : SingleObserver<String> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onSuccess(t: String) {
                // 相当于 onNext -> onComplete
            }

            override fun onError(e: Throwable) {

            }
        })

        single.unsubscribeOn(AndroidSchedulers.mainThread())

        // 使用 BiConsumer 简化 SingleObserver
        single.subscribe(object : BiConsumer<String, Throwable> {
            override fun accept(t: String, u: Throwable) {
                // 处理 onSuccess onError
            }
        })

        single.unsubscribeOn(AndroidSchedulers.mainThread())

        // Single 可转换成 Flowable 和 Observable
        single.toFlowable()
        single.toObservable()
    }
}
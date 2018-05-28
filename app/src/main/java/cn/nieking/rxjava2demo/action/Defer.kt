package cn.nieking.rxjava2demo.action

import android.util.Log
import io.reactivex.Flowable
import org.reactivestreams.Publisher
import java.util.concurrent.Callable

/**
 * 当被观察者被多个观察者订阅时都重新创建被观察者，则可使用defer操作符
 */
class Defer {

    init {
        val flowable = Flowable.defer(object : Callable<Publisher<String>> {
            override fun call(): Publisher<String> {
                return Flowable.just("1", "2")
            }
        })

        // 订阅第一个观察者
        flowable.subscribe { str -> Log.i("tag", str) }
        // 订阅第二个观察者
        flowable.subscribe { str -> Log.i("tag", str) }
        // 第一个观察者执行完后才创建第二个被观察者并订阅，之后再发送事件消息
    }
}
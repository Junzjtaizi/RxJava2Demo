package cn.nieking.rxjava2demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import org.reactivestreams.Subscription

class DemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        // 创建观察者
        val subscriber = object : FlowableSubscriber<String> {

            override fun onSubscribe(s: Subscription) {
                // 订阅时候的操作
                s.request(Long.MAX_VALUE)
            }

            override fun onNext(t: String?) {
                // onNext 事件处理
                Log.i("tag", t)
            }

            override fun onError(t: Throwable?) {
                // onError 事件处理
            }

            override fun onComplete() {
                // onComplete 事件处理
            }
        }

        // 创建被观察者
        val flowable = Flowable.create(object : FlowableOnSubscribe<String> {

            override fun subscribe(emitter: FlowableEmitter<String>) {
                // 订阅观察者时的操作
                emitter.onNext("test 1")
                emitter.onNext("test 2")
                emitter.onComplete()
            }

        }, BackpressureStrategy.BUFFER)

        // 订阅观察者
        flowable.subscribe(subscriber)

        flowable.unsubscribeOn(AndroidSchedulers.mainThread())

        // 如果观察者只需要处理 onNext 那么可以使用 Consumer
        flowable.subscribe(object : Consumer<String> {
            override fun accept(t: String?) {
                Log.i("Consumer", t)
            }
        })

        flowable.unsubscribeOn(AndroidSchedulers.mainThread())



        // 完整示例
        flowable.subscribe(
                object : Consumer<String> { // onNext
                    override fun accept(t: String?) {

                    }
                },
                object : Consumer<Throwable> { // onError
                    override fun accept(t: Throwable?) {

                    }
                },
                object : Action { // onComplete
                    override fun run() {

                    }
                },
                object : Consumer<Subscription> { // onSubscribe
                    override fun accept(t: Subscription?) {

                    }
                }
        )

        flowable.unsubscribeOn(AndroidSchedulers.mainThread())



        // 被观察者 Observable
        val observable = Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(emitter: ObservableEmitter<String>) {
                // 订阅观察者时的操作
                emitter.onNext("test 1")
                emitter.onNext("test 2")
                emitter.onComplete()
            }
        })

        // Observable 不支持订阅 Subscriber 观察者，需要使用 Observer 作为观察者
        val observer = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                // 订阅时无需 request
            }

            override fun onNext(t: String) {
                // onNext
            }

            override fun onError(e: Throwable) {
                // onError
            }

            override fun onComplete() {
                // onComplete
            }
        }

        // 订阅
        observable.subscribe(observer)


        // Observable 和 Flowable

        // Observable 不需要背压和请求资源操作
        //            不支持背压
        //            不超过 1000 个元素、随事件流逝基本不会 OOM
        //            不支持 Java Steam
        //            开销较低
        // Flowable   超过 10000 个元素
        //            闪存 I/O
        //            网络 I/O

        // BackPressure 背压
        // 即被观察者的产生速度大于观察者消费的速度而导致的问题
    }

}

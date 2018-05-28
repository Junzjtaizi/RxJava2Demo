package cn.nieking.rxjava2demo.action

import android.util.Log
import io.reactivex.Flowable

/**
 * 不发送任何数据，也不会触发观察者的任何回调
 */
class Never {

    init {

        Flowable.never<Any>()
                .subscribe(
                        { obj -> Log.i("tag", obj.toString()) },
                        { e -> Log.i("tag", e.toString()) })
                { Log.i("tag", "complete") }
    }
}
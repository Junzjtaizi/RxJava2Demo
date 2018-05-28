package cn.nieking.rxjava2demo.action

import android.util.Log
import io.reactivex.Flowable

/**
 * empty 不发送任何数据，直接发送 onComplete 事件
 */
class Empty {

    init {

        Flowable.empty<Any>()
                .subscribe(
                        { obj -> Log.i("tag", "next" + obj.toString()) },
                        { e -> Log.i("tag", e.toString()) })
                { Log.i("tag", "complete") }
    }
}
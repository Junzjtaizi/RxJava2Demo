package cn.nieking.rxjava2demo.action

import android.util.Log
import io.reactivex.Flowable

/**
 * 如果对于只是发送几个数据，可以使用 just 操作符
 */
class Just {

    init {
        Flowable.just("test1", "test2")
                .subscribe { str -> Log.i("tag", str) }
    }
}
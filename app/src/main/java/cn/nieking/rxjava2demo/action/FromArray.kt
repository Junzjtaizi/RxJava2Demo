package cn.nieking.rxjava2demo.action

import android.util.Log
import io.reactivex.Flowable

/**
 * just 操作符最多只能接收 10 个参数，但其实超过 2 个参数时会调用 fromArray 操作符，它可以接收任意长度的数据数组
 */
class FromArray {

    init {
        Flowable.fromArray(1, 2, 3, 4, 5)
                .subscribe { i -> Log.i("tag", i.toString()) }
    }
}
package cn.nieking.rxjava2demo.action

import android.util.Log
import io.reactivex.Flowable

/**
 * 从第一个数开始直接输出到最后一个数，中间没有延迟
 */
class Range {

    init {
        Flowable.range(0, 5)
                .subscribe { x -> Log.i("tag", x.toString()) }

        Flowable.rangeLong(Int.MAX_VALUE.toLong(), 5L)
                .subscribe { x -> Log.i("tag", x.toString()) }
    }
}
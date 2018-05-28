package cn.nieking.rxjava2demo.action

import android.util.Log
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

class Interval {

    init {
        // 延迟2秒后，每1秒发射一次数据
        Flowable.interval(2, 1, TimeUnit.SECONDS)
                .subscribe { x -> Log.i("tag", x.toString()) }

        // 从1开始，发射10个数据，第一次发射时延迟2秒，之后每1秒发射一个数据
        Flowable.intervalRange(1, 10, 2, 1, TimeUnit.SECONDS)
                .subscribe { x -> Log.i("tag", x.toString()) }
    }
}
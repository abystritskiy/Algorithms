import java.util.ArrayList
import java.util.Arrays

object AmznDemo {
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    private fun optimalUtilization(
            deviceCapacity: Int,
            foregroundAppList: List<List<Int>>,
            backgroundAppList: List<List<Int>>): List<List<Int>> {
        val out = ArrayList<List<Int>>()
        var max = 0

        for (i in foregroundAppList.indices) {
            val fApp = foregroundAppList[i]
            val fAppMem = fApp[1]
            val fAppId = fApp[0]

            for (j in backgroundAppList.indices) {
                val bApp = backgroundAppList[j]
                val bAppMem = bApp[1]
                val bAppId = bApp[0]

                if (bAppMem + fAppMem <= deviceCapacity) {
                    if (bAppMem + fAppMem > max) {
                        max = bAppMem + fAppMem

                        // we found higher utilization -
                        // erase previously stored results
                        out.clear()

                        val pair = ArrayList<Int>()
                        pair.add(fAppId)
                        pair.add(bAppId)
                        out.add(pair)
                    } else if (bAppMem + fAppMem == max) {

                        // we found another pair with tje utilization -
                        // erase previously stored results

                        val pair = ArrayList<Int>()
                        pair.add(fAppId)
                        pair.add(bAppId)
                        out.add(pair)
                    }
                }
            }
        }

        return out
    }


    @JvmStatic
    fun main(args: Array<String>) {
        val deviceCapacity = 20

        val foregroundAppList = ArrayList<List<Int>>()
        val f1 = ArrayList<Int>()
        f1.add(1)
        f1.add(8)
        val f2 = ArrayList<Int>()
        f2.add(2)
        f2.add(7)
        val f3 = ArrayList<Int>()
        f3.add(3)
        f3.add(14)
        foregroundAppList.add(f1)
        foregroundAppList.add(f2)
        foregroundAppList.add(f3)


        val backgroundAppList = ArrayList<List<Int>>()
        val b1 = ArrayList<Int>()
        b1.add(1)
        b1.add(8)
        val b2 = ArrayList<Int>()
        b2.add(2)
        b2.add(7)
        val b3 = ArrayList<Int>()
        b3.add(3)
        b3.add(14)
        backgroundAppList.add(b1)
        backgroundAppList.add(b2)
        backgroundAppList.add(b3)

        println(optimalUtilization(deviceCapacity, foregroundAppList, backgroundAppList))
    }
}

package com.smarttoolfactory.datastructuresandalgorithmsplayground

/*

Host Tracker: Server names consist of an alphabetic host type (e.g. "apibox")
concatenated with the server number, with server numbers allocated as before
(so, "apibox:1", "apibox:2", etc. are valid hostnames). Write a name tracking class with two
operations, allocate(hostType) and deallocate(hostName). The former should reserve and
return the next available hostname,
while the latter should release that hostname back into the pool.

For example:
>> tracker = Tracker()
>> tracker.allocate("apibox")
"apibox:1"
>> tracker.allocate("apibox")
"apibox:2"
>> tracker.deallocate("apibox:1")
>> tracker.allocate("apibox")
"apibox:1"
>> tracker.allocate("sitebox")
"sitebox:1"

 */
fun main() {
    val tracker = Tracker()
    val output1 = tracker.allocate("apibox") // appbox:1
    val output2 = tracker.allocate("apibox")
    tracker.deallocate("apibox:1")

    val output3 = tracker.allocate("apibox")
    val output4 = tracker.allocate("sitebox")

    println("OUTPUT1: $output1, OUTPUT2: $output2, OUTPUT3: $output3, OUTPUT4: $output4")

}

data class Server(val serverNumber: Int, val name: String)

class Tracker {

    private val servers = linkedSetOf<Server>()

    fun allocate(hostType: String): String {

        val server = servers.find {
            it.name == hostType
        }

        val serverNew = if (server == null) {
            Server(1, hostType)
        } else {

            val ids: List<Int> = servers.map {
                it.serverNumber
            }

            var currentId = 1

            ids.forEach {
                // 1 3,4
                if (currentId == it) {
                    currentId++
                }else {
                   return@forEach
                }
            }

            Server(currentId, hostType)
        }

        servers.add(serverNew)
        return "${serverNew.name}:${serverNew.serverNumber}"
    }

    fun deallocate(hostName: String) {

        // "apibox:1"
        val delimiter = hostName.indexOf(":")
        val serverNumber = hostName.substring(delimiter + 1).toInt()
        val hostType = hostName.substring(0, delimiter)

        val server = servers.find { host ->
            host.name == hostType && host.serverNumber == serverNumber
        }

        server?.let { it ->
            servers.remove(it)
        }
    }
}


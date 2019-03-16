import beans.User

fun main(args: Array<String>) {
    println("Let's learn some basics of (っ◕‿◕)っ Kotlin ")

    // Creating a bean in one line, see User.kt

    val users = listOf(User("Jacob", 31), User("Jane", 29))

    users.forEach { println("Printing user's info: $it") }
}
package com.ansgar.harbor.model

sealed class Tree<out T>

object Empty : Tree<Nothing>() {
    override fun toString() = "Empty"
}

data class Node<T>(
    val value: T,
    val left: Tree<T> = Empty,
    val right: Tree<T> = Empty
) : Tree<T>()

val tree = Node(
    42,
    Empty, // Nothing can be based of any type, which make this doable.
    Node(62)
)

// Nothing can also been used in control flow
fun fail(): Nothing {
    throw RuntimeException("Something went wrong")
}

fun nullableFun(): Boolean? = null

// Since return Nothing on [fail] mean no instance will be returned
// the type of data will be safely transmit as boolean automatically
val data = nullableFun() ?: fail ()
val isVisible = data
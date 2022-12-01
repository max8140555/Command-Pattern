package com.max.commandpattern

class Order {

    private val beverageList = mutableListOf<String>()

    fun addBeverage(beverage: String) {
        beverageList.add(beverage)
    }

    fun removeBeverage(beverage: String) {
        beverageList.remove(beverage)
    }

    fun printCreateOrder() {
        println("Create Order: $beverageList")
    }

    fun printCancelOrder() {
        println("Cancel Order: $beverageList")
    }
}

interface OrderCommand {
    fun execute()
    fun undo()
}

class AddBeverageCommand(val order: Order, val beverage: String): OrderCommand {
    override fun execute() = order.addBeverage(beverage)
    override fun undo() {}
}

class RemoveBeverageCommand(val order: Order, val beverage: String): OrderCommand {
    override fun execute() = order.removeBeverage(beverage)
    override fun undo() {}
}

class PrintOrderCommand(val order: Order): OrderCommand {
    override fun execute() = order.printCreateOrder()
    override fun undo() = order.printCancelOrder()
}

class CommandProcessor {

    private val currentQueue = mutableListOf<OrderCommand>()

    private var prevQueue: List<OrderCommand> = listOf()

    fun addToQueue(orderCommand: OrderCommand): CommandProcessor =
        apply {
            currentQueue.add(orderCommand)
        }

    fun processCommands() {
        currentQueue.forEach { it.execute() }
        prevQueue = currentQueue.toList()
        currentQueue.clear()
    }

    fun undoProcessCommands() {
        prevQueue.forEach { it.undo() }
        prevQueue = listOf()
    }
}
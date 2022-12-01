package com.max.commandpattern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val order = Order()
        val commandProcessor = CommandProcessor()
        commandProcessor
            .addToQueue((AddBeverageCommand(order, "Black Tea")))
            .addToQueue(AddBeverageCommand(order, "Milk Tea"))
            .addToQueue(RemoveBeverageCommand(order, "Milk Tea"))
            .addToQueue(AddBeverageCommand(order, "Green Tea"))
            .addToQueue(PrintOrderCommand(order))

        commandProcessor.processCommands()

        commandProcessor.undoProcessCommands()
    }
}
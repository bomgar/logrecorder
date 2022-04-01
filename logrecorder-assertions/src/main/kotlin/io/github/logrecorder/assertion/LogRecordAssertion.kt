/*
 * Copyright 2017-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.logrecorder.assertion

import io.github.logrecorder.api.LogRecord
import io.github.logrecorder.assertion.blocks.AssertionBlock
import io.github.logrecorder.assertion.blocks.Contains
import io.github.logrecorder.assertion.blocks.ContainsExactly
import io.github.logrecorder.assertion.blocks.ContainsInOrder
import io.github.logrecorder.assertion.blocks.IsEmpty
import io.github.logrecorder.assertion.blocks.MessagesAssertionBlock

@DslContext
class LogRecordAssertion(
    private val logRecord: LogRecord
) {

    private val blocks = mutableListOf<AssertionBlock>()

    fun addAssertionBlock(block: AssertionBlock) {
        blocks.add(block)
    }

    private fun check() {
        blocks.forEach { it.check(logRecord) }
    }

    companion object {
        @JvmStatic
        fun assertThat(logRecord: LogRecord, block: LogRecordAssertion.() -> Unit) {
            LogRecordAssertion(logRecord).apply(block).check()
        }
    }
}

/**
 * Define an [IsEmpty] assertion block.
 */
fun LogRecordAssertion.isEmpty() =
    addAssertionBlock(IsEmpty())

/**
 * Define a [Contains] assertion block.
 */
fun LogRecordAssertion.contains(block: MessagesAssertionBlock.() -> Unit) =
    addAssertionBlock(Contains().apply(block))

/**
 * Define a [ContainsExactly] assertion block.
 */
fun LogRecordAssertion.containsExactly(block: MessagesAssertionBlock.() -> Unit) =
    addAssertionBlock(ContainsExactly().apply(block))

/**
 * Define a [ContainsInOrder] assertion block.
 */
fun LogRecordAssertion.containsInOrder(block: MessagesAssertionBlock.() -> Unit) =
    addAssertionBlock(ContainsInOrder().apply(block))
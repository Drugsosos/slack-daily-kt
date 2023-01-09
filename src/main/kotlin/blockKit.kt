import com.slack.api.model.Attachment
import com.slack.api.model.block.ContextBlock
import com.slack.api.model.block.DividerBlock
import com.slack.api.model.block.HeaderBlock
import com.slack.api.model.block.LayoutBlock
import com.slack.api.model.block.SectionBlock
import com.slack.api.model.block.composition.MarkdownTextObject
import com.slack.api.model.block.composition.PlainTextObject


/**
 * Block kit templates
 */
class Blocks {

    /**
     * Attachment unit to be sent as a report in the channel
     *
     * @param header Question text (no markdown)
     * @param body Answer text (has markdown)
     * @param color Color of the strip on the left side of the block
     * @return Report block unit
     */
    fun reportAttachment(
        header: String,
        body: String,
        color: String
    ): Attachment {
        return Attachment.builder().blocks(
            listOf<LayoutBlock>(
                HeaderBlock.builder().text(
                    PlainTextObject.builder().text(header).build()
                ).build(),
                SectionBlock.builder().text(
                    MarkdownTextObject.builder().text(body).build()
                ).build()
            )
        ).color(color).build()
    }

    /**
     * Set of blocks to be sent to users on daily start
     *
     * @param header Greetings above divider
     * @param body Something inspirational (yup, daily is hard, cheer 'em up)
     * @param firstQuestion First question from question list
     * @return Blocks to be sent on daily start
     */
    fun startDaily(
        header: String,
        body: String,
        firstQuestion: String
    ): List<LayoutBlock> {
        return listOf(
            ContextBlock.builder().elements(
                listOf(
                    MarkdownTextObject.builder().text(header).build()
                )
            ).build(),
            DividerBlock(),
            SectionBlock.builder().text(
                MarkdownTextObject.builder().text(body).build()
            ).build(),
            SectionBlock.builder().text(
                MarkdownTextObject.builder().text(">${firstQuestion}").build()
            ).build()
        )
    }

    /**
     * Set of blocks to be sent on daily end (all questions ended)
     *
     * @param header Show 'em your gratitude here and tag 'em
     * @param body Something inspirational again (they have all day ahead)
     * @param footer Where to find a report (add channel link here)
     * @return Blocks to be sent on daily end
     */
    fun endDaily (
        header: String,
        body: String,
        footer: String
    ): List<LayoutBlock> {
        return listOf(
            SectionBlock.builder().text(
                MarkdownTextObject.builder().text(header).build()
            ).build(),
            SectionBlock.builder().text(
                MarkdownTextObject.builder().text(body).build()
            ).build(),
            DividerBlock(),
            ContextBlock.builder().elements(
                listOf(
                    MarkdownTextObject.builder().text(footer).build()
                )
            ).build()
        )
    }

    /**
     * Blocks constructor for get questions command
     *
     * @param header Header of the list block
     * @param parsedList List w/ all objects to be parsed
     * @return List of blocks w/ parsed data
     */

    fun numberedList (
        header: String,
        parsedList: List<String>
    ): List<LayoutBlock> {
        val blocks = mutableListOf<LayoutBlock>(
            HeaderBlock.builder().text(
                PlainTextObject.builder().text(header).build()
            ).build(),
            DividerBlock()
        )

        for ((idx, unit) in parsedList.withIndex()) {
            blocks.add(
                SectionBlock.builder().text(
                    MarkdownTextObject.builder().text("${intToSlackEmoji(idx)}/t$unit").build()
                ).build()
            )
        }

        return blocks
    }

    /**
     * Make errors and successes look beautiful
     *
     * @param status Choose status (changes emoji in blocks)
     * @param header Error/Success summary
     * @param body Error/Success main message (Optional)
     * @return Status message block
     */
    fun statusMessage(
        status: MessageStatus,
        header: String,
        body: String? = null
    ): List<LayoutBlock> {
        val blocks = mutableListOf<LayoutBlock>(
            HeaderBlock.builder().text(
                PlainTextObject.builder().text("${status.asEmoji}\t$header").build()
            ).build(),
            DividerBlock()
        )

        if (!body.isNullOrEmpty()) {
            blocks.add(
                SectionBlock.builder().text(
                    MarkdownTextObject.builder().text(body).build()
                ).build()
            )
        }

        return blocks
    }
}

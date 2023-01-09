import com.slack.api.model.Attachment
import com.slack.api.model.block.ContextBlock
import com.slack.api.model.block.DividerBlock
import com.slack.api.model.block.HeaderBlock
import com.slack.api.model.block.LayoutBlock
import com.slack.api.model.block.SectionBlock
import com.slack.api.model.block.composition.MarkdownTextObject
import com.slack.api.model.block.composition.PlainTextObject


class Block {
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

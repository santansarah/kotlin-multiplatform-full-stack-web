import emotion.sheet.StyleSheet
import kotlinx.js.ReadonlyArray
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLStyleElement

/*
object GlobalStyles : StyleSheet {
    private val styles = CSSBuilder().apply {
        body {
            */
/**
             * Gives the body the following css globally (there is only ever one body)
             *
             *  - Sets the maxWidth with to the size available for the content.
             *  - Adds Auto Margin to the body allowing everything to be centered.
             *  - Creates a padding of 100px all around the body
             *//*

            maxWidth = LinearDimension.maxContent
            margin(LinearDimension.auto)
            padding(100.px)
            */
/**
             * We can visualise this much better if we give the background a grey colour.
             *//*

            backgroundColor = Color.darkGray
        }
    }
    override val container: HTMLElement
        get() = TODO("Not yet implemented")
    override val key: String
        get() = TODO("Not yet implemented")
    override val nonce: String?
        get() = TODO("Not yet implemented")
    override val tags: ReadonlyArray<HTMLStyleElement>
        get() = TODO("Not yet implemented")

    override fun flush() {
        TODO("Not yet implemented")
    }

    override fun insert(rule: String) {
        TODO("Not yet implemented")
    }
}
*/

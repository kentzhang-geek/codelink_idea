package com.github.kentzhanggeek.codelinkidea
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.ide.CopyPasteManager
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.LocalFileSystem
import java.awt.datatransfer.DataFlavor

class CodeLinkOpen : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {        // Get the clipboard content
        val clipboardContent = CopyPasteManager.getInstance().contents
        val clipboardString = if (clipboardContent != null && clipboardContent.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            clipboardContent.getTransferData(DataFlavor.stringFlavor) as String
        } else {
            null
        }
        
        val url = Messages.showInputDialog(e.project, "Please enter the Code Link", "Code Link", null, clipboardString, null)
        if (url != null) {
            // Use a regex to extract the file path and line number
            val regex = Regex("""codelink://(.*):(\d+)""")
            val matchResult = regex.matchEntire(url)

        if (matchResult != null) {
            val (filePath, lineNumber) = matchResult.destructured

            // Use VirtualFileManager to get a VirtualFile instance for the file path
            val virtualFile = LocalFileSystem.getInstance().findFileByPath(filePath)

            if (virtualFile != null && e.project != null) {
                // Use FileEditorManager to open the file in the editor
                val fileEditorManager = FileEditorManager.getInstance(e.project!!)
                val textEditor = fileEditorManager.openTextEditor(com.intellij.openapi.fileEditor.OpenFileDescriptor(e.project!!, virtualFile, lineNumber.toInt() - 1), true)

                // Use FileEditorManager to navigate to the line number in the file
                if (textEditor != null) {
                    val document = textEditor.document
                    val lineStartOffset = document.getLineStartOffset(lineNumber.toInt() - 1)
                    textEditor.caretModel.moveToOffset(lineStartOffset)

                    // Scroll the view to the cursor's new position
                    textEditor.scrollingModel.scrollToCaret(com.intellij.openapi.editor.ScrollType.CENTER)
                }
            }
        }
    }
}
}
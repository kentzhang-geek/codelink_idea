package com.github.kentzhanggeek.codelinkidea
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ide.CopyPasteManager
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import java.awt.datatransfer.StringSelection

class CodeLinkGenerate : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project
        if (project != null) {
            val editor = FileEditorManager.getInstance(project).selectedTextEditor
            if (editor != null) {
                val document = editor.document
                val virtualFile = FileDocumentManager.getInstance().getFile(document)
                if (virtualFile != null) {
                    val lineNumber = document.getLineNumber(editor.caretModel.offset) + 1
                    val filePath = virtualFile.path
                    val url = "codelink://$filePath:$lineNumber"

                    // Copy the URL to the clipboard
                    val clipboard = CopyPasteManager.getInstance()
                    clipboard.setContents(StringSelection(url))
                }
            }
        }
    }
}
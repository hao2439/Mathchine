package com.mathchine.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * 这是整个应用唯一且最难的组件。
 * 它的职责：接收形如 "$1+1$" 或是 "$$ \int x dx $$" 的纯文本 LaTeX，并将其无损渲染。
 * 这里采用的是极简单占位（可以利用原生或WebView在后期实现）。
 */
@Composable
fun LatexView(
    latexText: String,
    modifier: Modifier = Modifier
) {
    // 方案一：使用普通文本作为占位
    // Text(text = latexText, modifier = modifier.padding(16.dp))
    
    // 方案二：嵌入 WebView 作为简易沙盒去跑 MathJax
    /*
    AndroidView(
        factory = {
            WebView(it).apply {
                settings.javaScriptEnabled = true
                val html = """
                    <html>
                    <head><script type="text/javascript" id="MathJax-script" async src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script></head>
                    <body>$latexText</body>
                    </html>
                """
                loadDataWithBaseURL(null, html, "text/html", "UTF-8", null)
            }
        },
        modifier = modifier.fillMaxWidth()
    )
    */
    
    Text(
        text = "【LaTeX Component 待接入】\n$latexText",
        modifier = modifier.fillMaxWidth()
    )
}

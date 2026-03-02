# 数学题目数据包生成脚本

这是一个极简的 Python 脚本，用于将结构化的数学题目数据（例如 JSON 格式）转换为可以直接供移动端使用的离线数据包（SQLite 数据库文件，`.db` 格式）。

## 依赖 (Dependencies)
- `python 3.6+`
- `sqlite3` (内置)
- `json` (内置)

## 数据输入格式示例 (data.json)
```json
[
  {
    "uuid": "asmd_001",
    "category": "四则运算",
    "question": "$1 + 1 = $",
    "options": "[\"1\", \"2\", \"3\", \"4\"]",
    "correct_option_index": 1,
    "explanation": "$1 + 1 = 2$"
  },
  {
    "uuid": "asmd_002",
    "category": "四则运算",
    "question": "$2 \\times 3 = $",
    "options": "[\"4\", \"5\", \"6\", \"7\"]",
    "correct_option_index": 2,
    "explanation": "$2 \\times 3 = 6$"
  }
]
```

## 使用方法

1. 准备你的题库，遵循 `data.json` 的格式。
2. 运行脚本：
   ```bash
   python generate_pack.py
   ```
3. 脚本执行完成后，将在同一目录下生成一个名如何 `math_pack.db` 的文件，这就是给移动端用的离线数据包。

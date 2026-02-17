# Mathchine

## 📱 项目概述

**Mathchine** 是我的首个练手项目，旨在通过一个简单的数学练习应用来提升我的 AI 开发和 Android 开发的技能。该应用将提供多难度等级的数学题目，帮助用户在轻松愉快的环境中提升数学能力。

### 核心功能
- 🎯 多难度等级的数学题目（简单、中等、困难）
- 📊 用户答题统计和成绩追踪
- 🎨 简洁美观的 UI 界面
- ⚡ 实时题目加载和答案验证

---

## 功能设计

Version 0.1 的核心功能将包括：
### 1. 题目的保存和分类
- 使用Latex格式展示题目
- 使用数据库存储题目数据
- 按难度等级分类存储题目
- 支持题目检索和过滤
- 作为测试，先写入 1+1=? 的题目，并分类为四则运算


### 2. 用户交互功能
- 用户可以选择难度等级或者题目类型
- 用户可以提交答案并获得即时反馈（正确/错误）

### 3. UI设计与实现
- 设计简洁美观的用户界面
- 以安卓平台为应用载体，使用 Jetpack Compose 实现响应式 UI


---

test





## 📋 工作分解 (WBS)

### Phase 1️⃣: 项目初始化和环境配置

#### 1.1 开发环境搭建
- [ ] Android Studio 配置
- [ ] Gradle 构建系统配置
- [ ] 依赖库导入（Kotlin、Jetpack Compose、Retrofit 等）
- [ ] Git 仓库初始化和管理

#### 1.2 后端环境搭建
- [ ] 选择后端框架（Spring Boot / Node.js / Flask 等）
- [ ] 数据库选择和初始化（SQLite / PostgreSQL / MongoDB 等）
- [ ] 项目结构初始化
- [ ] API 文档框架（Swagger/OpenAPI）

---

### Phase 2️⃣: 后端开发

#### 2.1 题目生成模块 (Question Generator)
**职责：** 根据难度等级生成数学题目

- [ ] 设计题目数据模型
  ```
  Question {
    id: String (唯一ID)
    type: String (加减乘除)
    difficulty: Int (1=简单, 2=中等, 3=困难)
    num1: Int (第一个操作数)
    num2: Int (第二个操作数)
    operator: String (+, -, *, /)
    correctAnswer: Double (正确答案)
    createdAt: Timestamp
  }
  ```

- [ ] 实现题目生成算法
  - 简单(难度1): 个位数运算（1-9）
  - 中等(难度2): 两位数运算（10-99）
  - 困难(难度3): 三位数运算（100-999）+ 混合运算

- [ ] 实现随机数生成和题目多样性

#### 2.2 题目存储与分类模块 (Storage & Classification)
**职责：** 管理题目的存储、分类和检索

- [ ] 数据库表设计
  - `questions` 表：存储题目
  - `user_answers` 表：存储用户答题记录
  - `user_stats` 表：存储用户统计数据

- [ ] 实现题目存储接口
  - 保存题目到数据库
  - 按难度分类存储
  - 支持题目检索和过滤

- [ ] 缓存机制
  - 实现热点题目缓存
  - 缓存失效策略

#### 2.3 答案验证模块 (Answer Verification)
**职责：** 验证用户答案的正确性

- [ ] 实现答案计算引擎
  - 支持四则运算
  - 支持浮点数计算
  - 处理四舍五入问题

- [ ] 实现答案验证接口
  - 接收用户答案
  - 比对正确答案
  - 返回验证结果

#### 2.4 用户统计模块 (User Statistics)
**职责：** 跟踪和管理用户的练习数据

- [ ] 用户数据模型设计
  ```
  UserStats {
    userId: String
    totalAttempts: Int (总答题数)
    correctAnswers: Int (正确数)
    accuracy: Float (准确率)
    averageTime: Float (平均用时)
    difficultyStats: {
      easy: {correct, total, accuracy},
      medium: {correct, total, accuracy},
      hard: {correct, total, accuracy}
    }
  }
  ```

- [ ] 实现统计数据更新接口
- [ ] 实现统计数据查询接口

#### 2.5 API 设计与实现 (RESTful API)

| 端点 | 方法 | 功能 | 请求 | 响应 |
|------|------|------|------|------|
| `/api/questions` | GET | 获取题目 | `difficulty` (1-3) | `Question` 对象 |
| `/api/questions/{id}` | GET | 获取单个题目 | `questionId` | `Question` 对象 |
| `/api/verify` | POST | 验证答案 | `{questionId, userAnswer}` | `{isCorrect, correctAnswer}` |
| `/api/stats/{userId}` | GET | 获取用户统计 | `userId` | `UserStats` 对象 |
| `/api/stats/{userId}` | POST | 更新统计数据 | 答题记录 | 更新结果 |

- [ ] 实现所有 API 端点
- [ ] 添加请求验证和错误处理
- [ ] 添加日志记录

#### 2.6 数据库操作 (Database)
- [ ] 实现数据库连接池
- [ ] 实现 ORM / Query Builder
- [ ] 实现数据库迁移脚本
- [ ] 添加数据库索引优化查询性能

---

### Phase 3️⃣: 前端开发 (Android UI)

#### 3.1 项目结构设计
```
app/
├── src/main/
│   ├── java/com/example/mathchine/
│   │   ├── ui/
│   │   │   ├── screen/
│   │   │   │   ├── HomeScreen.kt
│   │   │   │   ├── QuizScreen.kt
│   │   │   │   ├── ResultScreen.kt
│   │   │   │   └── StatisticsScreen.kt
│   │   │   ├── components/
│   │   │   │   ├── QuestionCard.kt
│   │   │   │   ├── OptionButton.kt
│   │   │   │   └── StatCard.kt
│   │   │   └── theme/
│   │   │       ├── Color.kt
│   │   │       ├── Typography.kt
│   │   │       └── Theme.kt
│   │   ├── data/
│   │   │   ├── model/
│   │   │   │   ├── Question.kt
│   │   │   │   └── UserStats.kt
│   │   │   ├── api/
│   │   │   │   ├── ApiService.kt
│   │   │   │   └── ApiClient.kt
│   │   │   └── repository/
│   │   │       ├── QuestionRepository.kt
│   │   │       └── StatsRepository.kt
│   │   ├── viewmodel/
│   │   │   ├── QuizViewModel.kt
│   │   │   └── StatsViewModel.kt
│   │   └── MainActivity.kt
│   └── res/
└── build.gradle.kts
```

#### 3.2 UI 屏幕设计

##### 3.2.1 首屏 (HomeScreen)
- [ ] 设计首屏布局
  - 应用 Logo 和标题
  - 难度选择按钮（简单、中等、困难）
  - "开始练习" 主按钮
  - 查看统计数据入口

- [ ] 实现难度选择逻辑
  - 保存用户选择
  - 导航到答题屏幕

##### 3.2.2 答题屏幕 (QuizScreen)
- [ ] 设计答题界面
  ```
  ┌─────────────────────────────┐
  │  进度条 [▓▓▓░░░░░] 3/10      │
  ├─────────────────────────────┤
  │                             │
  │        7 + 8 = ?            │
  │                             │
  │  ┌───┐ ┌───┐ ┌───┐ ┌───┐   │
  │  │ A │ │ B │ │ C │ │ D │   │
  │  │15 │ │16 │ │14 │ │13 │   │
  │  └───┘ └───┘ └───┘ └───┘   │
  │                             │
  ├─────────────────────────────┤
  │   [提交]  [跳过]              │
  └─────────────────────────────┘
  ```

- [ ] 题目显示组件
  - 题目卡片 (Question Card)
  - 四选一选项按钮
  - 选项高亮效果

- [ ] 交互功能
  - [ ] 选择选项
  - [ ] 提交答案
  - [ ] 跳过题目
  - [ ] 答案反馈（正确/错误提示）
  - [ ] 自动加载下一题

- [ ] 答题统计面板
  - 实时显示正确数、总数、准确率

##### 3.2.3 结果屏幕 (ResultScreen)
- [ ] 设计结果反馈界面
  ```
  ┌─────────────────────────────┐
  │      🎉 恭喜！               │
  │                             │
  │    本次答题统计:             │
  │  - 总题数: 10                │
  │  - 正确数: 8                 │
  │  - 准确率: 80%               │
  │  - 耗时: 5分32秒             │
  │                             │
  │  [继续练习]  [返回首页]       │
  └─────────────────────────────┘
  ```

- [ ] 实现结果展示
  - 成绩总结
  - 难度分析
  - 答题耗时

- [ ] 导航选项
  - 继续练习（同难度）
  - 更换难度
  - 返回首页

##### 3.2.4 统计屏幕 (StatisticsScreen)
- [ ] 设计统计界面
  - 总体数据展示
    - 总练习次数
    - 总正确数
    - 总体准确率
  
  - 按难度分类统计
    ```
    简单难度:
    ■ 正确: 45   总计: 50   准确率: 90%
    
    中等难度:
    ■ 正确: 32   总计: 40   准确率: 80%
    
    困难难度:
    ■ 正确: 18   总计: 30   准确率: 60%
    ```

  - 成就徽章（可选）

- [ ] 实现数据刷新
  - 上拉刷新最新统计
  - 自动定时更新

#### 3.3 组件开发

##### 3.3.1 通用组件
- [ ] `QuestionCard` - 题目卡片组件
  - 显示题目内容
  - 支持不同大小
  
- [ ] `OptionButton` - 选项按钮组件
  - 显示选项文本
  - 点击效果反馈
  - 正确/错误状态显示
  
- [ ] `ProgressBar` - 进度条组件
  - 显示当前进度
  - 显示题号
  
- [ ] `StatCard` - 统计卡片组件
  - 显示数据项
  - 百分比展示
  - 趋势指示

##### 3.3.2 主题设计
- [ ] 定义主题颜色
  - Primary Color (主色调)
  - Secondary Color (辅助色)
  - Success Color (成功绿)
  - Error Color (错误红)
  - Background Color (背景色)

- [ ] 定义字体样式
  - 标题
  - 正文
  - 小字

#### 3.4 网络请求与数据绑定

##### 3.4.1 API 客户端设置
- [ ] 使用 Retrofit 库
  - 配置 BaseURL
  - 配置拦截器（日志、错误处理）
  - 配置超时时间

- [ ] 定义 API Service 接口
  ```kotlin
  interface MathcineApiService {
      @GET("/api/questions")
      suspend fun getQuestion(@Query("difficulty") difficulty: Int): Question
      
      @POST("/api/verify")
      suspend fun verifyAnswer(@Body request: VerifyRequest): VerifyResponse
      
      @GET("/api/stats/{userId}")
      suspend fun getUserStats(@Path("userId") userId: String): UserStats
  }
  ```

##### 3.4.2 数据仓库 (Repository)
- [ ] 创建 `QuestionRepository`
  - 获取题目逻辑
  - 缓存策略
  - 错误处理

- [ ] 创建 `StatsRepository`
  - 获取统计数据
  - 更新统计数据
  - 本地存储

##### 3.4.3 ViewModel 设计
- [ ] 创建 `QuizViewModel`
  - 管理当前题目状态
  - 管理答题进度
  - 处理答案提交

- [ ] 创建 `StatsViewModel`
  - 管理统计数据状态
  - 数据加载逻辑
  - 数据刷新逻辑

#### 3.5 用户交互逻辑

- [ ] 难度选择流程
  ```
  首屏 --选择难度--> 答题屏幕
  ```

- [ ] 答题流程
  ```
  显示题目 --> 用户选择 --> 提交/跳过 --> 
  验证答案 --> 反馈结果 --> 加载下一题 --> ... --> 结束
  ```

- [ ] 返回逻辑
  - 返回键行为定义
  - 确认对话框（是否保存进度）

#### 3.6 状态管理与生命周期

- [ ] Activity 生命周期管理
  - 保存屏幕状态
  - 处理配置变化（旋转）
  - 清理资源

- [ ] ViewModel 状态管理
  - 使用 StateFlow 管理 UI 状态
  - 错误状态处理
  - 加载状态处理

---

### Phase 4️⃣: 集成与测试

#### 4.1 集成测试
- [ ] 前后端联调
  - 测试 API 连接
  - 测试数据流程
  - 测试错误处理

- [ ] 端到端测试
  - 完整的答题流程
  - 统计数据更新验证
  - 网络异常处理

#### 4.2 单元测试
- [ ] 后端单元测试
  - 题目生成算法测试
  - 答案验证逻辑测试
  - 统计计算测试

- [ ] 前端单元测试
  - ViewModel 测试
  - Repository 测试
  - 组件测试

#### 4.3 UI 测试
- [ ] Espresso 测试
  - 屏幕导航测试
  - 用户交互测试
  - 数据展示测试

#### 4.4 性能测试
- [ ] 响应时间测试
- [ ] 内存使用测试
- [ ] 电池消耗测试
- [ ] 网络优化测试

---

### Phase 5️⃣: 优化与发布

#### 5.1 性能优化
- [ ] 代码优化
  - 移除未使用的代码
  - 优化算法复杂度
  - 内存泄漏检查

- [ ] UI 优化
  - 减少重新绘制
  - 优化列表性能
  - 使用异步加载

- [ ] 网络优化
  - 请求合并
  - 缓存策略
  - 压缩传输

#### 5.2 安全性
- [ ] API 安全
  - 添加认证（JWT / OAuth）
  - 添加 HTTPS
  - 输入验证

- [ ] 数据安全
  - 敏感数据加密
  - 本地存储加密
  - 用户隐私保护

#### 5.3 文档完善
- [ ] API 文档
- [ ] 代码注释
- [ ] 用户使用指南
- [ ] 部署文档

#### 5.4 发布
- [ ] APK 签名
- [ ] Google Play Store 准备
- [ ] 版本管理
- [ ] 发布流程

---

## 🛠️ 技术栈

### 前端 (Android)
| 技术 | 用途 |
|------|------|
| **Kotlin** | 编程语言 |
| **Jetpack Compose** | UI 框架 |
| **Material3** | UI 设计规范 |
| **Retrofit** | HTTP 网络请求 |
| **OkHttp** | HTTP 客户端 |
| **Coroutines** | 异步编程 |
| **StateFlow** | 状态管理 |
| **Room** | 本地数据库（可选） |
| **Hilt** | 依赖注入 |

### 后端（待选）
| 组件 | 选项 |
|------|------|
| **框架** | Spring Boot / Node.js / Flask / Go |
| **数据库** | PostgreSQL / MySQL / MongoDB |
| **缓存** | Redis |
| **API 文档** | Swagger / OpenAPI |
| **部署** | Docker / Kubernetes |

---

## 📊 开发时间线（参考）

```
Week 1-2:   环境搭建 + 项目初始化
Week 3-4:   后端核心模块开发
Week 5-6:   前端 UI 开发
Week 7:     集成与调试
Week 8:     测试与优化
Week 9+:    发布与维护
```

---

## 📝 后续工作

### 待完成的关键任务
1. 确定后端技术栈和数据库
2. 详细设计数据库表结构
3. 实现题目生成算法
4. 设计 API 文档
5. 实现前端 UI 设计稿
6. 集成测试框架
7. 制定发布计划

### 可选增强功能
- [ ] 用户注册和登录系统
- [ ] 成就和排行榜
- [ ] 练习计划和提醒
- [ ] 错题库功能
- [ ] 社交分享功能
- [ ] 离线练习模式
- [ ] 多语言支持

---

## 📞 联系与反馈

项目有任何问题或建议，欢迎提出！

**项目开始日期：** 2026年2月18日

---

*最后更新：2026年2月18日*

import json
import sqlite3
import os

def create_database(db_file):
    # 如果已存在先删掉，避免重复插入数据
    if os.path.exists(db_file):
        os.remove(db_file)
        
    conn = sqlite3.connect(db_file)
    cursor = conn.cursor()
    
    # 建立 question 表结构
    cursor.execute('''
    CREATE TABLE questions (
        uuid TEXT PRIMARY KEY,
        category TEXT NOT NULL,
        question TEXT NOT NULL,
        options TEXT NOT NULL,
        correct_option_index INTEGER NOT NULL,
        explanation TEXT
    )
    ''')
    
    conn.commit()
    return conn

def insert_data(conn, data_file):
    with open(data_file, 'r', encoding='utf-8') as f:
        data = json.load(f)
        
    cursor = conn.cursor()
    
    inserted = 0
    for item in data:
        # SQLite 插入语句
        cursor.execute('''
        INSERT INTO questions (uuid, category, question, options, correct_option_index, explanation)
        VALUES (?, ?, ?, ?, ?, ?)
        ''', (
            item['uuid'], 
            item['category'], 
            item['question'], 
            item['options'], 
            item['correct_option_index'], 
            item.get('explanation', '')
        ))
        inserted += 1
        
    conn.commit()
    print(f"数据包生成成功！共写入 {inserted} 条题目记录。")

if __name__ == '__main__':
    # 设定输出文件名和输入文件名
    db_file_name = 'math_pack.db'
    data_file_name = 'data.json'
    
    print(f"开始编译 {data_file_name} 到数据库 {db_file_name}...")
    try:
        connection = create_database(db_file_name)
        insert_data(connection, data_file_name)
        connection.close()
    except Exception as e:
        print("遇到错误：", e)

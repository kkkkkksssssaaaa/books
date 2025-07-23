from openai import OpenAI
from dotenv import load_dotenv
import os

load_dotenv()
api_key = os.getenv('OPENAI_API_KEY')

def summarize_text(file_path: str):
  client = OpenAI(api_key=api_key)
  
  with open(file_path, 'r', encoding='utf-8') as f:
    text = f.read()
    
  system_prompt = f'''
  너는 다음 글을 요약해 주는 봇이야. 아래 글을 읽고 내용을 요약해 줘.
  요약해야 하는 포맷은 다음과 같아.
  
  # 제목
  
  ## 저자의 문제 인식 및 주장 (15문장 이내)
  
  ## 저자 소개 
  
  ============== 이하 텍스트 ==============
  
  
  {text}
  '''
  
  print(system_prompt)
  print('--------------------------------')
  
  response = client.chat.completions.create(
    model="gpt-4o-mini",
    temperature=0.1,
    messages=[{"role": "system", "content": system_prompt}]
  )
  
  return response.choices[0].message.content

if __name__ == "__main__":
  text_file_path = "chap04/output/KCI_FI001725284_with_processing.txt"
  
  summary = summarize_text(text_file_path)
  print(summary)
  
  with open(f"chap04/output/summary_{text_file_path}.txt", 'w', encoding='utf-8') as f:
    f.write(summary)
  
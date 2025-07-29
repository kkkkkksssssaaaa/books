from glob import glob
from openai  import OpenAI
from dotenv import load_dotenv
import os
import base64

load_dotenv()
api_key = os.getenv('OPENAI_API_KEY')

client = OpenAI(api_key=api_key)

def encode_image(image_path):
  with open(image_path, "rb") as image_file:
    return base64.b64encode(image_file.read()).decode('utf-8')

def image_quiz(image_path):
  encoded_image = encode_image(image_path)
  
  quiz_prompt = """
  제공한 이미지를 바탕으로, 다음과 같은 양식으로 퀴즈를 만들어 주세요.
  정답은 (1)~(4) 중 하나만 해당하도록 출제하세요.
  아래는 예시입니다.
  
  ----- 예시 -----
  
  Q: 다음 이미지에 대한 설명 중 옳지 않은 것은 무엇인가요?
  - (1) 사진 속 인물은 머리가 길다.
  - (2) 사진 속 인물은 흰 옷을 입었다.
  - (3) 사진 속 인물은 여성이다.
  - (4) 사진에 OSEN 이라는 문자가 포함 되어 있다.
  
  정답: (1) 사진 속 인물은 머리를 묶고 있습니다.
  (주의: 정답은 (1)~(4) 중 하나만 선택하도록 출제하세요.)
  """
  
  messages = [
    {
      "role": "user",
      "content": [
        {
          "type": "text",
          "text": quiz_prompt
        },
        {
          "type": "image_url",
          "image_url": {
            "url": f"data:image/jpeg;base64,{encoded_image}"
          }
        }
      ]
    }
  ] 
  
  response = client.chat.completions.create(
    model="gpt-4o-mini",
    messages=messages,
  )
  
  return response.choices[0].message.content

txt = ''
no = 1

for g in glob("./chap06/image/*.jpg"):
  try:
    q = image_quiz(g)
  except Exception as e:
    print(e)
    continue
  
  divider = f'## 문제 {no}\n\n'
  print(divider)
  txt += divider
  filename = os.path.basename(g)
  txt += f'![{filename}]({g})\n\n'
  
  # 문제 추가
  print(q)
  txt += q + '\n\n--------------------------------------------\n\n'
  
  with open("./chap06/image/image_quiz.md", "w", encoding="utf-8") as f:
    f.write(txt)
  
  no += 1

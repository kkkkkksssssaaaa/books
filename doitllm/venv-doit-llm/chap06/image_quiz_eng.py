from glob import glob
from openai  import OpenAI
from dotenv import load_dotenv
import os
import json
import base64

load_dotenv()
api_key = os.getenv('OPENAI_API_KEY')

client = OpenAI(api_key=api_key)

def encode_image(image_path):
  with open(image_path, "rb") as image_file:
    return base64.b64encode(image_file.read()).decode('utf-8')

def image_quiz(image_path, n_trial = 0, max_trial = 3):
  if n_trial >= max_trial:
    raise Exception("Failed to generate a quiz.")
  
  encoded_image = encode_image(image_path)
  
  quiz_prompt = """
  제공한 이미지를 바탕으로, 다음과 같은 양식으로 퀴즈를 만들어 주세요.
  정답은 (1)~(4) 중 하나만 해당하도록 출제하세요.
  토익 리스닝 문제 스타일로 문제를 만들어 주세요.
  아래는 예시입니다.
  
  ----- 예시 -----
  
  Q: 다음 이미지에 대한 설명 중 옳지 않은 것은 무엇인가요?
  - (1) 사진 속 인물은 머리가 길다.
  - (2) 사진 속 인물은 흰 옷을 입었다.
  - (3) 사진 속 인물은 여성이다.
  - (4) 사진에 OSEN 이라는 문자가 포함 되어 있다.
  
  Listening: Which of the following descriptions about the image is incorrect?
  - (1) The person in the photo has long hair.
  - (2) The person in the photo is wearing white clothes.
  - (3) The person in the photo is a woman.
  - (4) The text "OSEN" is included in the photo.
  
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
  
  try:
    response = client.chat.completions.create(
      model="gpt-4o-mini",
      messages=messages,
    )
  except Exception as e:
    print("failed\n" + e)
    return image_quiz(image_path, n_trial + 1)
  
  content = response.choices[0].message.content
  
  if "Listening" in content:
    return content, True
  else:
    return image_quiz(image_path, n_trial + 1)

txt = ''
eng_dict = []
no = 1

for g in glob("./chap06/image/*.jpg"):
  q, is_success = image_quiz(g)
  
  if not is_success:
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
  
  eng = q.split('Listening:')[1].split('정답:')[0].strip()
  
  eng_dict.append({
    "no": no,
    "eng": eng,
    "image": filename
  })
  
  with open("./chap06/image/image_quiz_eng.json", "w", encoding="utf-8") as f:
    json.dump(eng_dict, f, ensure_ascii=False, indent=4)
  
  no += 1

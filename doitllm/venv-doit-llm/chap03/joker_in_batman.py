from openai import OpenAI
from dotenv import load_dotenv
import os

load_dotenv()
api_key = os.getenv('OPENAI_API_KEY')

client = OpenAI(api_key=api_key)

response = client.chat.completions.create(
    model="gpt-4o",
    temperature=0.9,
    messages=[
        {"role": "system", "content": "너는 배트맨에 나오는 조커야. 악당처럼 대답해 줘."},
        {"role": "user", "content": "세상에서 누가 제일 아름답니?"}
    ]
)

print(response.choices[0].message.content)
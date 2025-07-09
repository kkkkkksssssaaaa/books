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
        {"role": "system", "content": "너는 유치원생이야. 유치원생처럼 답변해 줘."},
        {"role": "user", "content": "아이브"}
    ]
)

print(response.choices[0].message.content)
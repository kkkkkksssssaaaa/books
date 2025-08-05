from langchain_openai import ChatOpenAI
from langchain_core.messages import HumanMessage#, SystemMessage, AIMessage
from dotenv import load_dotenv
import os

load_dotenv()
api_key = os.getenv('OPENAI_API_KEY')

llm = ChatOpenAI(model = "gpt-4o-mini")

messages = [
  HumanMessage("안녕 안녕, 나는 왕해삼이야. 한국인이야."),
]

while True:
  user_input = input("사용자: ")
  
  if user_input == "종료":
    break
  
  messages.append(HumanMessage(user_input))
  ai_response = llm.invoke(messages)
  messages.append(ai_response)
  print(ai_response.content)
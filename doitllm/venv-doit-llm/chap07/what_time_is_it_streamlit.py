from gpt_functions import get_current_time, tools
from openai import OpenAI
from dotenv import load_dotenv
import os
import json
import streamlit as st

load_dotenv()
api_key = os.getenv('OPENAI_API_KEY')

client = OpenAI(api_key=api_key)

def get_ai_response(messages, tools = None):
  response = client.chat.completions.create(
    model="gpt-4o-mini",
    messages=messages,
    tools=tools
  )
  return response

st.title("What Time Is It?")

if "messages" not in st.session_state:
  st.session_state.messages = [
    {
      "role": "system",
      "content": "너는 사용자를 도와주는 상담사야."
    }
  ]

for message in st.session_state.messages:
  st.chat_message(message["role"]).write(message["content"])

if user_input := st.chat_input():
  st.session_state.messages.append({"role": "user", "content": user_input})
  st.chat_message("user").write(user_input)
  
  ai_response = get_ai_response(st.session_state.messages)
  ai_message = ai_response.choices[0].message
  st.chat_message("assistant").write(ai_message.content)
  
  tool_calls = ai_message.tool_calls
  
  if tool_calls:
    for tool_call in tool_calls:
      tool_name = tool_call.function.name
      tool_call_id = tool_call.id
      
      arguments = json.loads(tool_call.function.arguments)
    
      if tool_name == "get_current_time":
        st.session_state.messages.append({
          "role": "function",
          "tool_call_id": tool_call_id,
          "content": get_current_time(timezone = arguments["timezone"]),
          "name": tool_name,
        })
      
      st.session_state.messages.append({
        "role": "system",
        "content": "이제 주어진 결과를 바탕으로 답변할 차례야."
      })
      
    ai_response = get_ai_response(st.session_state.messages)
    ai_message = ai_response.choices[0].message
    
  # ChatCompletionMessage 객체를 딕셔너리로 변환하여 저장
  ai_message_dict = {
    "role": ai_message.role,
    "content": ai_message.content
  }
  st.session_state.messages.append(ai_message_dict)
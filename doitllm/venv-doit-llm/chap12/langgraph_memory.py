from langchain_openai import ChatOpenAI

load_dotenv()
api_key = os.getenv('OPENAI_API_KEY')

model = ChatOpenAI(api_key=api_key)

from typing import Annotated
from typing_extensions import TypedDict
from langgraph.graph import StateGraph, START, END
from langgraph.graph.message import add_messages

class State(TypedDict):
  """
  State 클래스는 TypedDict 를 상속받습니다.
  
  속성:
      messages(Annotated[list[str], add_messages]): 메세지들은 "list" 타입을 가집니다.
      'add_messages' 함수는 이 상태 키가 어떻게 업데이트 되어야 하는지를 정의합니다.
      (이 경우, 메세지를 덮어쓰는 대신 리스트에 추가합니다.)
  """
  
  messages: Annotated[list[str], add_messages]

graph_builder = StateGraph(State)

def generate(state: State):
  """
  주어진 상태를 기반으로 챗봇의 응답 메세지를 생성합니다.
  
  매개변수:
  state (State): 현재 대화 상태를 나타내는 객체로, 이전 메세지들이 포함 되어 있습니다.
  
  반환값:
  dict: 모델이 생성한 응답 메세지를 포함하는 dictionary
      형식은 {"messages": [응답메세지]}입니다.
  """
  
  return {"messages": [model.invoke(state["messages"])]}

graph_builder.add_node("generate", generate)

graph_builder.add_edge(START, "generate")
graph_builder.add_edge("generate", END)

graph = graph_builder.compile()

from langchain.schema import HumanMessage

while True:
  user_input = input("You\t:")
  
  if user_input in ["exit", "quit", "q"]:
    break
  
  for event in graph.stream({"messages": [HumanMessage(user_input)]}, stream_mode = "values"):
    event["messages"][-1].pretty_print()
  
  print(f"\n현재 메세지 개수: {len(event["messages"])} \n--------------------------------\n")
  
  
graph_builder.add_node("generate", generate)
graph_builder.add_edge(START, "generate")
graph_builder.add_edge("generate", END)

from langchain.checkpoint.memory import MemorySaver

memory = MemorySaver()

config = {"configurable": {"thread_id": "abcd"}}

graph = graph_builder.compile(checkpoint_saver = memory)

#-------

from langchain.schema import HumanMessage

while True:
  user_input = input("You\t:")
  
  if user_input in ["exit", "quit", "q"]:
    break
  
  for event in graph.stream({"messages": [HumanMessage(user_input)]}, stream_mode = "values"):
    event["messages"][-1].pretty_print()
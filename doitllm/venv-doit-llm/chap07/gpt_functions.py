from datetime import datetime
from altair.utils.core import P
import pytz
import yfinance as yf

def get_current_time(timezone: str = 'Asia/Seoul'):
  tz = pytz.timezone(timezone)
  
  now = datetime.now(tz).strftime("%Y-%m-%d %H:%M:%S")
  now_timezone = f'{now} {timezone}'
  
  print(f"Current time: {now}")
  
  return now

tools = [
  {
    "type": "function",
    "function": {
      "name": "get_current_time",
      "description": "현재 날짜와 시간을 반환합니다.",
      "parameters": {
        "type": "object",
        "properties": {
          "timezone": {
            "type": "string",
            "description": "타임존을 입력해주세요.",
          },
        },
        "required": ["timezone"]
      }
    }
  },
  {
    "type": "function",
    "function": {
      "name": "get_yf_stock_info",
      "description": "해당 종목의  Yahoo Finance 정보를 반환합니다.",
      "parameters": {
        "type": "object",
        "properties": {
          "ticker": {
            "type": "string",
            "description": "종목 코드를 입력해주세요.",
          },
        },
        "required": ["ticker"]
      }
    }
  },
  {
    "type": "function",
    "function": {
      "name": "get_yf_stock_history",
      "description": "해당 종목의  Yahoo Finance 정보를 반환합니다.",
      "parameters": {
        "type": "object",
        "properties": {
          "ticker": {
            "type": "string",
            "description": "종목 코드를 입력해주세요.",
          },
          "period": {
            "type": "string",
            "description": "기간을 입력해주세요.",
          },
        },
        "required": ["ticker", "period"]
      }
    } 
  },
  {
    "type": "function",
    "function": {
      "name": "get_yf_stock_recommendation",
      "description": "해당 종목의  Yahoo Finance 추천 정보를 반환합니다.",
      "parameters": {
        "type": "object",
        "properties": {
          "ticker": {
            "type": "string",
            "description": "종목 코드를 입력해주세요.",
          },
        },
        "required": ["ticker"]
      }
    }
  }
]

def get_yf_stock_info(ticker: str):
  stock = yf.Ticker(ticker)
  info = stock.info
  print(info)
  return str(info)

def get_yf_stock_history(ticker: str, period: str = "5d"):
  stock = yf.Ticker(ticker)
  history = stock.history(period = period)
  history_md = history.to_markdown()
  print(history_md)
  return history_md

def get_yf_stock_recommendation(ticker: str):
  stock = yf.Ticker(ticker)
  recommendation = stock.recommendations
  recommendation_md = recommendation.to_markdown()
  print(recommendation_md)
  return recommendation_md

if __name__ == "__main__":
  get_yf_stock_history("AAPL", "5d")
  print("--------------------------------")
  get_yf_stock_recommendation("AAPL")
  

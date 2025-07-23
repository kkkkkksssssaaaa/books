import pymupdf
import os

pdf_file_path = "chap04/KCI_FI001725284.pdf"
doc = pymupdf.open(pdf_file_path)

header_height = 100
footer_height = 100

full_text = ''

for page in doc:
  rect = page.rect
  
  header = page.get_text(
    clip=(
      0,
      0,
      rect.width,
      header_height
    )
  )
  
  footer = page.get_text(
    clip=(
      0,
      rect.height - footer_height,
      rect.width,
      rect.height
    )
  )
  
  text = page.get_text(
    clip=(
      0,
      header_height,
      rect.width,
      rect.height - footer_height
    )
  )
  
  full_text += text + '\n-------------------------------------------\n'

pdf_file_name = os.path.basename(pdf_file_path)
pdf_file_name = os.path.splitext(pdf_file_name)[0]

txt_file_name = f"chap04/output/{pdf_file_name}_with_processing.txt"

with open(txt_file_name, 'w', encoding='utf-8') as f:
  f.write(full_text)

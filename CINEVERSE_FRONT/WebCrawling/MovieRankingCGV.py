from bs4 import BeautifulSoup
import requests
import pandas as pd
import sys
sys.stdout.reconfigure(encoding='utf-8')

# CGV 영화 목록 페이지 URL
url = 'http://www.cgv.co.kr/movies/'
res = requests.get(url)
soup = BeautifulSoup(res.text, 'html.parser')

# 영화 제목 가져오기
titles = soup.find('div', class_='wrap-movie-chart')
titless = titles.find_all('strong', class_='title')
title_name = [title.text for title in titless]

# 예매율 및 개봉일 가져오기
box = soup.find('div', class_='sect-movie-chart')
percent = box.find_all('strong', class_='percent')
percent_list = [per.text[3:] for per in percent]

dates = box.find_all('span', class_='txt-info')
dates_list = [date.text.strip()[:10] for date in dates]

# 데이터프레임 생성
movie_df = pd.DataFrame({
    "영화 제목": title_name,
    "예매율": percent_list,
    "개봉일": dates_list
})

# 상위 10개의 행만 표시하며, 인덱스를 1부터 시작하여 순위 표시
top_10_movies = movie_df.head(10).reset_index(drop=True)
top_10_movies.index += 1

# 데이터프레임을 JSON 파일로 저장
file_path = r'C:\\Users\\pc\\Desktop\\HW BEYOND SW\\be04-4th-6team-CINE-VERSE-FRONT\\CINEVERSE_FRONT\\cine-verse\\public\\top_10_movies.json'
top_10_movies.to_json(file_path, force_ascii=False, orient='index', indent=2)

print(f"파일이 저장되었습니다: {file_path}")

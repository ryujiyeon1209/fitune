import pandas as pd
from django.shortcuts import render
from django.http import JsonResponse
from sklearn.metrics.pairwise import cosine_similarity


# Create your views here.
def recommend_view(request):
    if request.method == 'POST':
        # POST 요청에서 데이터를 추출하고 처리
        user_data = request.POST  # 혹은 request.body를 사용하여 JSON 데이터 파싱

        # 추천 로직을 구현하여 응답 데이터 생성
        recommended_data = {}  # 추천 결과 데이터
        df = pd.read_csv('dataset.csv')

        # 유사도 계산
        similarities = cosine_similarity([user_data], df)

        # 유사도가 가장 높은 컬럼 인덱스 찾기
        most_similar_column_index = similarities.argmax()

        most_similar_column = df.columns[most_similar_column_index]

        # 결과 반환
        recommended_data = {
            'most_similar_column': most_similar_column,
            'similarity_score': similarities[0][most_similar_column_index]
        }

        print(recommended_data)
        # 결과 반환
        recommended_data = {}  # 추천 결과 데이터
        return JsonResponse(recommended_data)
    else:
        return JsonResponse({'error': 'Invalid request method'})